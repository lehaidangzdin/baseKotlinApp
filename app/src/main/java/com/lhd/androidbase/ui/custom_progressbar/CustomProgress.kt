package com.lhd.androidbase.ui.custom_progressbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.lhd.androidbase.R
import kotlin.math.min


class CustomProgress(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint()

    //
    private var mColorBgProgress: Int = 0
    private var mColorProgress: Int = 0
    private var mRadius: Float = 10f
    private var mHeightPro: Float = 20f
    private var mWidthMileStones: Float = 5f

    //
    var lsMileStones = arrayOf<Float>()
        set(value) {
            field = value
            invalidate()
        }

    var progress = 0.4F // From float from 0 to 1
        set(state) {
            if (state in 0.0..1.0) {
                field = state
                invalidate()
            }
        }

    init {
        setupAttrs(attrs)
        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true
    }

    private fun setupAttrs(attr: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attr, R.styleable.CustomProgress, 0, 0
        ).apply {
            mColorBgProgress = getColor(R.styleable.CustomProgress_bgColor, mColorBgProgress)
            mColorProgress = getColor(R.styleable.CustomProgress_proColor, mColorProgress)
            mRadius = getFloat(R.styleable.CustomProgress_proRadius, mRadius)
            mHeightPro = getFloat(R.styleable.CustomProgress_proHeight, mHeightPro)

            recycle()
        }
    }

    private fun drawBgProgress(canvas: Canvas?) {
        mPaint.color = if (mColorBgProgress == 0) Color.BLACK else mColorBgProgress
        drawCenterBar(mPaint, canvas, width.toFloat())
    }

    private fun drawProgress(canvas: Canvas?) {
        mPaint.color = if (mColorProgress == 0) Color.RED else mColorProgress
        drawCenterBar(mPaint, canvas, width * progress)
    }

    private fun drawMileStones(canvas: Canvas?) {
        mPaint.color = Color.BLACK
        lsMileStones.forEach { value ->
            canvas!!.drawRect(
                value * width,
                0f,
                (value * width) + 5f,
                height.toFloat(),
                mPaint
            )
        }
    }


    private fun drawCenterBar(paint: Paint, canvas: Canvas?, width: Float) {
        val barTop = (height - mHeightPro) / 2
        val barBottom = (height + mHeightPro) / 2

        val rect = RectF(0f, barTop, width, barBottom)
        canvas!!.drawRoundRect(rect, mRadius, mRadius, paint)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBgProgress(canvas)
        drawProgress(canvas)
        drawMileStones(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // default size when chose wrap_content
        val desiredWidth = 50
        val desiredHeight = 50

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        //Measure Width
        val width: Int = when (widthMode) {
            MeasureSpec.EXACTLY -> {
                //Must be this size
                widthSize
            }

            MeasureSpec.AT_MOST -> {
                //Can't be bigger than...
                min(desiredWidth, widthSize)
            }

            else -> {
                //Be whatever you want
                desiredWidth
            }
        }

        //Measure Height
        val height: Int = when (heightMode) {
            MeasureSpec.EXACTLY -> {
                //Must be this size
                heightSize
            }

            MeasureSpec.AT_MOST -> {
                //Can't be bigger than...
                min(desiredHeight, heightSize)
            }

            else -> {
                //Be whatever you want
                desiredHeight
            }
        }
        //MUST CALL THIS
        setMeasuredDimension(width, height)
    }


}