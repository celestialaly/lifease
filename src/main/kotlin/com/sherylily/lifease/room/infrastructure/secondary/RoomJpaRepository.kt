package com.sherylily.lifease.room.infrastructure.secondary

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoomJpaRepository: JpaRepository<RoomRecord, UUID>