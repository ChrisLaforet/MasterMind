package com.chrislaforetsoftware.mm.board

import android.app.Activity
import android.os.Bundle
import com.chrislaforetsoftware.mm.R
import com.chrislaforetsoftware.mm.rules.PegColor
import java.util.*


class PlayMastermindActivity : Activity() {

    private var totalWells: Int = 4
    private var totalColors: Int = 6
    private var allowDuplicateColors: Boolean = false

    private lateinit var row0: MastermindPegRow
    private lateinit var row1: MastermindPegRow
    private lateinit var row2: MastermindPegRow
    private lateinit var row3: MastermindPegRow
    private lateinit var row4: MastermindPegRow
    private lateinit var row5: MastermindPegRow
    private lateinit var row6: MastermindPegRow
    private lateinit var row7: MastermindPegRow
    private lateinit var row8: MastermindPegRow
    private lateinit var row9: MastermindPegRow

    private var codeToMatch: List<PegColor> = listOf()

    companion object {
        const val TOTAL_WELLS = "totalWells"
        const val TOTAL_COLORS = "totalColors"
        const val ALLOW_DUPLICATE_COLORS = "allowDuplicateColors"

        const val MAX_WELLS = 6
        const val BASIC_WELLS = 4

        const val BASIC_COLORS = 6
        const val REDUCED_COLORS = 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        this.requestWindowFeature(Window.FEATURE_NO_TITLE)      // must precede content view

        setContentView(R.layout.activity_play_mastermind)

        totalWells = intent.getIntExtra(TOTAL_WELLS, BASIC_WELLS)
        totalColors = intent.getIntExtra(TOTAL_COLORS, BASIC_COLORS)
        allowDuplicateColors = intent.getBooleanExtra(ALLOW_DUPLICATE_COLORS, false)

        val is6Well = totalWells == MAX_WELLS
        row0 = preparePegRow(R.id.peg_row_0, 1, is6Well)
        row1 = preparePegRow(R.id.peg_row_1, 2, is6Well)
        row2 = preparePegRow(R.id.peg_row_2, 3, is6Well)
        row3 = preparePegRow(R.id.peg_row_3, 4, is6Well)
        row4 = preparePegRow(R.id.peg_row_4, 5, is6Well)
        row5 = preparePegRow(R.id.peg_row_5, 6, is6Well)
        row6 = preparePegRow(R.id.peg_row_6, 7, is6Well)
        row7 = preparePegRow(R.id.peg_row_7, 8, is6Well)
        row8 = preparePegRow(R.id.peg_row_8, 9, is6Well)
        row9 = preparePegRow(R.id.peg_row_9, 10, is6Well)

        generateCodeToMatch()

        // start the game clock
        // Create clock here

        row0.activateRow(true)
    }

    private fun preparePegRow(rowId: Int, rowNumber: Int, is6Well: Boolean): MastermindPegRow {
        val row = findViewById<MastermindPegRow>(rowId)
        row.setWells(if (is6Well) MAX_WELLS else BASIC_WELLS)
        row.setNumber(rowNumber)
        row.setChoices(if (totalColors == BASIC_COLORS) BASIC_COLORS else REDUCED_COLORS)
        return row
    }

    private fun generateCodeToMatch() {
        val rng = Random()

        for () {
            val offset = rng.nextInt()
        }

    }
}