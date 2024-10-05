package com.sherylily.lifease.daily.fixture

import com.sherylily.lifease.daily.domain.DailyTask
import com.sherylily.lifease.daily.infrastructure.secondary.DailyTaskWriteRepository

class DailyTaskFixture(private val repository: DailyTaskWriteRepository) {
    val dailyTasks = listOf(
        DailyTask(name = "Médicaments", icon = "pills", order = 0),
        DailyTask(name = "Lumino", icon = "light", order = 1),
    )

    fun generate() {
        dailyTasks.forEach {
            repository.save(it)
        }
    }
}