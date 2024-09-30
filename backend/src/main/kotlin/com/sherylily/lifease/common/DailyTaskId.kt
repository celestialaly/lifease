package com.sherylily.lifease.common

import java.util.*

class DailyTaskId(val value: UUID) {
    companion object {
        fun create(): DailyTaskId {
            return DailyTaskId(UUID.randomUUID())
        }
    }

    fun toUUID(): UUID {
        return value
    }

    override fun equals(other: Any?): Boolean {
        return other is DailyTaskId && other.value == value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}