package com.taushsampley.collisions

class JvmPlatform: Platform {
    override val name: String get() = "JVM"
}

actual fun getPlatform(): Platform = JvmPlatform()
