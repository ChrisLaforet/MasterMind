package com.chrislaforetsoftware.mm.board

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.chrislaforetsoftware.mm.R
import com.chrislaforetsoftware.mm.rules.PegColor
import com.chrislaforetsoftware.mm.rules.Response


@SuppressLint("CustomViewStyleable")
class MastermindPegRow(context: Context, attrs: AttributeSet?, defStyle: Int) : LinearLayout(context, attrs, defStyle), ChoiceListener {
//	class MastermindPegRow(context: Context, attrs: AttributeSet?, defStyle: Int) : View(context, attrs, defStyle) {

	constructor(context: Context) : this(context, null, 0)
	constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

	private var rowNumber: RowHeader
	private var pegs: LinearLayout
	private var peg0: MastermindPegHole
	private var peg1: MastermindPegHole
	private var peg2: MastermindPegHole
	private var peg3: MastermindPegHole
	private var peg4: MastermindPegHole
	private var peg5: MastermindPegHole
	private var doneButton: Button
	private var blackCount: TextView
	private var whiteCount: TextView

	var row: Int = 0
		private set
	private var choices = PlayMastermindActivity.BASIC_COLORS
	private var totalWells = PlayMastermindActivity.BASIC_WELLS
	private var activeWells: List<MastermindPegHole> = listOf()
	private var parentCallback: PegRowComplete? = null

	private var popupSelector: MastermindSelector? = null

	init {
		if (attrs != null) {
			val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.mastermind_peg_row)
			val configuredRowNumber: Int = typedArray.getInt(R.styleable.mastermind_peg_row_row_number, 0)
			row = configuredRowNumber

			typedArray.recycle()
		}

		// https://www.vogella.com/tutorials/AndroidCustomViews/article.html

		inflate(this.context, R.layout.mastermind_pegrow, this)

		rowNumber = findViewById<RowHeader>(R.id.row_number)
		rowNumber.rowNumber = row.toString()
		val rowIs = resources.getString(R.string.row_is)
		rowNumber.contentDescription = "$rowIs $this.row.toString()"

		pegs = findViewById<LinearLayout>(R.id.pegs)
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
		return findViewById<MastermindPegHole>(pegHoleId)
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
		this.row = number
		this.rowNumber.rowNumber = number.toString()

		val rowIs = resources.getString(R.string.row_is)
		this.rowNumber.contentDescription = "$rowIs $this.row.toString()"
	}

	fun registerCheckPlay(parentCallback: PegRowComplete) {
		this.parentCallback = parentCallback
	}

	fun setWells(wellCount: Int) {
		this.totalWells = wellCount

		val wells = mutableListOf<MastermindPegHole>()
		wells.add(peg0)
		wells.add(peg1)
		wells.add(peg2)
		wells.add(peg3)
		if (wellCount == PlayMastermindActivity.MAX_WELLS) {
			wells.add(peg4)
			wells.add(peg5)
		} else {
			peg4.visibility = GONE
			peg5.visibility = GONE
		}
		activeWells = wells.toList()
		setMaxWidthForPegs()
	}

	private fun setMaxWidthForPegs() {
		val pegsWidth = context.resources.displayMetrics.widthPixels * 70 / 100
		val pegsHeight = context.resources.displayMetrics.heightPixels * 7 / 100
		pegs.layoutParams = LinearLayout.LayoutParams(pegsWidth, pegsHeight)

		val pegWidth = pegsWidth / PlayMastermindActivity.MAX_WELLS			// keep size consistent across the board
		for (well in this.activeWells) {
			well.maxPegWidth = pegWidth
		}
	}

	fun activateRow(activate: Boolean) {
		if (activate) {
			this.doneButton.text = resources.getString(R.string.check_button_text)

			this.doneButton.setOnClickListener {
				if (areAllWellsFilled()) {
					parentCallback?.checkPlay(this, getPegs())
				} else {
					showCheckCannotBeCompletedAlert()
				}
			}

			activeWells.forEach {
				val peg: MastermindPegHole = it
				it.setOnClickListener {
					popupSelector = MastermindSelector(this.context, peg, this)
				}
			}
		}
		this.doneButton.isClickable = activate
	}

	fun setResults(response: Response) {
		this.doneButton.visibility = GONE
		this.blackCount.visibility = VISIBLE
		this.blackCount.text = response.matchCorrect.toString()
		this.whiteCount.visibility = VISIBLE
		this.whiteCount.text = response.colorCorrect.toString()

		activeWells.forEach {
			it.setOnClickListener {
			}
		}
	}

	private fun areAllWellsFilled(): Boolean {
		activeWells.forEach {
			if (it.getColor() == PegColor.Clear) {
				return false
			}
		}
		return true
	}

	private fun getPegs(): List<PegColor> {
		val pegs = mutableListOf<PegColor>()
		activeWells.forEach {
			pegs.add(it.getColor())
		}
		return pegs.toList()
	}

	private fun showCheckCannotBeCompletedAlert() {
		val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this.context)
		alertDialog.setMessage(R.string.all_pegs_must_be_set)
		alertDialog.setPositiveButton(R.string.ok) { _, _ -> }

		val alert: AlertDialog = alertDialog.create()
		alert.setCanceledOnTouchOutside(true)
		alert.show()
	}

	override fun handlePegColorChoice(activePegHole: MastermindPegHole, chosenPegColor: PegColor) {
		popupSelector?.close()
		activePegHole.setColor(chosenPegColor)
	}
}

interface PegRowComplete {
	fun checkPlay(row: MastermindPegRow, pegs: List<PegColor>)
}