package com.kaano8.sleeptracker.extensions

import android.view.View

fun View.enable() { isEnabled = true }

fun View.disable() { isEnabled = false }

fun View.visible() { visibility = View.VISIBLE }

fun View.gone() { visibility = View.GONE }
