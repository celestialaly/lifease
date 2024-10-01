package com.sherylily.lifease.daily.infrastructure.secondary

class DailyTaskMemoryRepositoryTest: RoomRepositoryContract() {
    override val repository: RoomWriteRepository = RoomInMemoryRepository()
}