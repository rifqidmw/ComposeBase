# Android Jetpack Compose Base Project

This project serves as a ready-to-use base for developing Android applications using Jetpack Compose. It follows MVVM architecture and uses Koin for dependency injection.

## Project Description

This base project provides a solid foundation for building Android applications with the following key features:

- Jetpack Compose for UI
- MVVM (Model-View-ViewModel) architecture
- Koin for dependency injection
- Kotlin coroutines for asynchronous programming
- Clean and scalable project structure

## Technologies & Libraries

- [Kotlin](https://kotlinlang.org/) - Primary programming language
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern toolkit for building native Android UI
- [Koin](https://insert-koin.io/) - Pragmatic lightweight dependency injection framework
- [Retrofit](https://square.github.io/retrofit/) - Type-safe HTTP client for Android and Java
- [OkHttp](https://square.github.io/okhttp/) - HTTP client that's efficient by default
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - For asynchronous and non-blocking programming
- [Kotlin Flow](https://kotlinlang.org/docs/flow.html) - For reactive programming
- [Coil](https://coil-kt.github.io/coil/) - Image loading library for Android backed by Kotlin Coroutines

## Setup
### Step 1. Add the JitPack repository to your build file ###

```gradle
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```

### Add it in your root build.gradle at the end of repositories: ###

```gradle
maven(url = "https://jitpack.io")
```

### Step 2. Add the dependency ###

```gradle
dependencies {
	        implementation 'com.github.wlprojt:dimage:Tag'
	}
```

### other ###

```gradle
implementation ("com.github.wlprojt:dimage:1.0")
```

## Project Structure

```
app/
├── src/
│   └── main/
│       ├── java/com/aigs/base/
│       │   ├── common/
│       │   ├── data/
│       │   │   ├── local/
│       │   │   ├── model/
│       │   │   ├── remote/
│       │   │   └── repository/
│       │   ├── di/
│       │   ├── domain/
│       │   │   └── usecase/
│       │   ├── ui/
│       │   │   ├── components/
│       │   │   ├── navigations/
│       │   │   ├── screens/
│       │   │   │   └── home/
│       │   │   │       └── section/
│       │   │   └── screens/
│       │   ├── utils/
│       │   ├── App.kt
│       │   └── MainActivity.kt
│       ├── res/
│       │   └── assets/
│       └── AndroidManifest.xml
└── build.gradle.kts
```

## Coding Conventions

### Naming Conventions

1. Assets
   - Separated by underscore symbol ➜ e.g., `ic_edit.svg`
   - Placed in `res/{type}/`

2. Functions
   - Use `camelCase` for regular functions
   - Use `PascalCase` for composable functions

3. Files/Folders/Classes
   - Use `PascalCase` for class names
   - No need for suffix `Screen/View` for folder naming
   - For folders with two words, write all in lowercase with no separators ➜ e.g., `createaccount`

4. Screens
   - Add suffix `View` ➜ e.g., `LoginView`
   - Place the file according to the Design structure, but separate if it's a reusable screen

5. Components
   - Placed in `ui/components`
   - For file names, describe the component's purpose directly without adding any suffix/prefix ➜ e.g., `BaseTextField`

6. Sections
   - Add suffix `Section` ➜ e.g., `HomeContentSection`
   - Placed in `{featureFolder}/section` ➜ e.g., `home/section`

7. Navigation
   - Define route names as constants in a Route object for use in composable functions like NavHost. Avoid hardcoded route strings throughout the app.
   - Use routes such as Route.ONBOARDING, Route.LOGIN within the AppNavigation() function.

## Versioning

We use Semantic Versioning (SemVer) for version numbers: MAJOR.MINOR.PATCH

- PATCH: Bug fixes, refactoring, or enhancements (increment by 1 for all changes in a day)
- MINOR: New feature additions (increment by the number of features added)
- MAJOR: Revamps or breaking changes from the previous version

Example changelog entry:
```
[0.2.1]-2024-09-22

Added
- Feat: Login View
- Feat: Register View

Changed
- Refactor: Break Home View into Sections
```

## Commit Messages

Use the following prefixes for commit messages:

- `Chore`: Changes not directly related to code (e.g., configuration updates, documentation)
- `Fix`: Bug fixes or issue resolutions
- `Feat`: New feature or functionality additions
- `Refactor`: Code changes that don't alter external functionality
- `Docs`: Documentation changes
- `Test`: Changes related to testing

## Merge Request Process

1. Before initiating a merge:
   - Ensure your feature branch is up to date with the base branch
   - Resolve any conflicts locally

2. Opening a Merge Request:
   - Set yourself as the Assignee
   - Set the Lead or Tech Lead as the Reviewer
   - Check "Squash commits when merge request is accepted" option

3. Code Review:
   - Submit all merge requests through version control platform for review
   - At least one team member must review and approve the merge request
   - Incorporate or discuss feedback as necessary

4. Merging:
   - Once approved, the reviewer merges the feature branch into the base branch
   - Delete the feature branch from the remote repository after merging

Remember to keep your MR/PR focused on a single feature or bug fix to make the review process easier and more efficient.
