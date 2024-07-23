package com.sherylily.lifease.common

// needs implementation in a companion object
interface DomainToRecordInterface<T, S> {
    fun fromDomain(domain: T): S
}

interface RecordToDomainInterface<T> {
    fun toDomain(): T
}