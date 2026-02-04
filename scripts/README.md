# Utility Scripts

This folder contains utility and learning scripts for the CalcEngine project.

## Logging Conversion Scripts

Two scripts that demonstrate automated refactoring to convert `System.out.println` to SLF4J logging.

### Python Version: `convert-logging.py`
More featureful version with better error handling.

**Usage:**
```bash
cd /Users/jupiter/IdeaProjects/CalcEngine
python3 scripts/convert-logging.py
```

**What it does:**
- Scans all Java files in `MCalc/src/main/`
- Adds `@Slf4j` annotation and Lombok import if needed
- Converts `System.out.println(...)` → `log.info(...)`
- Converts `System.err.println(...)` → `log.error(...)`
- Provides detailed output of files processed

### Bash Version: `fix-logging.sh`
Simpler version using standard Unix tools.

**Usage:**
```bash
cd /Users/jupiter/IdeaProjects/CalcEngine
./scripts/fix-logging.sh
```

**What it does:**
- Uses `find` and `sed` to process Java files
- Same conversion logic as Python version
- Good for learning `sed` and shell scripting

## Learning Concepts

These scripts demonstrate:
- **AST-aware refactoring** - Adding imports and annotations at correct positions
- **Pattern matching** - Using regex to find and replace code patterns
- **File traversal** - Recursively processing directories
- **Lombok integration** - Using `@Slf4j` for logging boilerplate

## Verification

After running either script, verify with:
```bash
# Check for remaining println statements
semgrep --config=.semgrep.yml MCalc/src/ | grep 'java-system-out-println'

# Compile to ensure no syntax errors
mvn compile
```

## Notes

- Both scripts skip files that already have `@Slf4j`
- Only processes `src/main`, not test files
- Commits should be reviewed carefully after automated refactoring
