package com.example.borutoapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {

    suspend fun savedOnBoardingState(completed:Boolean)
    fun readOnBoardingState(): Flow<Boolean>
}