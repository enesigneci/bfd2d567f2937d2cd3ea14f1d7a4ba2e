package com.enesigneci.satellite.base

interface Provider<T> {
    fun get(id: Int): T
}