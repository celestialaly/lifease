package com.sherylily.lifease.room.infrastructure.secondary

import com.sherylily.lifease.common.RoomId
import com.sherylily.lifease.room.domain.Room
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class RoomRepository(private val jpa: RoomJpaRepository) : RoomWriteRepository {
    override fun findAll(): List<Room> {
        return jpa.findAll().map { it.toDomain() }
    }

    override fun find(roomId: RoomId): Room? {
        return jpa.findByIdOrNull(roomId.toUUID())?.toDomain()
    }

    override fun save(room: Room) {
        jpa.saveAndFlush(RoomRecord.fromDomain(room))
    }
}