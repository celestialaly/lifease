package com.sherylily.lifease.daily.domain

import com.sherylily.lifease.common.DailyTaskForDayId
import com.sherylily.lifease.common.DailyTaskId
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import kotlin.test.Test

class DailyTaskForDayTest {
    private fun create(): DailyTaskForDay {
        val name = "Médicaments"
        val icon = "pills"
        val task = DailyTask(DailyTaskId.create(), name, icon, 0)

        val now = LocalDate.now()
        val taskForDay = DailyTaskForDay(DailyTaskForDayId.create(), task, now)

        return taskForDay
    }
    @Test
    fun `should instantiate a daily task for a specific day`() {
        val name = "Médicaments"
        val icon = "pills"
        val task = DailyTask(DailyTaskId.create(), name, icon, 0)

        val date = LocalDate.now()
        val taskForDay = DailyTaskForDay(DailyTaskForDayId.create(), task, date)
        taskForDay.task shouldBe task
        taskForDay.date shouldBe date
    }

    @Test
    fun `should complete daily task`() {
        val taskForDay = create()

        taskForDay.toggle()
        taskForDay.isCompleted() shouldBe true
    }
}