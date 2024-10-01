package com.sherylily.lifease.daily.infrastructure.secondary

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DailyTaskJpaRepository: JpaRepository<RoomRecord, UUID>