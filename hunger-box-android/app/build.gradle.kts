plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    kotlin(BuildPlugins.ANDROID)
    kotlin(BuildPlugins.ANDROID_EXTENSIONS)
    kotlin(BuildPlugins.KAPT)
    id(BuildPlugins.NAVIGATION_SAFE_ARGS)
    id(BuildPlugins.GOOGLE_PLAY_SERVICE)
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)
    buildToolsVersion(BuildAndroidConfig.BUILD_TOOL_VERSION)

    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)

        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        multiDexEnabled = BuildAndroidConfig.MULTIDEX_ENABLE
        vectorDrawables.useSupportLibrary = BuildAndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES

        testInstrumentationRunner = BuildAndroidConfig.ANDROID_TEST_JUNIT_RUNNER
    }

    buildTypes {
        getByName(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
        }
        getByName(BuildType.RELEASE) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    dynamicFeatures = mutableSetOf(
        BuildModules.Features.AUTH,
        BuildModules.Features.FEEDS,
        BuildModules.Features.ADD_RECIPE,
        BuildModules.Features.PROFILE
    )

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(BuildModules.CORE))
    implementation(project(BuildModules.COMMON_UI))
    implementation(project(BuildModules.NAVIGATION))


    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.MULTI_DEX)

    implementation(Dependencies.VECTOR_DRAWABLE)
    implementation(Dependencies.MATERIAL_DESIGN)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.LIFECYCLE_EXT)

    implementation(Dependencies.NAVIGATION_FRAG)
    implementation(Dependencies.NAVIGATION_FRAG_KTX)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.NAVIGATION_UI_KTX)
    implementation(Dependencies.NAVIGATION_DYNAMIC_FEATURE_FRAG)


    implementation(Dependencies.DAGGER)
    kapt(AnnotationProcessorDependencies.DAGGER_COMPILER)
    kapt(AnnotationProcessorDependencies.DAGGER_PROCESSOR)

    implementation(DebugDependencies.TIMBER)
    implementation(Dependencies.COROUTINE_EXT)

    implementation(Dependencies.FIREBASE_AUTH)

    implementation(Dependencies.VECTOR_DRAWABLE)
    implementation(Dependencies.MATERIAL_DESIGN)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.LIFECYCLE_EXT)

    implementation(Dependencies.NAVIGATION_FRAG)
    implementation(Dependencies.NAVIGATION_FRAG_KTX)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.NAVIGATION_UI_KTX)


    implementation(Dependencies.DAGGER)
    kapt(AnnotationProcessorDependencies.DAGGER_COMPILER)
    kapt(AnnotationProcessorDependencies.DAGGER_PROCESSOR)

    implementation(DebugDependencies.TIMBER)
    implementation(Dependencies.COROUTINE_EXT)

    testImplementation(TestDependencies.JUNIT)
    testImplementation(TestDependencies.JUNIT_EXT)
    testImplementation(TestDependencies.ESPRESSO)

}
