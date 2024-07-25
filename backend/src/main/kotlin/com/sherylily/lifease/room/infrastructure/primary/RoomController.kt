package com.sherylily.lifease.room.infrastructure.primary

import com.sherylily.lifease.common.RoomId
import com.sherylily.lifease.room.domain.Room
import com.sherylily.lifease.room.domain.Task
import com.sherylily.lifease.room.orchestration.RoomOrchestratorAdapter
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@CrossOrigin
@RequestMapping("/room")
class RoomController(private val orchestrator: RoomOrchestratorAdapter) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun list(): List<RoomResource> {
        val rooms = orchestrator.getRooms()

        return rooms.map { RoomResource(it) }
    }

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