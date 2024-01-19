plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.ipca_project.musenex"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.ipca_project.musenex"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    // Add the dependency for the Firebase SDK for Google Analytics
    implementation("com.google.firebase:firebase-analytics")
    // Declare the dependency for the Cloud Firestore library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-auth")
    //Imports for discovery
    implementation ("me.relex:circleindicator:2.1.6")
    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    implementation("androidx.gridlayout:gridlayout:1.0.0")
    implementation("com.squareup.picasso:picasso:2.8")
    implementation("io.github.glailton.expandabletextview:expandabletextview:1.0.4")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation ("com.google.android.gms:play-services-maps:17.0.1")
    implementation ("org.osmdroid:osmdroid-android:6.1.14")
    implementation ("androidx.preference:preference-ktx:1.1.1")
    implementation ("com.google.android.exoplayer:exoplayer:2.18.0")
    implementation ("com.google.android.exoplayer:exoplayer-ui:2.18.0")

}