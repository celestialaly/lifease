package com.sherylily.lifease.room.domain

import com.sherylily.lifease.common.RoomId
import com.sherylily.lifease.common.TaskId
import io.kotest.matchers.maps.shouldBeEmpty
import io.kotest.matchers.maps.shouldContainValue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class RoomTest {
    @Test
    fun `should instantiate a room`() {
        val roomName = "Salon"
        val room = Room(RoomId.create(), roomName)

        room.name shouldBe roomName
    }

    @Test
    fun `should add task to room`() {
        // given
        val room = Room(RoomId.create(), "Salon")

        // when
        val taskName = "Nettoyer la table"
        val task = Task(TaskId.create(), taskName)
        room.addTask(task)

        // then
        room.tasks.shouldContainValue(task)
    }

    @Test
    fun `should complete task from a room`() {
        // given
        var room = Room(RoomId.create(), "Salon")
        val task = Task(TaskId.create(), "Nettoyer le salon")
        room = room.addTask(task)

        // when
        room.completeTask(task.id)

        // then
        room.activeTasks().shouldBeEmpty()
    }
}