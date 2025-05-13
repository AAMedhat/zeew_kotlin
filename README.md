# Zeew - Food Delivery App

## Overview
Zeew is a modern food delivery application built with Android (Kotlin) that provides a seamless food ordering experience. The app features a beautiful, intuitive interface with smooth animations and a responsive design that works across different screen sizes.

## Design
The app's UI/UX design can be found in Figma:
[Zeew Design in Figma](https://www.figma.com/design/nlGwUBWVo6lqH3jPWdboqY/Zeew?node-id=3-151&t=lKntV1DSx6CwzB1i-1)

## Project Structure and Components

### Main Activity (`MainActivity.kt`)
The main screen of the application that handles:
- Restaurant information display
- Category navigation
- Menu item listing
- Cart management
- Scroll animations and transitions

Key functions:
- `setupRestaurantInfo()`: Initializes restaurant details including header image, logo, and basic information
- `setupCategories()`: Creates and manages the category navigation chips
- `setupRecyclerView()`: Configures the menu items list/grid view with proper layouts
- `setupViewCartButton()`: Manages the cart UI and functionality
- `setupAppBarTransition()`: Handles the dynamic color transitions during scrolling

### Models

#### MenuItem (`model/MenuItem.kt`)
Data class representing a menu item with properties:
- `id`: Unique identifier
- `name`: Item name
- `description`: Item description
- `price`: Price in AED
- `imageResId`: Resource ID for item image
- `isSpicy`: Spiciness indicator
- `category`: Category name

#### Category (`model/Category.kt`)
Data class for menu categories with properties:
- `id`: Unique identifier
- `name`: Category name
- `icon`: Optional icon resource
- `description`: Optional category description

### Adapters

#### MenuItemAdapter (`adapter/MenuItemAdapter.kt`)
RecyclerView adapter for displaying menu items with features:
- Dynamic category headers
- Grid/List layout support
- Click handling for cart operations
- Responsive design support

Key functions:
- `onCreateViewHolder()`: Creates view holders for menu items
- `onBindViewHolder()`: Binds menu item data to views
- `updateItems()`: Updates the displayed menu items
- `getItems()`: Retrieves current menu items

### Data Management

#### RestaurantData (`data/RestaurantData.kt`)
Singleton object managing static data including:
- Menu categories
- Menu items
- Restaurant information

Data categories:
- Restaurant details (name, description, rating)
- Delivery information (fee, time)
- Menu categories and items
- Resource IDs for images

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
│   │   │   ├── adapter/           # RecyclerView adapters
│   │   │   │   └── MenuItemAdapter.kt
│   │   │   ├── data/             # Data management
│   │   │   │   └── RestaurantData.kt
│   │   │   ├── model/            # Data models
│   │   │   │   ├── Category.kt
│   │   │   │   └── MenuItem.kt
│   │   │   └── MainActivity.kt    # Main UI and logic
│   │   └── res/
│   │       ├── layout/           # XML layouts
│   │       ├── values/           # Resources
│   │       └── drawable/         # Images
└── build.gradle                  # Project configuration
```

## Features
- [x] Dynamic header with parallax effect
- [x] Category navigation with indicators
- [x] Menu item display with images and details
- [x] Cart functionality
- [x] Responsive design
- [x] Tablet support
- [x] Edge-to-edge design

## Implementation Details

### UI Components
- **Header Section**: Implements parallax scrolling effect with dynamic color transitions
- **Category Navigation**: Horizontal scrollable chips with selection indicators
- **Menu Items**: Card-based design with support for both list and grid layouts
- **Cart Button**: Dynamic updates with item count and total price
- **Toolbar**: Transparent to solid transition based on scroll position

### Responsive Design
- Adapts to different screen sizes and orientations
- Grid layout for tablets in landscape mode
- Dynamic spacing and sizing based on screen dimensions
- Edge-to-edge design with proper system bar handling

### Performance Considerations
- Efficient RecyclerView implementation with view holders
- Smooth scrolling and animations
- Proper handling of configuration changes
- Optimized layout hierarchy

## Contributing
Feel free to submit issues and enhancement requests.

## License
[Add your license here] 