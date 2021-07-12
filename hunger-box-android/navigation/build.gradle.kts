plugins {
    id("commons.android-library")
    id("androidx.navigation.safeargs.kotlin")
}

dependencies{

    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APP_COMPAT)

    implementation(Dependencies.NAVIGATION_FRAG)
    implementation(Dependencies.NAVIGATION_FRAG_KTX)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.NAVIGATION_UI_KTX)
    implementation(Dependencies.NAVIGATION_DYNAMIC_FEATURE_FRAG)

}