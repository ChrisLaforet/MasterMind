package com.chrislaforetsoftware.mm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.chrislaforetsoftware.mm.board.PlayMastermindActivity
import com.chrislaforetsoftware.mm.board.PlayMastermindActivity.Companion.ALLOW_DUPLICATE_COLORS
import com.chrislaforetsoftware.mm.board.PlayMastermindActivity.Companion.TOTAL_COLORS
import com.chrislaforetsoftware.mm.board.PlayMastermindActivity.Companion.TOTAL_WELLS


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        prepareButtons()

    }

    private fun prepareButtons() {
        var button = findViewById(R.id.beginner_4by4_button) as? Button
        button?.setOnClickListener()  {
            startBeginner4x4Game()
        }

        button = findViewById(R.id.beginner_6by4_button) as? Button
        button?.setOnClickListener()  {
            startBeginner6x4Game()
        }

        button = findViewById(R.id.regular_6by4_button) as? Button
        button?.setOnClickListener()  {
            startStandardGame()
        }

        button = findViewById(R.id.challenger_6by6_button) as? Button
        button?.setOnClickListener()  {
            startChallengerGame()
        }

        button = findViewById(R.id.brainbuster_6by6_button) as? Button
        button?.setOnClickListener()  {
            startBrainBusterGame()
        }

        button = findViewById(R.id.how_to_play_mastermind_button) as? Button
        button?.setOnClickListener()  {
            showHowToPlay()
        }
    }

    private fun startBeginner4x4Game() {
        val intent = Intent(this, PlayMastermindActivity::class.java)
        intent.putExtra(TOTAL_WELLS, PlayMastermindActivity.BASIC_WELLS)
        intent.putExtra(TOTAL_COLORS, PlayMastermindActivity.REDUCED_COLORS)
        intent.putExtra(ALLOW_DUPLICATE_COLORS, false)
        startActivity(intent)
    }

    private fun startBeginner6x4Game() {
        val intent = Intent(this, PlayMastermindActivity::class.java)
        intent.putExtra(TOTAL_WELLS, PlayMastermindActivity.BASIC_WELLS)
        intent.putExtra(TOTAL_COLORS, PlayMastermindActivity.BASIC_COLORS)
        intent.putExtra(ALLOW_DUPLICATE_COLORS, false)
        startActivity(intent)
    }

    private fun startStandardGame() {
        val intent = Intent(this, PlayMastermindActivity::class.java)
        intent.putExtra(TOTAL_WELLS, PlayMastermindActivity.BASIC_WELLS)
        intent.putExtra(TOTAL_COLORS, PlayMastermindActivity.BASIC_COLORS)
        intent.putExtra(ALLOW_DUPLICATE_COLORS, true)
        startActivity(intent)
    }

    private fun startChallengerGame() {
        val intent = Intent(this, PlayMastermindActivity::class.java)
        intent.putExtra(TOTAL_WELLS, PlayMastermindActivity.MAX_WELLS)
        intent.putExtra(TOTAL_COLORS, PlayMastermindActivity.BASIC_COLORS)
        intent.putExtra(ALLOW_DUPLICATE_COLORS, false)
        startActivity(intent)
    }

    private fun startBrainBusterGame() {
        val intent = Intent(this, PlayMastermindActivity::class.java)
        intent.putExtra(TOTAL_WELLS, PlayMastermindActivity.MAX_WELLS)
        intent.putExtra(TOTAL_COLORS, PlayMastermindActivity.BASIC_COLORS)
        intent.putExtra(ALLOW_DUPLICATE_COLORS, true)
        startActivity(intent)
    }

    private fun showHowToPlay() {
        val intent = Intent(this, HowToPlayMastermindActivity::class.java)
        startActivity(intent)
    }
}