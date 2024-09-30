package com.sherylily.lifease.daily.domain

import com.sherylily.lifease.common.DailyTaskId
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class DailyTaskTest {
    @Test
    fun `should instantiate a daily task`() {
        val name = "Médicaments"
        val icon = "pills"
        val task = DailyTask(DailyTaskId.create(), name, icon, 0)

        task.name shouldBe name
    }
}