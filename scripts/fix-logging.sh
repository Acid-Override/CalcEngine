#!/bin/bash

# Automated System.out.println to SLF4J conversion script
# Usage: ./fix-logging.sh

echo "🔧 Starting System.out.println to SLF4J conversion..."

# Find all non-test Java files with System.out.println
find MCalc/src/main -name "*.java" -exec grep -l "System\.out\.println" {} \; | while read file; do
    echo "📝 Processing: $file"
    
    # Check if file already has @Slf4j annotation
    if ! grep -q "@Slf4j" "$file"; then
        # Check if file has package declaration to place import correctly
        if grep -q "^package " "$file"; then
            # Add @Slf4j import and annotation after package
            sed -i '' '/^package /a\
\
import lombok.extern.slf4j.Slf4j;\
' "$file"
            
            # Add @Slf4j annotation before class declaration
            sed -i '' '/^public class\|^class\|^public final class\|^final class\|^public abstract class\|^abstract class/i\
@Slf4j' "$file"
        else
            # No package, add at top of file
            sed -i '' '1i\
import lombok.extern.slf4j.Slf4j;\
\
@Slf4j' "$file"
        fi
    fi
    
    # Convert System.out.println patterns to log.info
    # Handle various common patterns
    sed -i '' 's/System\.out\.println(\([^)]*\));/log.info(\1);/g' "$file"
    sed -i '' 's/System\.err\.println(\([^)]*\));/log.error(\1);/g' "$file"
    
    echo "✅ Converted: $file"
done

echo "🎉 Conversion complete!"
echo "📊 Run Semgrep to verify fixes: semgrep --config=.semgrep.yml MCalc/src/"