package com.sherylily.lifease.room.domain

import com.sherylily.lifease.common.RoomId
import com.sherylily.lifease.common.TaskId

data class Room(
    val id: RoomId = RoomId.create(),
    val name: String,
    val tasks: MutableMap<TaskId, Task> = mutableMapOf(),
    val order: Int = 0
) {
    fun addTask(task: Task): Room {
        tasks[task.id] = task

        return this
    }

    fun completeTask(taskId: TaskId) {
        tasks[taskId] = tasks[taskId]?.completeTask() ?: throw IllegalArgumentException("Task $taskId not found")
    }

    fun activeTasks(): Map<TaskId, Task> {
        return tasks.filterValues { !it.completed }
    }
}
