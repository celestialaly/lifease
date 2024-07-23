package com.sherylily.lifease.room.infrastructure.secondary

import com.sherylily.lifease.common.RoomId
import com.sherylily.lifease.room.domain.Room
import java.util.*

interface RoomReadRepository {
    fun find(roomId: RoomId): Room?
}
interface RoomWriteRepository: RoomReadRepository {
    fun save(room: Room)
}

class RoomInMemoryRepository: RoomWriteRepository {
    private val store: MutableMap<UUID, Room> = mutableMapOf()

    override fun find(roomId: RoomId): Room? {
        return store[roomId.toUUID()]
    }

    override fun save(room: Room) {
        store[room.id.toUUID()] = room
    }
}