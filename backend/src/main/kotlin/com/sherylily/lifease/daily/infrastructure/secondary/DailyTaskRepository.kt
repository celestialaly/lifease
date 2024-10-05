package com.sherylily.lifease.daily.infrastructure.secondary

import com.sherylily.lifease.common.DailyTaskId
import com.sherylily.lifease.daily.domain.DailyTask
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class DailyTaskRepository(private val jpa: DailyTaskJpaRepository) : DailyTaskWriteRepository {
    override fun findAll(): List<DailyTask> {
        return jpa.findAll().map { it.toDomain() }
    }

    override fun find(taskId: DailyTaskId): DailyTask? {
        return jpa.findByIdOrNull(taskId.toUUID())?.toDomain()
    }

    override fun save(task: DailyTask) {
        jpa.saveAndFlush(DailyTaskRecord.fromDomain(task))
    }
}