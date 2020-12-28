package com.chrislaforetsoftware.mm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.chrislaforetsoftware.mm.board.PlayMastermindActivity
import com.chrislaforetsoftware.mm.board.PlayMastermindActivity.Companion.ALLOW_DUPLICATE_COLORS
import com.chrislaforetsoftware.mm.board.PlayMastermindActivity.Companion.TOTAL_COLORS
import com.chrislaforetsoftware.mm.board.PlayMastermindActivity.Companion.TOTAL_WELLS


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        this.requestWindowFeature(Window.FEATURE_NO_TITLE)      // must precede content view
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

        button = findViewById(R.id.brainbuster_6by6_button) as? Button
        button?.setOnClickListener()  {
            startBrainBusterGame()
        }
    }

    private fun startBeginner4x4Game() {
        val intent = Intent(this, PlayMastermindActivity::class.java)
        intent.putExtra(TOTAL_WELLS, 4)
        intent.putExtra(TOTAL_COLORS, 4)
        intent.putExtra(ALLOW_DUPLICATE_COLORS, false)
        startActivity(intent)
    }

    private fun startBeginner6x4Game() {
        val intent = Intent(this, PlayMastermindActivity::class.java)
        intent.putExtra(TOTAL_WELLS, 4)
        intent.putExtra(TOTAL_COLORS, 6)
        intent.putExtra(ALLOW_DUPLICATE_COLORS, false)
        startActivity(intent)
    }

    private fun startStandardGame() {
        val intent = Intent(this, PlayMastermindActivity::class.java)
        intent.putExtra(TOTAL_WELLS, 4)
        intent.putExtra(TOTAL_COLORS, 4)
        intent.putExtra(ALLOW_DUPLICATE_COLORS, true)
        startActivity(intent)
    }

    private fun startBrainBusterGame() {
        val intent = Intent(this, PlayMastermindActivity::class.java)
        intent.putExtra(TOTAL_WELLS, 6)
        intent.putExtra(TOTAL_COLORS, 6)
        intent.putExtra(ALLOW_DUPLICATE_COLORS, true)
        startActivity(intent)
    }
}