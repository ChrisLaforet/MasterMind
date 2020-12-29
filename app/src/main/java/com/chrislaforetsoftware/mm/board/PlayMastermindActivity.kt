package com.chrislaforetsoftware.mm.board

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import com.chrislaforetsoftware.mm.R
import com.chrislaforetsoftware.mm.rules.Code
import com.chrislaforetsoftware.mm.rules.PegColor
import com.chrislaforetsoftware.mm.rules.checkGuess
import java.util.*


class PlayMastermindActivity : Activity(), PegRowComplete {

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

    private lateinit var rows: List<MastermindPegRow>
    private var currentActiveRow: Int = 0

    private var codeToMatch: Code? = null

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

        setContentView(R.layout.activity_play_mastermind)

        totalWells = intent.getIntExtra(TOTAL_WELLS, BASIC_WELLS)
        totalColors = intent.getIntExtra(TOTAL_COLORS, BASIC_COLORS)
        allowDuplicateColors = intent.getBooleanExtra(ALLOW_DUPLICATE_COLORS, false)

        prepareRows()

        var code = generateCodeToMatch()
        codeToMatch = Code(code)

        // start the game clock
        // Create clock here

        rows[currentActiveRow].activateRow(true)
    }

    private fun prepareRows() {
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

        val allRows = mutableListOf<MastermindPegRow>()
        allRows.add(row0)
        allRows.add(row1)
        allRows.add(row2)
        allRows.add(row3)
        allRows.add(row4)
        allRows.add(row5)
        allRows.add(row6)
        allRows.add(row7)
        allRows.add(row8)
        allRows.add(row9)

        rows = allRows.toList()
    }

    private fun preparePegRow(rowId: Int, rowNumber: Int, is6Well: Boolean): MastermindPegRow {
        val row = findViewById<MastermindPegRow>(rowId)
        row.setWells(if (is6Well) MAX_WELLS else BASIC_WELLS)
        row.setNumber(rowNumber)
        row.setChoices(if (totalColors == BASIC_COLORS) BASIC_COLORS else REDUCED_COLORS)
        row.registerCheckPlay(this)
        return row
    }

    private fun generateCodeToMatch(): List<PegColor> {
        val rng = Random()

        val haystack = createPegList()
        val code: MutableList<PegColor> = mutableListOf()
        for (index in 1..totalWells) {
            val offset = rng.nextInt(haystack.size)
            code.add(haystack[offset])
            if (!allowDuplicateColors) {
                haystack.removeAt(offset)
            }
        }
        return code.toList()
    }

    private fun createPegList(): MutableList<PegColor> {
        val list: MutableList<PegColor> = mutableListOf()
        list.add(PegColor.Red)
        list.add(PegColor.Blue)
        list.add(PegColor.Green)
        list.add(PegColor.Yellow)
        if (totalColors == BASIC_COLORS) {
            list.add(PegColor.Black)
            list.add(PegColor.White)
        }
        return list
    }

    override fun checkPlay(row: MastermindPegRow, pegs: List<PegColor>) {
        val response = codeToMatch?.checkGuess(pegs)
        checkNotNull(response) { "Response cannot be null while checking play values" }
        row.setResults(response)

        if (response.matchCorrect == totalWells) {
            // success!
            showSuccessAlert()
            return
        }

        ++currentActiveRow
        if (currentActiveRow < rows.size) {
            rows[currentActiveRow].activateRow(true)
        } else {
            // failed - show code and finalized
        }
    }

    private fun showSuccessAlert() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setTitle("You won!")
        alertDialog.setMessage("You have successfully broken the selected code!")
        alertDialog.setPositiveButton("OK") { _, _ -> }

        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(true)
        alert.show()
    }
}