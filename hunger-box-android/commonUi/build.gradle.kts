plugins {
    id("commons.android-library")
}


dependencies{

    implementation(project(BuildModules.CORE))
    implementation(project(BuildModules.NAVIGATION))

    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APP_COMPAT)

    implementation(Dependencies.FIREBASE_AUTH)

    implementation(Dependencies.MATERIAL_DESIGN)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.LIFECYCLE_EXT)

    implementation(Dependencies.NAVIGATION_FRAG)
    implementation(Dependencies.NAVIGATION_FRAG_KTX)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.NAVIGATION_UI_KTX)

}