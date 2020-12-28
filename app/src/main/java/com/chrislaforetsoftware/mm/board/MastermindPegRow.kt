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

	private var choices = 6

	private var popupSelector: MastermindSelector? = null

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
			popupSelector = MastermindSelector(this.context, peg, this)
		}
		return peg
	}

	fun setChoices(choices: Int) {
		this.choices = choices
		peg0.choices = choices
		peg1.choices = choices
		peg2.choices = choices
		peg3.choices = choices
		peg4.choices = choices
		peg5.choices = choices
	}

	fun setNumber(number: Int) {
		this.rowNumber.text = number.toString()

		val rowIs = resources.getString(R.string.row_is)
		this.rowNumber.contentDescription = "$rowIs $number.toString()"
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
			this.doneButton.text = resources.getString(R.string.check_button_text)
		}
		this.doneButton.isClickable = activate
	}


	override fun handlePegColorChoice(activePegHole: MastermindPegHole, chosenPegColor: PegColor) {
		popupSelector?.close()
		activePegHole.setColor(chosenPegColor)
	}
}