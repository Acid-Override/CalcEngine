# AI Operating Instructions

## Project Context

This is a Java 21 educational codebase containing algorithm practice problems, coding challenges, and design pattern implementations. The main module `MCalc` serves as a Maven project with integrated PostgreSQL/Hibernate functionality and Kafka messaging capabilities.

## How to Operate

### Project Navigation
- Working directory: `/Users/jupiter/IdeaProjects/CalcEngine/MCalc`
- Root directory: `/Users/jupiter/IdeaProjects/CalcEngine`
- AI folder: `/Users/jupiter/IdeaProjects/CalcEngine/.ai/`

### Build & Test Commands
Run from `/MCalc` directory:
```bash
mvn clean compile      # Clean and compile the project
mvn test               # Run all tests using JUnit 5
mvn clean test         # Clean, compile, and run tests
mvn clean package      # Build JAR package
```

### Key Project Patterns
- **Repository Pattern**: JPA entities with Criteria API queries
- **Entity Mapping**: All entities extend `AuditableEntity` with soft delete support
- **Testing Strategy**: JUnit 5 with dedicated test classes for each algorithm

### Important Notes

#### Test File Locations
- **Non-standard structure**: Test files exist in BOTH `src/main/java` and `src/test/java`
- **Impact**: JUnit dependencies must be in **compile scope**, not test scope
- **Do NOT** add `<scope>test</scope>` to JUnit Jupiter dependencies

#### Maven Surefire Configuration
- JUnit 5 is configured via Surefire plugin dependencies
- **DO NOT** add `junit-platform-runner` - it pulls in JUnit 4 and breaks provider detection
- Current working configuration uses `junit-platform-launcher` and `junit-jupiter-engine`

#### Git Commit Messages
- **DO NOT** include Claude Code attribution or co-authorship
- Keep commit messages clean and focused on actual changes

## Session Logging

All work sessions are logged in `.ai/sessions/` with format:
```
{date}-{key_words}.md
```

Example: `2026-02-03-junit5-fix-two-sum-tests.md`

## Code Change Protocol

When making changes, follow this process:

1. **Analysis Phase** - Identify and explain the problem
2. **Solution Proposal** - Propose fix with alternatives
3. **User Approval** - Wait for confirmation before implementing
4. **Implementation** - Make the change and verify

## Reference Documentation

- Main project documentation: `CLAUDE.md` (in project root)
- Session logs: `.ai/sessions/`
- This file: `.ai/instructions.md`
