package com.dk.crickprediction.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.dk.crickprediction.R
import com.dk.crickprediction.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    val viewModel : MainViewModel by inject()
    private lateinit var activityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        activityBinding.mainViewModel = viewModel
        viewModel.getPlayers()
        setObservers()
    }

    private fun setObservers() {
        viewModel.player1LiveData.observe(this, Observer {
            activityBinding.batsman1Tv.text = it
        })
        viewModel.player2LiveData.observe(this, Observer {
            activityBinding.batsman2Tv.text = it
        })
        viewModel.runLiveData.observe(this, Observer {
            activityBinding.runTv.text = it
        })
        viewModel.scoreLiveData.observe(this, Observer {
            activityBinding.scoreTv.text = "Score : ".plus(it)
        })
        viewModel.overLiveData.observe(this, Observer {
            activityBinding.overTv.text = "Over : ".plus(it)
        })
        viewModel.gameOverLiveData.observe(this, Observer {
            activityBinding.bowlBtn.text = "Game Over"
            activityBinding.bowlBtn.isEnabled = false
            activityBinding.gameOver.text = it
        })
        viewModel.thisOverStatus.observe(this, Observer {
            activityBinding.overStateTv.text = activityBinding.overStateTv.text.toString().plus(it)
        })
    }
}