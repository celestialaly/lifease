package com.sherylily.lifease.daily.infrastructure.secondary

import com.sherylily.lifease.common.DailyTaskId
import com.sherylily.lifease.common.DomainToRecordInterface
import com.sherylily.lifease.common.RecordToDomainInterface
import com.sherylily.lifease.daily.domain.DailyTask
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.*

@Entity
class DailyTaskForDayRecord: RecordToDomainInterface<DailyTask> {
    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private var id: UUID? = null

    private var name: String? = null
    private var icon: String? = null
    private var order: Int = 0

    companion object: DomainToRecordInterface<DailyTask, DailyTaskForDayRecord> {
        override fun fromDomain(domain: DailyTask): DailyTaskForDayRecord {
            val record = DailyTaskForDayRecord()

            record.id = domain.id.toUUID()
            record.name = domain.name
            record.icon = domain.icon
            record.order = domain.order

            return record
        }
    }

    override fun toDomain(): DailyTask {
        return DailyTask(
            id = DailyTaskId(id!!),
            name = name!!,
            icon = icon!!,
            order = order
        )
    }

    fun getId(): UUID? {
        return id
    }
}