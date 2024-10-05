package com.sherylily.lifease.daily.domain

import com.sherylily.lifease.common.DailyTaskId
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import kotlin.test.Test

class DailyTaskTest {
    @Test
    fun `should instantiate a daily task`() {
        val name = "Médicaments"
        val icon = "pills"
        val task = DailyTask(DailyTaskId.create(), name, icon, 0)

        task.name shouldBe name
    }

    @Test
    fun `should instantiate a daily task & complete it for a specific day`() {
        // given
        val name = "Médicaments"
        val icon = "pills"
        val task = DailyTask(DailyTaskId.create(), name, icon, 0)

        // when
        task.completeFor(LocalDate.now())

        // then
        task.isCompleted(LocalDate.now()) shouldBe true
    }
}