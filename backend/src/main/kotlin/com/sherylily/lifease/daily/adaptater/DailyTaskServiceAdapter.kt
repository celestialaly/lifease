package com.sherylily.lifease.daily.adaptater

import com.sherylily.lifease.daily.domain.DailyTaskService
import com.sherylily.lifease.daily.domain.DomainDailyTaskService
import com.sherylily.lifease.daily.infrastructure.secondary.DailyTaskWriteRepository
import org.springframework.stereotype.Service

@Service
class DailyTaskServiceAdapter(
    repository: DailyTaskWriteRepository
): DailyTaskService by DomainDailyTaskService(repository)
