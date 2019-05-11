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

## Installation
### Requirements
To run the game you need an Android device running minimum API level: 19 Android 4.4(KITKAT)

### Easy installation
Download the game to your Android device from the following [url](https://drive.google.com/open?id=19u5-Zs2CV6bi1L_OwWxzOoQV09W-HCze)

### Development installation
#### Game
- Clone the repository
```console
foo@bar:~$ git clone https://github.com/battleofthebases/game.git
```
- Download and install the latest version of [Android Studio](https://developer.android.com/studio/?gclid=CjwKCAjw5dnmBRACEiwAmMYGOflSlfAQpt9Oq52CFh7S1pG1E8d93Q8huF4q-1gWykTxmjh-hYvubBoC-zsQAvD_BwE)
- Open the project files through the setup wizard or by using the navigation bar in Android Studio
- Press *Ctrl + R* on Mac or *Shift + F10* on Windows/Linux to run the application
- Choose your connected device if you have one connected through the USB interface. You can also use a virtual device.
- Press *OK* to build and run the game on the chosen device
