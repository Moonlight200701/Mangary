package com.example.mangary3.core

import timber.log.Timber

fun Timber.Companion.logDebug(tag: String, message: String) =
    Timber.d("$tag: $message")

fun Timber.logError(className: String, throwable: Throwable) =
    Timber.e(throwable, "$className: ${throwable.message}")