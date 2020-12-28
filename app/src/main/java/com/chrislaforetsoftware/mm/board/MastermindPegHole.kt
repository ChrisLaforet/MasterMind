package com.chrislaforetsoftware.mm.board

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.chrislaforetsoftware.mm.R
import com.chrislaforetsoftware.mm.rules.PegColor


class MastermindPegHole(context: Context, attrs: AttributeSet?, defStyle: Int) :
        View(context, attrs, defStyle) {

    var colorName = "Empty"
    var pegColor = PegColor.Clear
    var choices = 4

    private var defaultBoxDimension = 20
    private var side: Int = defaultBoxDimension


    constructor(context: Context) : this(context, null, 0) {}

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {}

    init {
        prepareContentDescriptionFor(pegColor)
    }

    private fun prepareContentDescriptionFor(pegColor: PegColor) {
        val pegIs = resources.getString(R.string.peg_is)
        val colorPart = getColorStringFromColor(pegColor.color)

        contentDescription = "$pegIs $colorPart"
    }

    private fun getColorStringFromColor(color: String): String {
        return when(color) {
            PegColor.Red.color -> resources.getString(R.string.red)
            PegColor.Blue.color -> resources.getString(R.string.blue)
            PegColor.Green.color -> resources.getString(R.string.green)
            PegColor.Yellow.color -> resources.getString(R.string.yellow)
            PegColor.Black.color -> resources.getString(R.string.black)
            PegColor.White.color -> resources.getString(R.string.white)
            else -> resources.getString(R.string.clear)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width = 0
        var height = 0
        when (MeasureSpec.getMode(widthMeasureSpec)) {
            MeasureSpec.AT_MOST, MeasureSpec.EXACTLY -> width = MeasureSpec.getSize(widthMeasureSpec)
            MeasureSpec.UNSPECIFIED -> width = defaultBoxDimension
        }
        when (MeasureSpec.getMode(heightMeasureSpec)) {
            MeasureSpec.AT_MOST, MeasureSpec.EXACTLY -> height = MeasureSpec.getSize(heightMeasureSpec)
            MeasureSpec.UNSPECIFIED -> height = defaultBoxDimension
        }
        this.side = Math.max(width, height)
        setMeasuredDimension(this.side, this.side)
    }

    fun setChoiceCount(choices: Int) {
        this.choices = choices
    }

    fun setColor(pegColor: PegColor) {
        this.pegColor = pegColor

        prepareContentDescriptionFor(pegColor)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val paint = Paint()
        paint.setColor(pegColor.getDisplayColor())
        paint.setStyle(Paint.Style.FILL)

        val sideMax = this.side * 0.8
        val padding = (this.side.toFloat() - sideMax) / 2f
        val radius = sideMax / 2f
        val cx = padding + radius
        val cy = padding + radius

        canvas.drawCircle(cx.toFloat(), cy.toFloat(), radius.toFloat(), paint)
    }
}