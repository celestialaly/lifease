package com.sherylily.lifease.room.infrastructure.primary

import com.sherylily.lifease.room.domain.Task

data class TaskResource(private val task: Task) {
    val taskId = task.id.toUUID()
    val name = task.name
    val priority = task.priority
}
