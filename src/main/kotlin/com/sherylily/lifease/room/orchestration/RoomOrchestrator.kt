package com.sherylily.lifease.room.orchestration

import com.sherylily.lifease.common.RoomId
import com.sherylily.lifease.room.domain.Room
import com.sherylily.lifease.room.domain.Task
import com.sherylily.lifease.room.infrastructure.secondary.RoomWriteRepository
import org.springframework.stereotype.Service

interface RoomOrchestrator {
    fun getRoom(roomId: RoomId): Room?
    fun addRoom(room: Room)
    fun addTaskToRoom(room: Room, task: Task)
}

@Service
class RoomOrchestratorAdapter(private val repository: RoomWriteRepository): RoomOrchestrator {
    override fun getRoom(roomId: RoomId): Room? {
        return repository.find(roomId)
    }

    override fun addRoom(room: Room) {
        repository.save(room)
    }

    override fun addTaskToRoom(room: Room, task: Task) {
        room.addTask(task)
        repository.save(room)
    }
}