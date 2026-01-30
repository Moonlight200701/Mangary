package com.example.mangary3.core

import timber.log.Timber

fun Timber.Tree.logError(className: String, throwable: Throwable) {
    Timber.tag(className).e(throwable)
}

fun logError(className: String, throwable: Throwable) {
    Timber.tag(className).e(throwable)
}