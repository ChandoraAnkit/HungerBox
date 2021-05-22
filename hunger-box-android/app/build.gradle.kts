plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
}


android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")

    defaultConfig {
        applicationId = "com.androidy.hungerbox"
        minSdkVersion(18)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"

        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug"){
            applicationIdSuffix = ".debug"
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility =JavaVersion.VERSION_1_8
        targetCompatibility =JavaVersion.VERSION_1_8
    }

    kapt {
        generateStubs = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    dataBinding {
        isEnabled = true
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(GeneralDependencies.CORE_KTX)
    implementation(GeneralDependencies.KOTLIN_JDK)
    implementation(GeneralDependencies.MULTI_DEX)

    implementation(GeneralDependencies.APP_COMPAT)
    implementation(GeneralDependencies.VECTOR_DRAWABLE)
    implementation(GeneralDependencies.MATERIAL_DESIGN)
    implementation(GeneralDependencies.CONSTRAINT_LAYOUT)
    implementation(GeneralDependencies.LIFECYCLE_EXT)

    implementation(GeneralDependencies.NAV_FRAG)
    implementation(GeneralDependencies.NAV_FRAG_KTX)
    implementation(GeneralDependencies.NAV_UI)
    implementation(GeneralDependencies.NAV_UI_KTX)

    implementation(GeneralDependencies.DAGGER)
    implementation(GeneralDependencies.DAGGER_ANDROID)
    kapt(KaptDependencies.DAGGER_COMPILER)
    kapt(KaptDependencies.DAGGER_PROCESSOR)

    implementation(GeneralDependencies.FIREBASE_AUTH)
    implementation(GeneralDependencies.FIRESTORE)

    implementation(GeneralDependencies.TIMBER)
    implementation(GeneralDependencies.COROUTINE_EXT)

    testImplementation(TestDependencies.JUNIT)
    testImplementation(TestDependencies.JUNIT_EXT)
    testImplementation(TestDependencies.ESPRESSO)

}
