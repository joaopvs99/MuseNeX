buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
<<<<<<< Updated upstream
=======
        classpath("com.android.tools.build:gradle:7.2.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.0")
>>>>>>> Stashed changes
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
}