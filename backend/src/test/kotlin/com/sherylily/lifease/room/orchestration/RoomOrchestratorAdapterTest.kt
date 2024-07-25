package com.sherylily.lifease.room.orchestration

import com.sherylily.lifease.room.domain.Room
import com.sherylily.lifease.room.domain.Task
import com.sherylily.lifease.room.fixture.RoomFixture
import com.sherylily.lifease.room.infrastructure.secondary.RoomInMemoryRepository
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.maps.shouldHaveKey
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RoomOrchestratorAdapterTest {
    private val repository = RoomInMemoryRepository()
    private val orchestrator = RoomOrchestratorAdapter(repository)
    private val roomFixture = RoomFixture(repository)

    @BeforeEach
    fun loadFixtures() {
        roomFixture.generate()
    }

    @Test
    fun `should retrieve rooms`() {
        val rooms = orchestrator.getRooms()

        rooms.size shouldBeExactly roomFixture.roomList.size
    }

    @Test
    fun `should add a room to database`() {
        // given
        val roomName = "Bureau"
        val room = Room(name = roomName)

        // when
        orchestrator.addRoom(room)

        // then
        repository.find(room.id)?.name shouldBe roomName
    }

    @Test
    fun `should add a task to a room and persist it to database`() {
        // given
        val room = Room(name = "Bureau")
        orchestrator.addRoom(room)

        // when
        val task = Task(name = "Faire le ménage")
        orchestrator.addTaskToRoom(room, task)

        // then
        repository.find(room.id)!!.tasks.shouldHaveKey(task.id)
    }
}