package com.sherylily.lifease.room.infrastructure.secondary

import com.sherylily.lifease.room.domain.Room
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

abstract class RoomRepositoryContract {
    protected abstract val repository: RoomWriteRepository

    @Test
    fun `save room to database and retrieve it`() {
        // given
        val room = Room(name = "Salon")

        // when
        repository.save(room)

        // then
        repository.find(room.id) shouldBe room
    }
}