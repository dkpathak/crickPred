package com.dk.crickprediction.data

import com.dk.crickprediction.utils.RandomRunGenerator

data class Player(
    val name : String = "",
    var onStrike : Boolean = false,
    var totalRun : Int,
    var onPitch : Boolean,
    val runGenerator: RandomRunGenerator<Int>
    )