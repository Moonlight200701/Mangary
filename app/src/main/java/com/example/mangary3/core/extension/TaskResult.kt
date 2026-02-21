package com.example.mangary3.core.extension

sealed class TaskResult<T> {
    data class Success<T>(val data: T) : TaskResult<T>()
    data class Error<T>(val e: Throwable) : TaskResult<T>()
}

inline fun <T> doAsTaskResult(block: () -> T): TaskResult<T> {
    return try {
        TaskResult.Success(block())
    } catch (e: Throwable) {
        TaskResult.Error(e)
    }
}