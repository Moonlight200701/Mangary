package com.example.mangary3.domain.repository

import kotlinx.coroutines.flow.Flow

interface NetworkRepository {
    val networkStatus: Flow<Boolean>
}