plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
}

kotlin {

    jvmToolchain(17)

    dependencies {
        implementation(project(":core"))

        implementation(compose.desktop.currentOs)
        implementation(compose.runtime)
    }
}

compose.desktop {
    application {
        mainClass = "com.taushsampley.collisions.MainKt"
    }
}
