plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
}

kotlin {

    jvmToolchain(17)

    dependencies {
        implementation(compose.runtime)
    }
}

compose.desktop {
    application {
        mainClass = "com.taushsampley.collisions.MainKt"
    }
}
