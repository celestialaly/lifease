package com.sherylily.lifease.daily.infrastructure.primary

import com.sherylily.lifease.daily.domain.DailyTask
import com.sherylily.lifease.daily.domain.DailyTaskService
import com.sherylily.lifease.daily.infrastructure.secondary.DailyTaskReadRepository
import com.sherylily.lifease.daily.infrastructure.secondary.DailyTaskRequest
import com.sherylily.lifease.daily.infrastructure.secondary.DailyTaskWriteRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.UUID


@RestController
@CrossOrigin
@RequestMapping("/dailyTask")
class DailyTaskController(
    private val repository: DailyTaskWriteRepository,
    private val service: DailyTaskService
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun list(): List<DailyTaskResource> {
        val tasks = service.getDailyTasks()

        return tasks.map { DailyTaskResource(it) }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createDailyTask(@RequestBody dailyTaskRequest: DailyTaskRequest): DailyTaskResource {
        val task = DailyTask(
            name = dailyTaskRequest.name,
            icon = dailyTaskRequest.icon,
            order = dailyTaskRequest.order
        )

        service.addDailyTask(task)

        return DailyTaskResource(task)
    }

    @PostMapping("/{dailyTaskId}/toggle")
    @ResponseStatus(HttpStatus.OK)
    fun toggleDailyTaskCompletion(@PathVariable("dailyTaskId") dailyTaskId: UUID): DailyTaskResource {
        val task = service.getDailyTask(dailyTaskId) ?: throw EntityNotFoundException("DailyTask $dailyTaskId not found")

        task.toggleComplete(LocalDate.now())
        repository.save(task)

        return DailyTaskResource(task)
    }
}