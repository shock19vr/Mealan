# Mealan - Food Donation App

**Mealan** is an Android application that connects food donors with receivers (NGOs and welfare societies) to reduce food waste and help those in need. The app provides a platform where individuals can donate excess food and organizations can find available food donations in their area.

## 🍽️ Features

### For Donors
- **Food Posting**: Create listings for available food with details like food type, quantity (number of people it can serve), and pickup address
- **Extensive Food Database**: Choose from 150+ Indian food items including rice dishes, curries, dals, snacks, and sweets
- **Auto-complete Search**: Smart food selection with comma-separated multi-selection
- **Profile Management**: Manage personal information and view donation history

### For Receivers (NGOs/Welfare Societies)
- **Browse Donations**: View all available food donations in a list format
- **Contact Donors**: Access donor information to coordinate food pickup
- **Organization Registration**: Register as an NGO or welfare society with organization details

### General Features
- **User Authentication**: Secure login/registration system with Firebase
- **Remember Me**: Option to stay logged in across sessions
- **Dual User Types**: Separate interfaces for donors and receivers
- **Profile Pages**: Comprehensive user profile management
- **Help Section**: Built-in help and support functionality

## 🏗️ Technical Architecture

### Technology Stack
- **Platform**: Android (Java)
- **Backend**: Firebase Firestore (NoSQL database)
- **Authentication**: Firebase Authentication
- **UI Framework**: Android Material Design Components
- **Architecture**: Activity-based navigation with ViewBinding

### Key Components
- **MainActivity**: Splash screen with 3-second delay
- **LoginActivity**: User authentication with remember me functionality
- **RegisterActivity**: User registration with donor/receiver differentiation
- **DonorActivity**: Food posting interface for donors
- **ReceiverActivity**: Food browsing interface for receivers
- **UserProfilePage**: Profile management for all users
- **MyAdapter**: RecyclerView adapter for food listings

### Database Structure
The app uses Firebase Firestore with the following collections:
- **users**: User profiles with personal information and user type
- **listings**: Food donation posts with food details, address, and donor information

## 📱 App Flow

1. **Splash Screen** → **Login/Register**
2. **User Type Detection** → **Donor Home** or **Receiver Home**
3. **Donors**: Create food listings → Manage posts → Profile
4. **Receivers**: Browse available food → Contact donors → Profile

## 🛠️ Setup and Installation

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK API level 24 or higher
- Firebase project with Firestore enabled
- Google Services configuration file

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd Mealan
   ```

2. **Firebase Setup**
   - Create a Firebase project at [Firebase Console](https://console.firebase.google.com/)
   - Enable Firestore Database
   - Download `google-services.json` and place it in the `app/` directory
   - Ensure Firebase Authentication is configured

3. **Open in Android Studio**
   - Open the project in Android Studio
   - Sync Gradle files
   - Ensure all dependencies are resolved

4. **Build and Run**
   - Connect an Android device or start an emulator
   - Build and run the application

### Dependencies
```gradle
// Core Android libraries
implementation 'androidx.appcompat:appcompat:1.6.1'
implementation 'com.google.android.material:material:1.11.0'
implementation 'androidx.activity:activity:1.8.0'
implementation 'androidx.constraintlayout:constraintlayout:2.1.4'

// Firebase
implementation 'com.google.firebase:firebase-firestore:24.11.0'
implementation 'com.google.firebase:firebase-database:20.3.1'

// Lifecycle components
implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.6.1'
implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1'

// Testing
testImplementation 'junit:junit:4.13.2'
androidTestImplementation 'androidx.test.ext:junit:1.1.5'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
```

## 📋 App Configuration

### Minimum Requirements
- **Min SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **Compile SDK**: API 34

### Permissions
The app uses standard Android permissions for basic functionality. No special permissions are required.

## 🎨 UI/UX Features
- **Material Design**: Modern Android UI components
- **ViewBinding**: Type-safe view binding for better performance
- **Edge-to-Edge Display**: Full-screen experience with proper insets handling
- **Custom Fonts**: Preloaded fonts for enhanced typography
- **Responsive Layouts**: Optimized for different screen sizes

## 🔐 Security Features
- **Firebase Authentication**: Secure user authentication
- **Input Validation**: Email format validation and password confirmation
- **Data Encryption**: Firebase handles data encryption in transit and at rest
- **User Session Management**: Secure session handling with remember me functionality

## 🚀 Future Enhancements
- Push notifications for new food donations
- Location-based food discovery
- Rating and review system for donors/receivers
- Photo upload for food items
- Real-time chat between donors and receivers
- Analytics dashboard for organizations

## 🤝 Contributing
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License
This project is licensed under the MIT License - see the LICENSE file for details.

## 📞 Support
For support and questions, please contact the development team or create an issue in the repository.

## 🙏 Acknowledgments
- Firebase for providing robust backend services
- Material Design team for UI components
- The open-source community for various libraries and tools

---

**Made with ❤️ to reduce food waste and help communities**
