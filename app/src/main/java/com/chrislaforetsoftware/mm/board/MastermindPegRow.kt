package com.chrislaforetsoftware.mm.board

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.chrislaforetsoftware.mm.R
import com.chrislaforetsoftware.mm.rules.PegColor


class MastermindPegRow(context: Context, attrs: AttributeSet?, defStyle: Int) : LinearLayout(context, attrs, defStyle), ChoiceListener {
//	class MastermindPegRow(context: Context, attrs: AttributeSet?, defStyle: Int) : View(context, attrs, defStyle) {

	constructor(context: Context) : this(context, null, 0) {}

	constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {}

	private lateinit var rowNumber: TextView
	private lateinit var peg0: MastermindPegHole
	private lateinit var peg1: MastermindPegHole
	private lateinit var peg2: MastermindPegHole
	private lateinit var peg3: MastermindPegHole
	private lateinit var peg4: MastermindPegHole
	private lateinit var peg5: MastermindPegHole
	private lateinit var doneButton: Button
	private lateinit var blackCount: TextView
	private lateinit var whiteCount: TextView

	init {
		inflate(this.context, R.layout.mastermind_pegrow, this)

		rowNumber = findViewById<TextView>(R.id.row_number)
		peg0 = preparePegHole(R.id.peg_0)
		peg1 = preparePegHole(R.id.peg_1)
		peg2 = preparePegHole(R.id.peg_2)
		peg3 = preparePegHole(R.id.peg_3)
		peg4 = preparePegHole(R.id.peg_4)
		peg5 = preparePegHole(R.id.peg_5)
		doneButton = findViewById<Button>(R.id.row_done)
		blackCount = findViewById<TextView>(R.id.black_count)
		whiteCount = findViewById<TextView>(R.id.white_count)
	}

	private fun preparePegHole(pegHoleId: Int): MastermindPegHole {
		val peg = findViewById<MastermindPegHole>(pegHoleId)
		peg.setOnClickListener {
			val selector = MastermindSelector(this.context, peg, this)
		}
		return peg
	}

	fun setNumber(number: Int) {
		this.rowNumber.text = number.toString()
	}

	fun setWells(wellCount: Int) {
		if (wellCount == 6) {
			return
		}

		peg4.visibility = GONE
		peg5.visibility = GONE
	}

	fun activateRow(activate: Boolean) {
		if (activate) {
			this.doneButton.text = "Done"
		}
		this.doneButton.isClickable = activate
	}


	override fun handleChoice(chosenPeg: PegColor) {

		Toast.makeText(this.context, "Color " + chosenPeg.toString(), Toast.LENGTH_LONG)
//		TODO("Not yet implemented")
	}
}