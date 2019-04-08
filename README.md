# Battle of the Bases
[![Build Status](https://travis-ci.com/battleofthebases/game.svg?branch=master)](https://travis-ci.com/battleofthebases/game)
[![codecov](https://codecov.io/gh/battleofthebases/game/branch/master/graph/badge.svg)](https://codecov.io/gh/battleofthebases/game)

NTNU Course [TDT4240 Software Architecture](https://www.ntnu.edu/studies/courses/TDT4240#tab=omEmnet) project repository for group 26.

## Content
* [Description](#description)
* [Target](#target)
* [Development Setup](#development-setup)
* [Game Instructions](#game-instructions)

## Description
The task is to create a Android Game, with focus on good architecture.

## Target
The target platform is Android, with API levels from 19 (Android 4.4 | KITKAT) to 28 (Android 9.0 | Pie).

## Development Setup
Clone the repository or open project from git in Android studio.
```
git clone https://github.com/battleofthebases/game.git
````

Open the "**game**" folder as a project in Android Studio.

### Code style`
1. 
```
cd codestyle
```
2.
```
./install.sh
```
3. Restart Android Studio
4. Select the code style scheme "grandcentrix" via Preferences --> Editor --> Code Style

#### Reformat on save
With 3 simple steps you can reorder and reformat your code automatically with ⌘ + S. That shortcut you are used to press constantly although you know Android Studio automatically saves all files for you . Give ⌘ + S a different meaning:

Make sure a Java source file has focus (or you can’t record all steps)
- Select Edit > Macros > Start Macro Recording
- Select Code > Optimize Imports
- Select Code > Reformat Code
- Select Code > Rearrange Code
- Select File > Save All
- Select Edit > Macros > Stop Macro Recording and give it a name (mine is OptimizeImportsReformatRearrangeSave)
Go to Preferences > Keymap
- Find the Macro section
- Add ⌘ + S shortcut for the new macro

### Testing

To run [tests](https://github.com/arturdm/jacoco-android-gradle-plugin)
```
./gradlew jacocoTestReport
```

To create test coverage report
```
./gradlew build jacocoTestReport
```


## Game Instructions
Press "Play" and follow the in-game instructions.