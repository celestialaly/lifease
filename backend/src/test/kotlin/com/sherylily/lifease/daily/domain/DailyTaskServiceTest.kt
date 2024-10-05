package com.sherylily.lifease.daily.domain

import com.sherylily.lifease.daily.fixture.DailyTaskFixture
import com.sherylily.lifease.daily.infrastructure.secondary.DailyTaskInMemoryRepository
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class DailyTaskServiceTest {
    private val repository = DailyTaskInMemoryRepository()
    private val dailyTaskService = DomainDailyTaskService(repository)
    private val dailyTaskFixture = DailyTaskFixture(repository)

    @BeforeEach
    fun loadFixtures() {
        dailyTaskFixture.generate()
    }

    @Test
    fun `should retrieve daily tasks`() {
        val tasks = dailyTaskService.getDailyTasks()

        tasks.size shouldBeExactly dailyTaskFixture.dailyTasks.size
    }

    @Test
    fun `should add daily task`() {
        // when
        val dailyTask = DailyTask(name = "Test", icon = "test")
        dailyTaskService.addDailyTask(dailyTask)

        // given
        val tasks = dailyTaskService.getDailyTasks()

        // then
        tasks.last() shouldBe dailyTask
    }
}