package com.sherylily.lifease.common

import java.util.*

class RoomId(val value: UUID) {
    companion object {
        fun create(): RoomId {
            return RoomId(UUID.randomUUID())
        }
    }

    fun toUUID(): UUID {
        return value
    }

    override fun equals(other: Any?): Boolean {
        return other is RoomId && other.value == value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}