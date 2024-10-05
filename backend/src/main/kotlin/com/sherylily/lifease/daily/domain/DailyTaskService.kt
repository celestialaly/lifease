package com.sherylily.lifease.daily.domain

import com.sherylily.lifease.common.DailyTaskId
import com.sherylily.lifease.daily.infrastructure.secondary.DailyTaskWriteRepository
import java.util.UUID

interface DailyTaskService {
    fun getDailyTask(dailyTaskId: UUID): DailyTask?
    fun getDailyTasks(): List<DailyTask>
    fun addDailyTask(task: DailyTask)
}

class DomainDailyTaskService(
    private val repository: DailyTaskWriteRepository
): DailyTaskService {
    override fun getDailyTask(dailyTaskId: UUID): DailyTask? {
        return repository.find(DailyTaskId(dailyTaskId))
    }

    override fun getDailyTasks(): List<DailyTask> {
        return repository.findAll()
    }

    override fun addDailyTask(task: DailyTask) {
        repository.save(task)
    }
}
