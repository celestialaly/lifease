package com.sherylily.lifease.daily.infrastructure.secondary

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDate

@Entity
class DailyTaskForDayRecord {
    @Id
    private var completedDate: LocalDate? = null

    @Id
    @ManyToOne
    @JoinColumn(name = "daily_task_id", referencedColumnName = "id", nullable = false)
    private var dailyTask: DailyTaskRecord? = null

    companion object {
        fun create(task: DailyTaskRecord, completedDate: LocalDate): DailyTaskForDayRecord {
            val record = DailyTaskForDayRecord()

            record.dailyTask = task
            record.completedDate = completedDate

            return record
        }
    }

    fun getCompletedDate(): LocalDate {
        return completedDate!!
    }
}