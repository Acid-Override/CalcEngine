# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

> **📁 AI Workspace:** Additional context and session logs are maintained in the [`.ai/`](.ai/) folder:
> - [`.ai/instructions.md`](.ai/instructions.md) - Operating instructions and project navigation
> - [`.ai/sessions/`](.ai/sessions/) - Work session logs with format `{date}-{keywords}.md`

## Project Overview

This is a Java 21 educational codebase containing algorithm practice problems, coding challenges, and design pattern implementations. The main module `MCalc` serves as a Maven project with integrated PostgreSQL/Hibernate functionality and Kafka messaging capabilities.

## Build System & Commands

**Maven Commands (run from `/MCalc` directory):**
- `mvn clean compile` - Clean and compile the project
- `mvn test` - Run all tests using JUnit 5
- `mvn clean test` - Clean, compile, and run tests
- `mvn clean package` - Build JAR package

**Testing:**
- Uses JUnit 5 (Jupiter) for unit testing
- Test files follow naming convention: `*Test.java`
- Tests are located in `src/test/java` with same package structure as main code

**Database:**
- PostgreSQL database configured via `hibernate.cfg.xml`
- Database name: `demo`, user: `jupiter`
- Run `docker-compose up` from `/docker` directory to start Kafka cluster
- Hibernate auto-update enabled (`hibernate.hbm2ddl.auto=update`)

## Code Architecture

**Package Structure:**
- `com.pluralsight.calcengine` - Core calculator and basic Java examples
- `com.playground` - Advanced implementations and design patterns
- `com.playground.PostgreSQL` - Hibernate/JPA entities and repositories
- `com.DailyByte`, `com.CoderPad`, `kata.kyu*` - Algorithm challenges by source

**Key Patterns:**
- **Repository Pattern**: `OrderRepository`, `CustomerRepository` with Criteria API queries
- **Entity Mapping**: JPA entities with proper relationships and soft delete support
- **Hibernate Integration**: `HibernateUtil` for SessionFactory management
- **Decorator Pattern**: Coffee/MyList implementations in `playground.Patterns`
- **Observer Pattern**: Stock market implementation
- **Visitor Pattern**: Payment processing implementations

**Entity Relationships:**
- `Customer` 1:N `Order` 1:N `OrderItem` N:1 `Product`
- All entities extend `AuditableEntity` with created/modified timestamps
- Soft delete implemented with `@SQLDelete` and `deleted` boolean field

**Testing Strategy:**
- Repository classes have corresponding integration tests
- Hibernate SessionFactory properly configured for test environment
- Each algorithm/challenge has dedicated test class

## Key Dependencies

- **Java 21** with Maven compiler plugin 3.14.0
- **Hibernate 6.5.3** with PostgreSQL driver 42.7.5
- **JUnit 5.10.5** for testing
- **Lombok 1.18.34** for code generation
- **Log4j 2.19.0** for logging
- **Kafka 3.8.1** and **MongoDB 5.1.4** drivers

## Development Notes

- All entities use Lombok annotations (`@Getter`, `@Setter`, `@ToString`)
- Hibernate proxy-aware `equals()` and `hashCode()` implementations
- Criteria API used extensively for type-safe queries
- Transaction management with proper rollback handling in repositories

**Git Commit Messages:**
- Do NOT include Claude Code attribution or co-authorship in commit messages
- Keep commit messages clean and focused on the actual changes made

---

# Claude Code Operating Instructions

## Code Change Protocol

When proposing or making code changes, follow this process:

### 1. Analysis Phase
- **Identify the Issue**: Clearly explain what problem you're solving
- **Root Cause**: Explain why the issue exists 
- **Impact Assessment**: Describe the consequences of not fixing it

### 2. Solution Proposal Phase
- **Proposed Solution**: Describe your intended fix in detail
- **Alternative Approaches**: Mention other solutions you considered and why you chose this one
- **Trade-offs**: Explain any downsides or limitations of your approach
- **Code Example**: Show the specific before/after code when possible

### 3. User Approval Phase
- **Wait for Confirmation**: Do not make changes until the user approves
- **Answer Follow-up Questions**: Be prepared to explain technical details, provide more context, or discuss alternatives
- **Modify Approach**: Be flexible if the user suggests a different solution

### 4. Implementation Phase
- **Make the Change**: Implement the approved solution
- **Verify the Fix**: Test that the change works as expected
- **Document Impact**: Briefly summarize what was changed

## Code Quality Standards

### Warning and Error Handling
- **Explain Each Warning**: For compiler warnings, explain what causes them and why they matter
- **Justify Suppressions**: If using `@SuppressWarnings`, explain why suppression is appropriate vs fixing the root cause
- **Security Implications**: Always consider if a warning indicates a potential security issue

### Refactoring Guidelines
- **Minimal Scope**: Make the smallest change that solves the problem
- **Backward Compatibility**: Preserve existing functionality unless explicitly asked to change it
- **Educational Value**: Since this is a learning codebase, prefer solutions that demonstrate best practices

### Code Examples
When explaining changes, provide:
```java
// Before (problematic code)
PriorityQueue heap = new PriorityQueue(); // Raw type warning

// After (fixed code)  
PriorityQueue<Integer> heap = new PriorityQueue<>(); // Type-safe generics

// Why: Raw types bypass compile-time type checking, risking ClassCastException at runtime
```

## Communication Style

### Technical Explanations
- **Start Simple**: Begin with a high-level explanation
- **Provide Details**: Offer technical depth when requested
- **Use Examples**: Include concrete code examples
- **Explain Consequences**: Describe what happens if the issue isn't fixed

### Decision Making
- **Present Options**: When multiple solutions exist, explain the pros/cons of each
- **Recommend Best Practice**: Suggest the approach that follows Java/industry standards
- **Respect Preferences**: Defer to user's architectural decisions and coding style

### Follow-up Support
- **Answer Questions**: Be prepared to explain any aspect of your proposed changes
- **Provide Context**: Explain how changes fit into the larger codebase architecture
- **Offer Alternatives**: If the user doesn't like your approach, suggest other options

## Example Workflow

```
1. Claude identifies issue: "There's a deprecation warning in OrderRepository.java line 236"

2. Claude explains: "The multiselect() method is deprecated in JPA 3.2 because..."

3. Claude proposes: "I can fix this by either: A) Suppressing the warning, or B) Refactoring to use cb.tuple(). Here's why I recommend option A..."

4. User responds: "Why not option B? Show me what that would look like."

5. Claude provides detailed comparison and code examples

6. User approves: "Go with option A for now"

7. Claude implements and confirms the fix works
```

This ensures you understand not just what to change, but why the change is beneficial and have approved the approach before implementation.

## Advanced Best Practices

### Object-Oriented Design Principles (SOLID)

#### Single Responsibility Principle (SRP)
- **Class Purpose**: Ensure each class has one clear, focused responsibility
- **Method Scope**: Functions should do one thing well
- **Refactoring Trigger**: If explaining what a class does requires "and", consider splitting it

#### Open/Closed Principle (OCP)
- **Extension Points**: Design for extension through interfaces and inheritance
- **Modification Risk**: Prefer adding new code over modifying existing stable code
- **Strategy Pattern**: Use for algorithms that may change (like payment processing)

#### Liskov Substitution Principle (LSP)
- **Inheritance Contracts**: Subclasses must be substitutable for their base classes
- **Behavioral Compatibility**: Override methods should strengthen, not weaken, contracts
- **Interface Segregation**: Don't force implementers to depend on unused methods

#### Interface Segregation Principle (ISP)
- **Focused Interfaces**: Create small, focused interfaces rather than large monolithic ones
- **Client Specificity**: Interfaces should serve specific client needs
- **Default Methods**: Use Java 8+ default methods judiciously

#### Dependency Inversion Principle (DIP)
- **Abstract Dependencies**: Depend on abstractions, not concrete implementations
- **Injection Patterns**: Prefer constructor injection over field injection
- **Repository Pattern**: Abstract data access behind interfaces

### Performance & Security

#### Performance Impact Analysis
- **Big O Complexity**: Explain algorithmic complexity changes
- **Database Queries**: Analyze N+1 query problems and query optimization
- **Memory Usage**: Consider object creation, collections sizing, and garbage collection
- **Lazy Loading**: Evaluate Hibernate lazy vs eager loading implications

#### Security Review
- **Input Validation**: Ensure all user inputs are properly validated and sanitized
- **SQL Injection**: Use parameterized queries and avoid string concatenation
- **Data Exposure**: Review what data is exposed in logs, error messages, and APIs
- **Access Control**: Verify authorization checks are in place where needed
- **Sensitive Data**: Never log passwords, tokens, or other secrets

#### Resource Management
- **Try-with-resources**: Use for database connections, file handles, streams
- **Connection Pooling**: Ensure proper Hibernate session management
- **Memory Leaks**: Watch for static collections, listeners not removed, unclosed resources

### Testing Strategy

#### Test Impact Assessment
- **Unit Test Coverage**: Identify which tests need updates after changes
- **Integration Tests**: Especially critical for Hibernate/JPA repository changes
- **Mock Boundaries**: Determine what should be mocked vs tested with real dependencies
- **Test Data Management**: Consider test database state and cleanup

#### New Test Requirements
- **Boundary Conditions**: Test edge cases, null inputs, empty collections
- **Error Scenarios**: Test exception handling and error conditions
- **Performance Tests**: For changes that might affect performance
- **Security Tests**: For authentication, authorization, and input validation changes

#### Integration Test Considerations
- **Database Transactions**: Ensure proper rollback in test scenarios
- **Test Isolation**: Each test should be independent and repeatable
- **Environment Configuration**: Test with realistic data volumes when relevant

### Documentation & Maintainability

#### Code Comments Policy
- **When to Comment**: Complex business logic, non-obvious algorithms, workarounds
- **When NOT to Comment**: Don't comment what the code obviously does
- **Javadoc Standards**: Use for public APIs, include @param, @return, @throws
- **TODO/FIXME**: Include tickets or deadlines for temporary solutions

#### Breaking Changes
- **API Compatibility**: Flag any changes to public method signatures
- **Database Schema**: Highlight any entity or migration impacts
- **Configuration Changes**: Note any required updates to properties files
- **Deprecation Strategy**: Use @Deprecated with replacement guidance

#### Future-Proofing
- **Java Version Compatibility**: Consider impact of language feature usage
- **Dependency Updates**: Design to be resilient to minor version updates
- **Extensibility**: Leave extension points for likely future requirements

### Dependency Management

#### Version Compatibility
- **Semantic Versioning**: Understand major/minor/patch version implications
- **Spring Boot/Hibernate**: Be aware of compatibility matrices
- **Java 21 Features**: Leverage appropriate modern language features

#### Transitive Dependencies
- **Dependency Conflicts**: Watch for version conflicts in Maven
- **Classpath Issues**: Consider library conflicts and exclusions
- **Security Vulnerabilities**: Be aware of vulnerable dependency versions

#### Build Tool Integration
- **Maven Lifecycle**: Ensure changes work with all relevant goals
- **IDE Compatibility**: Consider IntelliJ IDEA and VS Code integration
- **CI/CD Pipeline**: Think about automated build and deployment impacts

### Educational Considerations

#### Learning Objectives
- **Design Patterns**: Demonstrate Gang of Four patterns appropriately
- **Best Practices**: Show modern Java idioms and conventions
- **Architecture Concepts**: Illustrate layered architecture, separation of concerns
- **Enterprise Patterns**: Repository, Service Layer, Data Transfer Objects

#### Progressive Complexity
- **Scaffold Learning**: Start with simple implementations, add complexity gradually
- **Multiple Approaches**: Show different ways to solve similar problems
- **Real-World Examples**: Use patterns that mirror production scenarios
- **Avoid Over-Engineering**: Don't add complexity just to show off features

#### Code Examples Across Files
- **Consistency**: Use similar patterns for similar problems
- **Variation**: Show different approaches where educationally valuable
- **Evolution**: Demonstrate how simple code can be refactored to more sophisticated solutions

### Error Handling & Debugging

#### Error Message Quality
- **User-Friendly Messages**: Clear, actionable error descriptions
- **Developer Context**: Include enough detail for debugging
- **Internationalization**: Consider message externalization for larger applications
- **Log Levels**: Use appropriate levels (ERROR, WARN, INFO, DEBUG)

#### Debugging Support
- **Meaningful Variable Names**: Aid in debugger inspection
- **Intermediate Variables**: Sometimes break complex expressions for clarity
- **Logging Strategy**: Log important state changes and decision points
- **Stack Trace Preservation**: Don't swallow exceptions without proper handling

#### Rollback Strategy
- **Database Migrations**: Plan for rollback scenarios
- **Configuration Changes**: Document how to revert settings
- **Feature Flags**: Consider toggles for larger changes
- **Backup Plans**: Know how to quickly revert problematic changes

### Code Review Guidelines

#### Before Proposing Changes
- **Self-Review**: Walk through the change as if reviewing someone else's code
- **Testing**: Verify all relevant test scenarios pass
- **Documentation**: Update any affected documentation
- **Performance**: Consider the performance implications

#### Review Criteria
- **Functionality**: Does it solve the intended problem?
- **Design**: Does it follow SOLID principles and good OOP design?
- **Security**: Are there any security implications?
- **Performance**: Will it perform acceptably under load?
- **Maintainability**: Will future developers understand and modify this easily?

This comprehensive approach ensures code changes are not only functional but also maintainable, secure, and educational.