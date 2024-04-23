plugins {
    id("com.android.library")
}
android {
    buildFeatures {
        viewBinding = true
    }
    namespace = "ru.marina.githubrepositoriesobserver_auth_api"

    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {


}