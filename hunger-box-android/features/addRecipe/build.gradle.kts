plugins {
    id("commons.android-dynamic-feature")
}

dependencies{
    implementation(Dependencies.FIREBASE_AUTH)
    implementation(Dependencies.FIRESTORE)

    kapt ("com.android.databinding:compiler:3.1.4")
}
