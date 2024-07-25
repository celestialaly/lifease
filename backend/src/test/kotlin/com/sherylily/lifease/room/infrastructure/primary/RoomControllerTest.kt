package com.sherylily.lifease.room.infrastructure.primary

import com.sherylily.lifease.common.RoomId
import com.sherylily.lifease.common.TaskId
import com.sherylily.lifease.room.fixture.RoomFixture
import com.sherylily.lifease.room.infrastructure.secondary.RoomInMemoryRepository
import com.sherylily.lifease.room.orchestration.RoomOrchestratorAdapter
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.maps.shouldHaveKey
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RoomControllerTest {
    private val repository = RoomInMemoryRepository()
    private val orchestrator =  RoomOrchestratorAdapter(repository)
    private val controller = RoomController(orchestrator)
    private val roomFixture = RoomFixture(repository)

    @BeforeEach
    fun loadFixtures() {
        roomFixture.generate()
    }

    @Test
    fun `should retrieve list of rooms`() {
        val rooms = controller.list()

        rooms.size shouldBeExactly roomFixture.roomList.size
    }

    @Test
    fun `should create a new room`() {
        // given
        val roomName = "Salon"
        val request = RoomRequest(name = roomName)

        // when
        val roomResource = controller.createRoom(request)

        // then
        repository.find(RoomId(roomResource.roomId))!!.name shouldBe roomName
    }

    @Test
    fun `should create task to a room`() {
        // given
        val roomResource = controller.createRoom(RoomRequest(name = "Salon"))
        val taskRequest = TaskRequest(name = "Ménage")

        // when
        val taskResource = controller.addTaskToRoom(roomResource.roomId, taskRequest)

        // then
        repository.find(RoomId(roomResource.roomId))!!.tasks.shouldHaveKey(TaskId(taskResource.taskId))
    }
}