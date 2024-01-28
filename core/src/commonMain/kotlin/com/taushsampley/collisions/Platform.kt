package com.taushsampley.collisions

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform