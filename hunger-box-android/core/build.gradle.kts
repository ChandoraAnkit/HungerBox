plugins {
    id("commons.android-library")
}

dependencies{

    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.KOTLIN)

    implementation(Dependencies.APP_COMPAT)

    implementation(Dependencies.NAVIGATION_FRAG)
    implementation(Dependencies.NAVIGATION_FRAG_KTX)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.NAVIGATION_UI_KTX)

    implementation(Dependencies.FIREBASE_AUTH)
    implementation(Dependencies.FIRESTORE)
}