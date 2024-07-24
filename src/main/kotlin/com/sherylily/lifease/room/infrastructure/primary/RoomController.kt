package com.sherylily.lifease.room.infrastructure.primary

import com.sherylily.lifease.common.RoomId
import com.sherylily.lifease.room.domain.Room
import com.sherylily.lifease.room.domain.Task
import com.sherylily.lifease.room.orchestration.RoomOrchestratorAdapter
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/room")
class RoomController(private val orchestrator: RoomOrchestratorAdapter) {
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createRoom(@RequestBody roomRequest: RoomRequest): RoomResource {
        val room = Room(name = roomRequest.name)

        orchestrator.addRoom(room)

        return RoomResource(room)
    }

    @PostMapping("/{roomId}/task/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun addTaskToRoom(@PathVariable("roomId") roomId: UUID, @RequestBody taskRequest: TaskRequest): TaskResource {
        val room = orchestrator.getRoom(RoomId(roomId)) ?: throw EntityNotFoundException("Room $roomId not found")
        val task = Task(name = taskRequest.name)

        orchestrator.addTaskToRoom(room, task)

        return TaskResource(task)
    }
}