package com.sherylily.lifease.daily.infrastructure.secondary

data class DailyTaskRequest(
    val name: String,
    val icon: String,
    val order: Int
)