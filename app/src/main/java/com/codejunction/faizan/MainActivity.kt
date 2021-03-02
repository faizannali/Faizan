package com.codejunction.faizan

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var animZoomIn: Animation? =null
    private var fade: Animation? =null
    private var mediaPlayer:MediaPlayer?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayer.create(this, R.raw.eating);

        animZoomIn = AnimationUtils.loadAnimation(
            this,
            R.anim.zoom_in
        )
        fade = AnimationUtils.loadAnimation(
            this,
            R.anim.fade
        )



        feedMeBtn.setOnClickListener{
            feedMeBtn.isEnabled=false
            var newValue= appleEaten()

            when(newValue){
                5 -> {
                    dinoImage.startAnimation(fade)
                    dinoImage.setImageResource(R.drawable.ic_dino2)
                    stageLevel.text = "Stage 2"
                }
                10 -> {
                    dinoImage.startAnimation(fade)
                    dinoImage.setImageResource(R.drawable.ic_dino3)
                    stageLevel.text = "Stage 3"
                }
                15 -> {
                    dinoImage.startAnimation(fade)
                    dinoImage.setImageResource(R.drawable.ic_dino4)
                    stageLevel.text = "Stage 4"
                }
                20 -> {
                    dinoImage.startAnimation(fade)
                    dinoImage.setImageResource(R.drawable.ic_dino5)
                    feedMeBtn.visibility = View.GONE
                    playAgainBtn.visibility = View.VISIBLE
                    stageLevel.text = "Stage 5"
                    tvMeetDino.text = "You Won!"
                    tvFeedDino.setText(R.string.Congrats)
                    playAgainBtn.setOnClickListener { reset() }
                }

                }
            }

    }

    private fun appleEaten():Int{
        mediaPlayer?.start()
        dinoImage.startAnimation(animZoomIn)
        val oldValue=eatenAppleValue.text.toString()
        val newValue=oldValue.toInt()+1
        eatenAppleValue.text=newValue.toString()
        buttonTimer()
        return newValue
    }

    private fun reset(){
        dinoImage.startAnimation(fade)
        dinoImage.setImageResource(R.drawable.ic_dino)
        feedMeBtn.visibility=View.VISIBLE
        playAgainBtn.visibility=View.GONE
        stageLevel.text="Stage 1"
        tvMeetDino.setText(R.string.meetDino)
        tvFeedDino.setText(R.string.FeedDino)
        eatenAppleValue.text="0"
    }

    private fun buttonTimer(){
        val buttonTimer = Timer()
        buttonTimer.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread { feedMeBtn.isEnabled=true }
            }
        }, 1600)
    }
}