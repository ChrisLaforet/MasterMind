package com.chrislaforetsoftware.mm.board

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import kotlin.math.min


class MastermindPegHole(context: Context, attrs: AttributeSet?, defStyle: Int) :
        View(context, attrs, defStyle) {

    var colorName = "Empty"
    var pegColor = Color.GRAY

    private var defaultBoxDimension = 20
    private var side: Int = defaultBoxDimension

    constructor(context: Context) : this(context, null, 0) {}

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {}

    init {
        // Load attributes
//        val a = context.obtainStyledAttributes(
//            attrs, R.styleable.MastermindPegHole, defStyle, 0
//        )

//        a.recycle()
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

    fun setColor(colorName: String) {
        this.colorName = colorName
        pegColor = when (colorName) {
            "Red" -> Color.RED
            "Blue" -> Color.BLUE
            "Yellow" -> Color.YELLOW
            "Green" -> Color.GREEN
            "Black" -> Color.BLACK
            "White" -> Color.WHITE
            else -> Color.GRAY
        }

        invalidate()
    }

    fun getCodeColor() : com.chrislaforetsoftware.mm.rules.Color? {
        if (colorName.isEmpty()) {
            return null
        }
        return com.chrislaforetsoftware.mm.rules.Color(colorName)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        val contentWidth = width - paddingLeft - paddingRight
//        val contentHeight = height - paddingTop - paddingBottom

        val paint = Paint()
        paint.setColor(pegColor)
        paint.setStyle(Paint.Style.FILL)

//        val cx = canvas.width.toFloat() / 2f;
//        val cy = canvas.height.toFloat() / 2f;
        val sideMax = this.side * 0.8
        val padding = (this.side.toFloat() - sideMax) / 2f
        val radius = sideMax / 2f
        val cx = padding + radius
        val cy = padding + radius


        canvas.drawCircle(cx.toFloat(), cy.toFloat(), radius.toFloat(), paint)
 //       canvas.drawRect(Rect(0, 0, canvas.width, canvas.height), p)
    }
}