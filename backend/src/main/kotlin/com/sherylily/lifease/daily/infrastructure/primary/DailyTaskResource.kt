package com.sherylily.lifease.daily.infrastructure.primary

import com.sherylily.lifease.daily.domain.DailyTask

data class DailyTaskResource(private val dailyTask: DailyTask) {
    val dailyTaskId = dailyTask.id.toUUID()
    val name = dailyTask.name
    val icon = dailyTask.icon
    val order = dailyTask.order
    val completed = dailyTask.completed
}