package com.sherylily.lifease.daily.domain

import com.sherylily.lifease.common.DailyTaskForDayId
import java.time.LocalDate

data class DailyTaskForDay(
    val id: DailyTaskForDayId = DailyTaskForDayId.create(),
    val task: DailyTask,
    val date: LocalDate,
    private val completed: Boolean = false
) {
    fun toggle(): DailyTaskForDay {
        return this.copy(completed = !completed)
    }

    fun isCompleted(): Boolean {
        return completed
    }
}