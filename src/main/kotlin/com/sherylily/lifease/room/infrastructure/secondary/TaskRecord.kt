package com.sherylily.lifease.room.infrastructure.secondary

import com.sherylily.lifease.common.DomainToRecordInterface
import com.sherylily.lifease.common.RecordToDomainInterface
import com.sherylily.lifease.common.TaskId
import com.sherylily.lifease.room.domain.Task
import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.*

@Entity
class TaskRecord: RecordToDomainInterface<Task> {
    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private var id: UUID? = null

    private var name: String? = null
    private var completed: Boolean = false
    private var priority: Int = 1

    @OneToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    private var room: RoomRecord? = null

    companion object: DomainToRecordInterface<Task, TaskRecord> {
        override fun fromDomain(domain: Task): TaskRecord {
            val record = TaskRecord()

            record.id = domain.id.toUUID()
            record.name = domain.name
            record.completed = domain.completed
            record.priority = domain.priority

            return record
        }
    }

    override fun toDomain(): Task {
        return Task(
            TaskId(id!!),
            name!!,
            completed,
            priority
        )
    }

    fun getId(): UUID? {
        return id
    }
}