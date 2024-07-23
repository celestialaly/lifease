package com.sherylily.lifease.room.infrastructure.secondary

import com.sherylily.lifease.common.DomainToRecordInterface
import com.sherylily.lifease.common.RecordToDomainInterface
import com.sherylily.lifease.common.RoomId
import com.sherylily.lifease.common.TaskId
import com.sherylily.lifease.room.domain.Room
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.*

@Entity
class RoomRecord: RecordToDomainInterface<Room> {
    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private var id: UUID? = null

    private var name: String? = null
    private var order: Int = 0

    @OneToMany(mappedBy = "room")
    private var tasks: MutableSet<TaskRecord> = mutableSetOf()

    companion object: DomainToRecordInterface<Room, RoomRecord> {
        override fun fromDomain(domain: Room): RoomRecord {
            val record = RoomRecord()

            record.id = domain.id.toUUID()
            record.name = domain.name
            record.order = domain.order
            record.tasks = domain.tasks.map { TaskRecord.fromDomain(it.value) }.toMutableSet()

            return record
        }
    }

    override fun toDomain(): Room {
        return Room(
            RoomId(id!!),
            name!!,
            tasks.associate { TaskId(it.getId()!!) to it.toDomain() }.toMutableMap(),
            order
        )
    }

    fun getId(): UUID? {
        return id
    }
}