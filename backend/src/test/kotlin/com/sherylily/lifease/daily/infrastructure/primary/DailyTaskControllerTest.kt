package com.sherylily.lifease.daily.infrastructure.primary

import com.sherylily.lifease.common.DailyTaskId
import com.sherylily.lifease.daily.domain.DomainDailyTaskService
import com.sherylily.lifease.daily.fixture.DailyTaskFixture
import com.sherylily.lifease.daily.infrastructure.secondary.DailyTaskInMemoryRepository
import com.sherylily.lifease.daily.infrastructure.secondary.DailyTaskRequest
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import java.time.LocalDate
import kotlin.test.Test

class DailyTaskControllerTest {
    private val repository = DailyTaskInMemoryRepository()
    private val service =  DomainDailyTaskService(repository)
    private val controller = DailyTaskController(repository, service)
    private val dailyTaskFixture = DailyTaskFixture(repository)

    @BeforeEach
    fun loadFixtures() {
        dailyTaskFixture.generate()
    }

    @Test
    fun `should retrieve list of daily tasks`() {
        val tasks = controller.list()

        tasks.size shouldBeExactly dailyTaskFixture.dailyTasks.size
    }

    @Test
    fun `should create a daily task`() {
        // given
        val request = DailyTaskRequest(name = "Médicaments", icon = "pills", order = 0)

        // when
        val taskResource = controller.createDailyTask(request)

        // then
        repository.find(DailyTaskId(taskResource.dailyTaskId))!!.name shouldBe request.name
    }

    @Test
    fun `should toggle daily task completion`() {
        // given
        val task = controller.list().first()

        // when
        controller.toggleDailyTaskCompletion(task.dailyTaskId)

        // then
        repository.find(DailyTaskId(task.dailyTaskId))!!.isCompleted(LocalDate.now()) shouldBe true

    }
}