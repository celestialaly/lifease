package com.sherylily.lifease.daily.infrastructure.secondary

import com.sherylily.lifease.common.DailyTaskId
import com.sherylily.lifease.common.DomainToRecordInterface
import com.sherylily.lifease.common.RecordToDomainInterface
import com.sherylily.lifease.daily.domain.DailyTask
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.*

@Entity
class DailyTaskRecord: RecordToDomainInterface<DailyTask> {
    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private var id: UUID? = null

    private var name: String? = null
    private var icon: String? = null
    private var order: Int = 0

    @OneToMany(mappedBy = "dailyTask", cascade = [CascadeType.ALL])
    private var days: MutableSet<DailyTaskForDayRecord> = mutableSetOf()

    companion object: DomainToRecordInterface<DailyTask, DailyTaskRecord> {
        override fun fromDomain(domain: DailyTask): DailyTaskRecord {
            val record = DailyTaskRecord()

            record.id = domain.id.toUUID()
            record.name = domain.name
            record.icon = domain.icon
            record.order = domain.order
            record.days = domain.completed.map { DailyTaskForDayRecord.create(record, it) }.toMutableSet()

            return record
        }
    }

    override fun toDomain(): DailyTask {
        return DailyTask(
            id = DailyTaskId(id!!),
            name = name!!,
            icon = icon!!,
            order = order,
            completed = days.map { it.getCompletedDate() }.toMutableList()
        )
    }

    fun getId(): UUID? {
        return id
    }
}