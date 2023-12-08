package com.lhd.androidbase.ui.search

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.lhd.androidbase.R


class CircleCanvas(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var mPaint = Paint()
    private var mPivotX: Float = height.toFloat()
    private var mPivotY: Float = width.toFloat()

    //
    private var mColor: Int = 0
    private var mRadius: Float = 40.0f


    init {
        setupAttrs(attrs)
        mPaint.color = if (mColor == 0) Color.BLACK else mColor
        mPaint.style = Paint.Style.FILL_AND_STROKE
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = 5f
    }

    private fun setupAttrs(attr: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attr, R.styleable.CircleCanvas, 0, 0
        ).apply {
            mColor = getColor(R.styleable.CircleCanvas_colorCir, mColor)
            mRadius = getFloat(R.styleable.CircleCanvas_radiusCir, mRadius)

            recycle()
        }
    }


    fun drawCircle(x: Float, y: Float) {
//        val minX = mRadius * 2
//        val maxX = width - mRadius * 2
//        val minY = mRadius * 2
//        val maxY = height - mRadius * 2

        //Generate random numbers for x and y locations of the circle on screen
//        val random = Random()
//        mPivotX = random.nextInt(maxX - minX + 1) + minX
//        mPivotY = random.nextInt(maxY - minY + 1) + minY
        mPivotX = x
        mPivotY = y
        //important. Refreshes the view by calling onDraw function
        invalidate()
    }

    //what I want to draw is here
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(mPivotX, mPivotY, mRadius, mPaint)
    }
}