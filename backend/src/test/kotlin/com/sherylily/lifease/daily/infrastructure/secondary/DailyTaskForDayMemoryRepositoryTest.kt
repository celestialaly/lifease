package com.sherylily.lifease.daily.infrastructure.secondary

class DailyTaskForDayMemoryRepositoryTest: DailyTaskRepositoryContract() {
    override val repository: DailyTaskWriteRepository = DailyTaskInMemoryRepository()
}