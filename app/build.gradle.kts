plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.appquiz"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.appquiz"
        minSdk = 26  // Tăng minSdk lên 26 ở đây
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}


dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


}

