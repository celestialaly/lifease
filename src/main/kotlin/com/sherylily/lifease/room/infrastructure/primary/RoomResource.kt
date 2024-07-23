package com.sherylily.lifease.room.infrastructure.primary

import com.sherylily.lifease.room.domain.Room

data class RoomResource(private val room: Room) {
    val roomId = room.id.toUUID()
    val name = room.name
    val order = room.order
}
