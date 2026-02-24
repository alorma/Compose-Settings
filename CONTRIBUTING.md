# Contributing to Compose-Settings

Thank you for your interest in contributing to Compose-Settings! This document provides guidelines and instructions for contributing.

## Code of Conduct

By participating in this project, you agree to abide by our [Code of Conduct](CODE_OF_CONDUCT.md).

## Getting Started

### Prerequisites

- **JDK**: Version 17 or higher
- **Android Studio**: Latest stable version (recommended) or IntelliJ IDEA
- **Xcode**: 14.0+ (for iOS development, macOS only)
- **Git**: For version control

### Setting Up the Development Environment

1. **Clone the repository**:
   ```bash
   git clone https://github.com/alorma/Compose-Settings.git
   cd Compose-Settings
   ```

2. **Open in IDE**:
   - Open the project in Android Studio or IntelliJ IDEA
   - Wait for Gradle sync to complete

3. **Build the project**:
   ```bash
   ./gradlew build
   ```

4. **Run tests**:
   ```bash
   ./gradlew allTests
   ```

## Project Structure

```
Compose-Settings/
├── ui-core/              # Foundation module (shared components)
├── ui-tiles/             # Core settings components
├── ui-tiles-extended/    # Advanced settings components
├── ui-tiles-expressive/  # Expressive Material 3 components
├── samples/              # Sample applications
│   ├── androidApp/       # Android demo
│   ├── desktopApp/       # Desktop (JVM) demo
│   ├── iosApp/          # iOS demo
│   └── webApp/          # Web (JS/WASM) demo
├── build-logic/          # Convention plugins
└── docs/                # Documentation and assets
```

See [ARCHITECTURE.md](ARCHITECTURE.md) for detailed architecture information.

## How to Contribute

### Reporting Bugs

1. Check if the bug has already been reported in [Issues](https://github.com/alorma/Compose-Settings/issues)
2. If not, create a new issue using the Bug Report template
3. Provide as much detail as possible:
   - Steps to reproduce
   - Expected behavior
   - Actual behavior
   - Platform (Android, iOS, Desktop, Web)
   - Library version
   - Code samples or screenshots

### Suggesting Features

1. Check if the feature has already been requested in [Issues](https://github.com/alorma/Compose-Settings/issues)
2. Create a new issue using the Feature Request template
3. Describe:
   - The problem you're trying to solve
   - Your proposed solution
   - Any alternatives you've considered
   - Example use cases

### Submitting Pull Requests

1. **Fork the repository** and create a new branch from `main`:
   ```bash
   git checkout -b feature/your-feature-name
   # or
   git checkout -b fix/your-bug-fix
   ```

2. **Make your changes**:
   - Follow the existing code style
   - Write clear, self-documenting code
   - Add comments where logic isn't obvious
   - Update documentation if needed

3. **Test your changes**:
   ```bash
   # Run all tests
   ./gradlew allTests

   # Run checks (includes tests + linting)
   ./gradlew check

   # Test on specific platforms
   ./gradlew desktopTest
   ```

4. **Commit your changes**:
   - Write clear, descriptive commit messages
   - Follow conventional commit format (optional but appreciated):
     ```
     feat: add dark mode support to SettingsSwitch
     fix: resolve crash on iOS when toggling checkbox
     docs: update README with new component examples
     ```

5. **Push to your fork**:
   ```bash
   git push origin feature/your-feature-name
   ```

6. **Create a Pull Request**:
   - Use the PR template
   - Link related issues
   - Provide a clear description of changes
   - Add screenshots/videos for UI changes
   - Ensure CI checks pass

## Development Guidelines

### Code Style

- **Kotlin**: Follow [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)
- **Compose**: Follow [Compose API guidelines](https://github.com/androidx/androidx/blob/androidx-main/compose/docs/compose-api-guidelines.md)
- **Formatting**: The project uses ktlint for code formatting
  ```bash
  # Check formatting
  ./gradlew ktlintCheck

  # Auto-format code
  ./gradlew ktlintFormat
  ```

### Adding New Components

When creating new settings components:

1. **Choose the appropriate module**:
   - `ui-tiles`: Core/basic components (checkbox, switch, menu link, etc.)
   - `ui-tiles-extended`: Advanced components requiring additional dependencies (slider, etc.)

2. **Use the scaffold pattern**:
   - Build on `SettingsTileScaffold` from `ui-core`
   - This ensures consistent layout and theming

3. **Follow the API pattern**:
   ```kotlin
   @Composable
   fun SettingsYourComponent(
       title: @Composable () -> Unit,
       modifier: Modifier = Modifier,
       subtitle: @Composable (() -> Unit)? = null,
       icon: @Composable (() -> Unit)? = null,
       enabled: Boolean = true,
       colors: ListItemColors = SettingsTileDefaults.colors(),
       // Component-specific parameters
   ) {
       // Implementation using SettingsTileScaffold
   }
   ```

4. **Respect LocalSettingsGroupEnabled**:
   ```kotlin
   val groupEnabled = LocalSettingsGroupEnabled.current
   val actualEnabled = enabled && groupEnabled
   ```

5. **Add to sample apps**:
   - Add examples to `samples/shared/`
   - Ensure it works across all platforms

6. **Document the component**:
   - Add KDoc comments
   - Update README.md with usage example
   - Add screenshots to `docs/art/`

### Platform-Specific Code

- Place platform-specific implementations in the appropriate source set:
  - `commonMain/` - Shared code
  - `androidMain/` - Android-specific
  - `iosMain/` - iOS-specific
  - `desktopMain/` - JVM desktop
  - `jsMain/` - JavaScript
  - `wasmJsMain/` - WebAssembly

### Testing

- Write tests for new functionality
- Ensure existing tests still pass
- Test on multiple platforms when possible
- Use descriptive test names

### Documentation

- Update README.md if adding new components
- Update ARCHITECTURE.md for architectural changes
- Add KDoc comments to public APIs
- Include code examples in documentation

## Running Sample Apps

### Android
```bash
./gradlew :samples:androidApp:assembleDebug
# Or open in Android Studio and run
```

### Desktop
```bash
./gradlew :samples:desktopApp:run
```

### iOS
```bash
# Open in Xcode
open samples/iosApp/iosApp.xcodeproj
# Or use command line
cd samples/iosApp
xcodebuild -project iosApp.xcodeproj -scheme iosApp
```

### Web (JavaScript/WASM)
```bash
./gradlew :samples:webApp:jsBrowserDevelopmentRun
# Or for WASM
./gradlew :samples:webApp:wasmJsBrowserDevelopmentRun
```

## Publishing (Maintainers Only)

See [PUBLISHING.md](PUBLISHING.md) for detailed publishing instructions.

## Questions?

- Open a [Discussion](https://github.com/alorma/Compose-Settings/discussions)
- Check existing [Issues](https://github.com/alorma/Compose-Settings/issues)
- Review the [Documentation](https://alorma.github.io/Compose-Settings/dokka)

## License

By contributing, you agree that your contributions will be licensed under the same license as the project (see [LICENSE](LICENSE)).