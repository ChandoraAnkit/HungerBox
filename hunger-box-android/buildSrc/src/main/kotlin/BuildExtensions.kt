import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addTestDependencies(){
    add("testImplementation", TestDependencies.JUNIT)
    add("testImplementation", TestDependencies.JUNIT_EXT)
    add("testImplementation", TestDependencies.ESPRESSO)
}