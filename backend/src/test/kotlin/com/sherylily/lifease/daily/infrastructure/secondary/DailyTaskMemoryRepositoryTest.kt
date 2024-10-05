package com.sherylily.lifease.daily.infrastructure.secondary

class DailyTaskMemoryRepositoryTest: DailyTaskRepositoryContract() {
    override val repository: DailyTaskWriteRepository = DailyTaskInMemoryRepository()
}