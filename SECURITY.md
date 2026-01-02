# Security Policy

## Supported Versions

We provide security updates for the following versions of Compose-Settings:

| Version | Supported          |
| ------- | ------------------ |
| 2.x.x   | :white_check_mark: |
| < 2.0   | :x:                |

## Reporting a Vulnerability

We take the security of Compose-Settings seriously. If you discover a security vulnerability, please follow these steps:

### 1. Do Not Disclose Publicly

Please **do not** create a public GitHub issue for security vulnerabilities. Public disclosure could put users at risk.

### 2. Report Privately

Send an email to **bernatbor15@gmail.com** with the following information:

- **Description**: A clear description of the vulnerability
- **Impact**: What could an attacker do with this vulnerability?
- **Reproduction**: Step-by-step instructions to reproduce the issue
- **Affected Versions**: Which versions of the library are affected?
- **Proposed Fix**: If you have suggestions for fixing the issue (optional)
- **Your Contact**: How we can reach you for follow-up questions

### 3. Response Timeline

You can expect:

- **Initial Response**: Within 48 hours acknowledging receipt
- **Status Update**: Within 7 days with our assessment and planned actions
- **Resolution**: We aim to release a fix within 30 days for critical vulnerabilities

### 4. Coordinated Disclosure

We follow coordinated disclosure practices:

1. We'll work with you to understand and validate the issue
2. We'll develop and test a fix
3. We'll prepare a security advisory
4. We'll release the fix and publish the advisory
5. You'll be credited for the discovery (unless you prefer to remain anonymous)

## Security Best Practices

When using Compose-Settings in your application:

### Input Validation

If you're using settings components with user input, ensure you validate and sanitize data:

```kotlin
SettingsSwitch(
    state = userPreference,
    title = { Text(sanitizeInput(userTitle)) }, // Sanitize user-provided content
    onCheckedChange = { newValue ->
        // Validate before persisting
        if (isValidInput(newValue)) {
            savePreference(newValue)
        }
    }
)
```

### Data Storage

- Don't store sensitive data (passwords, tokens) in SharedPreferences without encryption
- Use Android Keystore or platform-specific secure storage for sensitive information
- Be cautious with `SettingsMenuLink` onClick handlers that navigate to sensitive screens

### Dependencies

- Keep Compose-Settings updated to the latest version
- Regularly update your Compose Multiplatform and Kotlin dependencies
- Monitor for security advisories in your dependency chain

## Known Security Considerations

### UI Rendering

This library is primarily a UI component library. It does not:

- Store or transmit data on its own
- Make network requests
- Access device permissions
- Perform cryptographic operations

Security is primarily the responsibility of the application using these components.

### Platform Security

The library respects platform security models:

- **Android**: Follows Android's security guidelines
- **iOS**: Follows iOS security best practices
- **Desktop**: Runs with JVM security manager constraints
- **Web**: Operates within browser security sandbox

## Security Updates

Security updates will be:

- Released as patch versions (e.g., 2.24.1)
- Documented in release notes
- Announced via GitHub Security Advisories
- Highlighted in the project README

## Questions?

If you have questions about security but haven't found a vulnerability, you can:

- Open a [GitHub Discussion](https://github.com/alorma/Compose-Settings/discussions)
- Email bernatbor15@gmail.com for sensitive questions

Thank you for helping keep Compose-Settings and its users safe!
