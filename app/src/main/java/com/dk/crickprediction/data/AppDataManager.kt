package com.dk.crickprediction.data

import com.dk.crickprediction.utils.RandomRunGenerator

class AppDataManager : AppDataHelper {

    private var players : List<Player>

    init {
         players = initPlayers()
    }

    override fun getPlayersInfo( position: Int): Player? {
        return if(position< players.size)
            players[position]
        else null
    }

    override fun initPlayers(): List<Player> {
        return listOf(Player("Kirat Bohli",true, 0,true,
            RandomRunGenerator<Int>().add(0.05,0)
                .add(.3,1)
                .add(0.25,2)
                .add(0.1,3)
                .add(0.15,4)
                .add(0.01,5)
                .add(0.09,6)
                .add(0.05,-1)),

            Player("NS Lodhi",false, 0,true,
            RandomRunGenerator<Int>().add(0.1,0)
                .add(.4,1)
                .add(0.2,2)
                .add(0.05,3)
                .add(0.1,4)
                .add(0.01,5)
                .add(0.04,6)
                .add(0.1,-1)),

            Player("R Rumrah",false, 0,false,
                RandomRunGenerator<Int>().add(0.2,0)
                .add(.3,1)
                .add(0.15,2)
                .add(0.05,3)
                .add(0.05,4)
                .add(0.01,5)
                .add(0.04,6)
                .add(0.02,-1)),

            Player("Shashi Henra",false,0,false,
                RandomRunGenerator<Int>().add(0.3,0)
                    .add(.25,1)
                    .add(0.05,2)
                    .add(0.0,3)
                    .add(0.05,4)
                    .add(0.01,5)
                    .add(0.04,6)
                    .add(0.05,-1)))
    }
}