package com.app.firebase

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform