package com.kaano8.sleeptracker.util

import java.math.BigInteger
import java.security.MessageDigest

object User {

    private const val username = "" // Enter your username

    fun getUserId(): String {
        val md = MessageDigest.getInstance(ALGORITHM)
        return BigInteger(1, md.digest(username.toByteArray())).toString(16).padStart(32, '0')
    }

    private const val ALGORITHM = "MD5"
}