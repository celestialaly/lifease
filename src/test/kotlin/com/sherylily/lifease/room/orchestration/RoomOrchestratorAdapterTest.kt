package com.sherylily.lifease.room.orchestration

import com.sherylily.lifease.room.domain.Room
import com.sherylily.lifease.room.infrastructure.secondary.RoomInMemoryRepository
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class RoomOrchestratorAdapterTest {
    private val repository = RoomInMemoryRepository()
    private val orchestrator = RoomOrchestratorAdapter(repository)

    @Test
    fun `should add a room to database`() {
        // given
        val roomName = "Salon"
        val room = Room(name = roomName)

        // when
        orchestrator.addRoom(room)

        // then
        repository.find(room.id)?.name shouldBe roomName
    }
}