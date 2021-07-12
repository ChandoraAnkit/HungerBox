package commons

plugins{
    id("com.android.dynamic-feature")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}


android{

    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig{
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)
        vectorDrawables.useSupportLibrary = BuildAndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    dataBinding {
        isEnabled = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(project(BuildModules.APP))
    implementation(project(BuildModules.CORE))
    implementation(project(BuildModules.COMMON_UI))
    implementation(project(BuildModules.NAVIGATION))

    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.VECTOR_DRAWABLE)
    implementation(Dependencies.MATERIAL_DESIGN)
    implementation(Dependencies.CONSTRAINT_LAYOUT)

    implementation(Dependencies.LIFECYCLE_EXT)
    implementation(Dependencies.COROUTINE_EXT)

    implementation(Dependencies.NAVIGATION_FRAG)
    implementation(Dependencies.NAVIGATION_FRAG_KTX)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.NAVIGATION_UI_KTX)

    implementation(Dependencies.DAGGER)
    kapt(AnnotationProcessorDependencies.DAGGER_COMPILER)
    kapt(AnnotationProcessorDependencies.DAGGER_PROCESSOR)

    implementation(DebugDependencies.TIMBER)
}