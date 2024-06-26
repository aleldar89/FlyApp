plugins {
    alias(libs.plugins.flyapp.android.library)
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.common_resources"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}