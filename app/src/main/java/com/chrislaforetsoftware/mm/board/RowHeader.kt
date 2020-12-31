package com.chrislaforetsoftware.mm.board

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View

class RowHeader(context: Context, attrs: AttributeSet?, defStyle: Int) : View(context, attrs, defStyle) {

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    private var defaultWidth = 30

    var rowNumber = ""

    init {
        
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width = 0
        var height = 0
        when (MeasureSpec.getMode(widthMeasureSpec)) {
            MeasureSpec.AT_MOST, MeasureSpec.EXACTLY -> width = MeasureSpec.getSize(widthMeasureSpec)
            MeasureSpec.UNSPECIFIED -> width = defaultWidth
        }
        when (MeasureSpec.getMode(heightMeasureSpec)) {
            MeasureSpec.AT_MOST, MeasureSpec.EXACTLY -> height = MeasureSpec.getSize(heightMeasureSpec)
            MeasureSpec.UNSPECIFIED -> height = defaultWidth
        }

        val widthLimit = 3 * height / 2

        if (width > widthLimit) {
            width = widthLimit
        }

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val box = Rect(0, 0, canvas.width, canvas.height)

        val fontSize = box.height().toFloat()  * 0.4f

        // https://stackoverflow.com/questions/2655402/android-canvas-drawtext
        val paint = Paint()
        paint.style = Paint.Style.FILL
        paint.textSize = fontSize
        paint.isFakeBoldText = true
        paint.isAntiAlias = true

        val bounds = Rect()
        paint.getTextBounds(rowNumber, 0, rowNumber.length, bounds)

        val textX = box.exactCenterX() - (bounds.width() / 2)
        val textY = box.exactCenterY() + (bounds.height() / 2)
        val shadowOffsetX = bounds.width() / 24
        val shadowOffsetY = bounds.height() / 12

        paint.color = Color.WHITE
        canvas.drawText(rowNumber, textX + shadowOffsetX, textY + shadowOffsetY, paint)

        paint.color = Color.GRAY
        canvas.drawText(rowNumber, textX, textY, paint)
    }
}