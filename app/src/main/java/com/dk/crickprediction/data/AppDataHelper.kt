package com.dk.crickprediction.data

interface AppDataHelper {
    fun getPlayersInfo(pos : Int) : Player?
    fun initPlayers() : List<Player>
}