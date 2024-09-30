package com.sherylily.lifease.common

import java.util.*

class DailyTaskForDayId(val value: UUID) {
    companion object {
        fun create(): DailyTaskForDayId {
            return DailyTaskForDayId(UUID.randomUUID())
        }
    }

    fun toUUID(): UUID {
        return value
    }

    override fun equals(other: Any?): Boolean {
        return other is DailyTaskForDayId && other.value == value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}