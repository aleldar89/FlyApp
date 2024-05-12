plugins {
    alias(libs.plugins.tickets.android.library)
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
//    alias(libs.plugins.androidx.navigation.safeargs)
    id("androidx.navigation.safeargs")
    alias(libs.plugins.ksp)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.airtickets_feature"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":common-resources"))
    implementation(project(":airtickets-data"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.glide)
    ksp(libs.glide.ksp)
    implementation(libs.adapterdelegates4.kotlin.dsl.viewbinding)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}