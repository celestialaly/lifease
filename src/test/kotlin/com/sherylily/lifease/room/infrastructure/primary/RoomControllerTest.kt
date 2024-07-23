package com.sherylily.lifease.room.infrastructure.primary

import com.sherylily.lifease.common.RoomId
import com.sherylily.lifease.room.infrastructure.secondary.RoomInMemoryRepository
import com.sherylily.lifease.room.orchestration.RoomOrchestratorAdapter
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class RoomControllerTest {
    private val repository = RoomInMemoryRepository()
    private val orchestrator =  RoomOrchestratorAdapter(repository)
    private val controller = RoomController(orchestrator)

    @Test
    fun `createRoom new room from controller endpoint`() {
        // given
        val roomName = "Salon"
        val request = RoomRequest(name = roomName)

        // when
        val roomResource = controller.createRoom(request)

        // then
        repository.find(RoomId(roomResource.roomId))?.name shouldBe roomName
    }
}