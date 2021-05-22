buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${PluginsVersion.BUILD_TOOL}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersion.KOTLIN}")
        classpath("com.google.gms:google-services:${PluginsVersion.GOOGLE_PLAY}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersion.NAV_SAGE_ARG}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}
