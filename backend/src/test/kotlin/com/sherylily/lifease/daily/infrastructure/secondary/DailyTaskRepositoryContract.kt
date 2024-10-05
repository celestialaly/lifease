package com.sherylily.lifease.daily.infrastructure.secondary

import com.sherylily.lifease.daily.domain.DailyTask
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.time.LocalDate

abstract class DailyTaskRepositoryContract {
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
    fun `save daily task completed for a specific day to database and retrieve it`() {
        // given
        val task = DailyTask(name = "Médicaments", icon = "pills")
        task.completeForToday()

        // when
        repository.save(task)

        // then
        val dbTask = repository.find(task.id)
        dbTask shouldBe task
        dbTask?.isCompleted(LocalDate.now()) shouldBe true
    }
}