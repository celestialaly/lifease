package com.sherylily.lifease.daily.infrastructure.secondary

import com.sherylily.lifease.common.DailyTaskId
import com.sherylily.lifease.daily.domain.DailyTask
import java.util.*

interface DailyTaskReadRepository {
    fun find(taskId: DailyTaskId): DailyTask?
    fun findAll(): List<DailyTask>
}
interface DailyTaskWriteRepository: DailyTaskReadRepository {
    fun save(task: DailyTask)
}

class DailyTaskInMemoryRepository: DailyTaskWriteRepository {
    private val store: MutableMap<UUID, DailyTask> = mutableMapOf()

    override fun findAll(): List<DailyTask> {
        return store.values.toList()
    }

    override fun find(taskId: DailyTaskId): DailyTask? {
        return store[taskId.toUUID()]
    }

    override fun save(task: DailyTask) {
        store[task.id.toUUID()] = task
    }
}