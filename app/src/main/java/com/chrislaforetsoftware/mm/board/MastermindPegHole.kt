package com.chrislaforetsoftware.mm.board

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class MastermindPegHole : View {

    var colorName = "Empty"
    var pegColor = Color.GRAY

    var defaultBoxDimension = 30

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
            context,
            attrs,
            defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
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
        setMeasuredDimension(width, height)
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

        val p = Paint()
        p.setColor(pegColor)
        p.setStyle(Paint.Style.FILL)
        canvas.drawCircle(Rect(0,0, canvas.width, canvas.height));
 //       canvas.drawRect(Rect(0, 0, canvas.width, canvas.height), p)
    }
}