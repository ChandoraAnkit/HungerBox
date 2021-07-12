plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
}

object PluginsVersion{
    const val BUILD_TOOL = "4.2.0"
    const val KOTLIN = "1.4.10"
    const val GOOGLE_PLAY = "4.3.3"
    const val NAV_SAGE_ARG = "2.3.5"
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

dependencies{
    implementation("com.android.tools.build:gradle:${PluginsVersion.BUILD_TOOL}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersion.KOTLIN}")
    implementation("com.google.gms:google-services:${PluginsVersion.GOOGLE_PLAY}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersion.NAV_SAGE_ARG}")
}