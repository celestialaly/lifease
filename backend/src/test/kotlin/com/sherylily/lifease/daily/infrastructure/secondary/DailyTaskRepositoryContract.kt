package com.sherylily.lifease.daily.infrastructure.secondary

import com.sherylily.lifease.room.domain.Room
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

abstract class DailyTaskRepositoryContract {
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