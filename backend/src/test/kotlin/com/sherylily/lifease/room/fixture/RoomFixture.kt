package com.sherylily.lifease.room.fixture

import com.sherylily.lifease.room.domain.Room
import com.sherylily.lifease.room.domain.Task
import com.sherylily.lifease.room.infrastructure.secondary.RoomWriteRepository

object RoomFixtureConstant {
}

class RoomFixture(private val repository: RoomWriteRepository) {
    val roomList = listOf(
        Room(name = "Salon").addTask((Task(name = "Rangement"))),
        Room(name = "Cuisine").addTask((Task(name = "Vaisselle")))
    )

    fun generate() {
        roomList.forEach {
            repository.save(it)
        }
    }
}