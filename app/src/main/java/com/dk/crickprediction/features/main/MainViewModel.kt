package com.dk.crickprediction.features.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dk.crickprediction.data.AppDataHelper
import com.dk.crickprediction.data.Player

class MainViewModel(val appDataHelper: AppDataHelper) : ViewModel(){
    private val TAG = "MainViewModel"
    private var player1 : Player?=null
    private var player2 : Player?=null

    private var onStrikePlayer : Player?=null
    private var nextPlayerNumber = 0
    private var ballNumber =0
    private var over =0
    private var totalRun = 0
    private var wicketDown = 0

    val scoreLiveData = MutableLiveData<String>()
    val runLiveData = MutableLiveData<String>()
    val overLiveData = MutableLiveData<String>()
    val player1LiveData = MutableLiveData<String>()
    val player2LiveData = MutableLiveData<String>()
    val gameOverLiveData = MutableLiveData<String>()
    val thisOverStatus = MutableLiveData<String>()

    fun getPlayers(){
        player1 =  appDataHelper.getPlayersInfo(nextPlayerNumber++)
        onStrikePlayer = player1
        player2 = appDataHelper.getPlayersInfo(nextPlayerNumber++)
        updateScoreBoard(totalRun)
    }

    fun onBowlClicked(){
        ballNumber++
        val run = onStrikePlayer?.runGenerator?.next()
        Log.i(TAG, "onBowlClicked $run ${onStrikePlayer?.name}")
        if (run != null) {
            updatePlayerState(run)
        }
        if(ballNumber==6){
            thisOverStatus.value = " | "
            over++
            ballNumber = 0
            swapPlayer()
            if(over ==4){
                gameOverLiveData.value = "Bengaluru lost by ${40-totalRun} runs"
            }
        }
        updateOver()
    }

    private fun updatePlayerState(state : Int){
        when(state) {
            -1 -> {
                onOutPlayer()

            }
            0 -> updateRun(state)
            1 -> {
                updateRun(state)
                swapPlayer()
            }
            2 -> updateRun(state)
            3 -> {
                updateRun(state)
                swapPlayer()
            }
            4 -> {
                updateRun(state)
            }
            5 -> {
                updateRun(state)
                swapPlayer()
            }
            6 -> {
                updateRun(state)
            }
        }
    }

    private fun onOutPlayer() {
        thisOverStatus.value = " OUT "
        getNextPlayer()
    }

    private fun getNextPlayer() {

        Log.i(TAG,"getNextPlayer OUT  ->${onStrikePlayer?.name}")
        wicketDown++
        val newPlayer = appDataHelper.getPlayersInfo(nextPlayerNumber++)
        if (newPlayer==null){
            gameOverLiveData.value = "Bengaluru lost by ${40-totalRun} runs"
            return
        }

        newPlayer.apply {
            onStrike = true
            onPitch = true
        }

        if(player1?.onStrike!!){
            player1 = newPlayer
            onStrikePlayer = newPlayer
        }else{
            player2 = newPlayer
            onStrikePlayer = newPlayer
        }
        Log.i(TAG,"onStrike player${onStrikePlayer?.name}")
        updateScoreBoard(totalRun)

    }

    private fun updateScoreView(wicketDown: Int, totalRun: Int) {
        scoreLiveData.value = "$totalRun - $wicketDown"
    }
    private fun updateOver() {
        overLiveData.value = "$over.$ballNumber"
    }

    private fun swapPlayer() {

        if (player1?.onStrike!!){
           onStrikePlayer = player2?.apply { onStrike = true }
            player1?.onStrike = false
        }else{
            onStrikePlayer = player1?.apply { onStrike = true }
            player2?. onStrike = false
        }
        updateScoreBoard(totalRun)
        Log.i(TAG,"swapPlayer onstrike ${onStrikePlayer?.name}")
    }

    private fun updateScoreBoard(run : Int){
        if(player1?.onStrike!!){
            onStrikePlayer = player1
            player1LiveData.value = player1?.name + "( ${player1?.totalRun} ) *"
            player2LiveData.value = player2?.name + "( ${player2?.totalRun} ) "
            runLiveData.value = "${player1?.totalRun}"
        }
        else {
            player1LiveData.value = player1?.name + "( ${player1?.totalRun} ) "
            player2LiveData.value = player2?.name + "( ${player2?.totalRun} ) *"
            runLiveData.value = "${player2?.totalRun}"
            onStrikePlayer = player2
        }

        updateScoreView(wicketDown,totalRun)
    }

    private fun updateRun(run: Int) {
        thisOverStatus.value = "$run  "
        totalRun += run
        onStrikePlayer?.totalRun = onStrikePlayer?.totalRun?.plus(run)!!
        updateScoreBoard(totalRun)
        if(totalRun>=40){
            gameOverLiveData.value = "Bengaluru won by ${3-wicketDown} wickets"
        }
        Log.i(TAG, onStrikePlayer!!.name +" - "+ onStrikePlayer!!.totalRun)
    }



}