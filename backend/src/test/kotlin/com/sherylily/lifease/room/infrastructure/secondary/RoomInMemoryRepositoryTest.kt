package com.sherylily.lifease.room.infrastructure.secondary

class RoomInMemoryRepositoryTest: RoomRepositoryContract() {
    override val repository: RoomWriteRepository = RoomInMemoryRepository()
}