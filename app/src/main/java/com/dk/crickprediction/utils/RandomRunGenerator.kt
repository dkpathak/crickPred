package com.dk.crickprediction.utils

import java.util.*

class RandomRunGenerator<E> @JvmOverloads constructor(random: Random = Random()) {
    private val map: NavigableMap<Double, E> = TreeMap<Double, E>()
    private val random: Random = random

    private var total = 0.0

    fun add(weight: Double, result: E): RandomRunGenerator<E> {
        if (weight <= 0) return this
        total += weight
        map[total] = result
        return this
    }

    operator fun next(): E {
        val value: Double = random.nextDouble() * total
        return map.higherEntry(value).value
    }

}