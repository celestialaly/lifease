package com.sherylily.lifease.room.orchestration

import com.sherylily.lifease.room.domain.Room
import com.sherylily.lifease.room.infrastructure.secondary.RoomWriteRepository
import org.springframework.stereotype.Service

interface RoomOrchestrator {
    fun addRoom(room: Room)
}

@Service
class RoomOrchestratorAdapter(private val repository: RoomWriteRepository): RoomOrchestrator {
    override fun addRoom(room: Room) {
        repository.save(room)
    }
}