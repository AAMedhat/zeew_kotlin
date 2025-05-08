# Zeew - Food Delivery App

## Overview
Zeew is a modern food delivery application built with Android (Kotlin) that provides a seamless food ordering experience. The app features a beautiful, intuitive interface with smooth animations and a responsive design that works across different screen sizes.

## Design
The app's UI/UX design can be found in Figma:
[Zeew Design in Figma](https://www.figma.com/design/nlGwUBWVo6lqH3jPWdboqY/Zeew?node-id=3-151&t=lKntV1DSx6CwzB1i-1)

### Implemented Screens
- **Store Page**: A fully functional restaurant detail page featuring:
  - Dynamic header image with parallax effect
  - Restaurant information (name, logo, rating, delivery details)
  - Category navigation with smooth scrolling
  - Menu items in a scrollable list/grid view
  - Cart functionality with real-time updates
  - Responsive layout supporting both phone and tablet layouts

## Technical Features
- Modern Android development with Kotlin
- Material Design 3 components
- Edge-to-edge design with transparent status bar
- Responsive layouts (supports both portrait and landscape orientations)
- Tablet support with grid layout in landscape mode
- Smooth animations and transitions
- Dynamic UI elements that respond to scroll events

## Requirements
- Android Studio Arctic Fox or newer
- Minimum SDK: 21 (Android 5.0)
- Target SDK: Latest stable version
- Kotlin version: 1.5.0 or newer

## Setup Instructions
1. Clone the repository
```bash
git clone [repository-url]
```

2. Open the project in Android Studio

3. Sync project with Gradle files

4. Run the app on an emulator or physical device

## Project Structure
```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/zeew/
│   │   │   ├── adapter/
│   │   │   ├── data/
│   │   │   ├── model/
│   │   │   └── MainActivity.kt
│   │   └── res/
│   │       ├── layout/
│   │       ├── values/
│   │       └── drawable/
└── build.gradle
```

## Features
- [x] Dynamic header with parallax effect
- [x] Category navigation with indicators
- [x] Menu item display with images and details
- [x] Cart functionality
- [x] Responsive design
- [x] Tablet support
- [x] Edge-to-edge design

## Contributing
Feel free to submit issues and enhancement requests.

## License
[Add your license here] 