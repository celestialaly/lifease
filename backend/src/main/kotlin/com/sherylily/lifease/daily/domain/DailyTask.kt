package com.sherylily.lifease.daily.domain

import com.sherylily.lifease.common.DailyTaskId

data class DailyTask(
    val id: DailyTaskId = DailyTaskId.create(),
    val name: String,
    val icon: String,
    val order: Int = 0
) {
}