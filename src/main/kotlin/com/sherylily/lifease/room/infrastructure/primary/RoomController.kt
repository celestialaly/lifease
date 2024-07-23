package com.sherylily.lifease.room.infrastructure.primary

import com.sherylily.lifease.room.domain.Room
import com.sherylily.lifease.room.orchestration.RoomOrchestratorAdapter
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

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
}