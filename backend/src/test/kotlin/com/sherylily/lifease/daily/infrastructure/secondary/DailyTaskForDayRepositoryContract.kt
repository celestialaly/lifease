package com.sherylily.lifease.daily.infrastructure.secondary

import com.sherylily.lifease.daily.domain.DailyTask
import com.sherylily.lifease.daily.domain.DailyTaskForDay
import com.sherylily.lifease.room.domain.Room
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.time.LocalDate

abstract class DailyTaskForDayRepositoryContract {
    protected abstract val repository: DailyTaskWriteRepository

    @Test
    fun `save daily task to database and retrieve it`() {
        // given
        val task = DailyTask(name = "Médicaments", icon = "pills")

        // when
        repository.save(task)

        // then
        repository.find(task.id) shouldBe task
    }

    @Test
    fun `complete daily task for a specific day and save to database`() {
        // given
        val task = DailyTask(name = "Médicaments", icon = "pills")
        repository.save(task)
        val taskForDay = DailyTaskForDay(task = task, date = LocalDate.now())

        // when
        repository.save(taskForDay.toggle())

        // then
        repository.find(task.id) shouldBe task
    }
}