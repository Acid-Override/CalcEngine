#!/usr/bin/env python3
"""
Advanced System.out.println to SLF4J converter
Handles edge cases and maintains code formatting
"""

import re
import os
import glob
from pathlib import Path

def has_slf4j_annotation(content):
    """Check if file already has @Slf4j annotation"""
    return '@Slf4j' in content

def has_lombok_import(content):
    """Check if file has lombok slf4j import"""
    return 'import lombok.extern.slf4j.Slf4j' in content

def add_slf4j_to_file(file_path):
    """Add @Slf4j annotation and import to Java file"""
    with open(file_path, 'r') as f:
        content = f.read()
    
    lines = content.split('\n')
    modified = False
    
    # Skip if already has @Slf4j
    if has_slf4j_annotation(content):
        return convert_println_statements(file_path, content)
    
    # Find package line to insert import after
    package_line = -1
    class_line = -1
    
    for i, line in enumerate(lines):
        if line.startswith('package '):
            package_line = i
        elif re.match(r'^\s*(public\s+)?(abstract\s+)?(final\s+)?class\s+', line):
            class_line = i
            break
    
    # Add import after package declaration
    if package_line >= 0:
        lines.insert(package_line + 1, '')
        lines.insert(package_line + 2, 'import lombok.extern.slf4j.Slf4j;')
        class_line += 2
        modified = True
    elif class_line >= 0:
        lines.insert(0, 'import lombok.extern.slf4j.Slf4j;')
        lines.insert(1, '')
        class_line += 2
        modified = True
    
    # Add @Slf4j annotation before class
    if class_line >= 0 and modified:
        lines.insert(class_line, '@Slf4j')
    
    if modified:
        content = '\n'.join(lines)
        with open(file_path, 'w') as f:
            f.write(content)
        print(f"✅ Added @Slf4j to: {file_path}")
    
    return convert_println_statements(file_path, content)

def convert_println_statements(file_path, content):
    """Convert System.out.println to log.info"""
    original_content = content
    
    # Convert different println patterns
    patterns = [
        (r'System\.out\.println\((.*?)\);', r'log.info(\1);'),
        (r'System\.err\.println\((.*?)\);', r'log.error(\1);'),
    ]
    
    for pattern, replacement in patterns:
        content = re.sub(pattern, replacement, content)
    
    if content != original_content:
        with open(file_path, 'w') as f:
            f.write(content)
        print(f"🔄 Converted System.out.println in: {file_path}")
        return True
    return False

def main():
    """Main conversion process"""
    print("🚀 Starting System.out.println to SLF4J conversion...")
    
    # Find all Java files in src/main (exclude tests)
    java_files = glob.glob('MCalc/src/main/**/*.java', recursive=True)
    
    converted_files = 0
    total_conversions = 0
    
    for file_path in java_files:
        # Skip if file doesn't have System.out.println
        with open(file_path, 'r') as f:
            if 'System.out.println' not in f.read() and 'System.err.println' not in f.read():
                continue
        
        print(f"📝 Processing: {file_path}")
        
        try:
            if add_slf4j_to_file(file_path):
                converted_files += 1
                total_conversions += 1
        except Exception as e:
            print(f"❌ Error processing {file_path}: {e}")
    
    print(f"\n🎉 Conversion Complete!")
    print(f"📊 Files processed: {converted_files}")
    print(f"📈 Total conversions: {total_conversions}")
    print(f"\n🔍 Next steps:")
    print(f"1. Run: semgrep --config=.semgrep.yml MCalc/src/ | grep 'java-system-out-println'")
    print(f"2. Compile: mvn compile")
    print(f"3. Commit changes!")

if __name__ == '__main__':
    main()