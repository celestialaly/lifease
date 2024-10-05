package com.sherylily.lifease.daily.domain

import com.sherylily.lifease.common.DailyTaskId
import java.time.LocalDate

data class DailyTask(
    val id: DailyTaskId = DailyTaskId.create(),
    val name: String,
    val icon: String,
    val order: Int = 0,
    val completed: MutableList<LocalDate> = mutableListOf()
) {
    fun completeForToday() {
        completeFor(LocalDate.now())
    }

    fun completeFor(date: LocalDate) {
        if (!isCompleted(date)) {
            completed.add(date)
        }
    }

    fun toggleComplete(date: LocalDate) {
        when (isCompleted(date)) {
            true -> completed.remove(date)
            else -> completed.add(date)
        }
    }

    fun isCompleted(date: LocalDate = LocalDate.now()): Boolean {
        return completed.contains(date)
    }
}