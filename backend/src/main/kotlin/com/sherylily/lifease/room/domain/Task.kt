package com.sherylily.lifease.room.domain

import com.sherylily.lifease.common.TaskId

data class Task(
    val id: TaskId = TaskId.create(),
    val name: String,
    val completed: Boolean = false,
    val priority: Int = 1,
) {
    fun completeTask(): Task {
        return this.copy(completed = true)
    }
}