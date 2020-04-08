package com.wangdong.lazystep.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wangdong.lazystep.R
import kotlin.math.min

/**
 *@author wangdong
 *@descripiton
 *@date 2020/4/8 0:07
 */
class MyProgressBar @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {
    private lateinit var mContext:Context
    private lateinit var mPaint:Paint
    private lateinit var mCircleBitmap:Bitmap
    private var viewWidth = 0
    private var viewHeight = 0
    private var mCircleColor = Color.GRAY
    private var mCircleBitmapRadius:Int = 0
    private var mStokeWidth = 10f;
    init {
        mContext = context!!
        var obtainStyledAttributes =
            mContext.obtainStyledAttributes(attrs, R.styleable.MyProgressBar)
        mCircleColor = obtainStyledAttributes.getColor(R.styleable.MyProgressBar_color,Color.GRAY)
        initPaint();
        initDrawable()
    }

    private fun initDrawable() {
        mCircleBitmap = BitmapFactory.decodeResource(resources, R.drawable.circle_back)
    }

    private fun initPaint() {
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.isDither = true
        mPaint.style = Paint.Style.STROKE
        mPaint.color = mCircleColor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
       var widthMode =  MeasureSpec.getMode(widthMeasureSpec)
        var measureWidth = MeasureSpec.getSize(widthMeasureSpec)
        var heightMode = MeasureSpec.getMode(heightMeasureSpec)
        var measureHeight = MeasureSpec.getSize(heightMeasureSpec)
        if(widthMode == MeasureSpec.EXACTLY){
            viewWidth = measureWidth
        }else{
            viewWidth = measureWidth + paddingLeft + paddingRight
        }
        if(heightMode == MeasureSpec.EXACTLY){
            viewHeight = measureHeight
        }else{
            viewHeight = measureHeight + paddingTop + paddingBottom
        }
        mCircleBitmapRadius = (mCircleBitmap.height/2)
        var minNum = min(viewWidth/2,viewHeight/2)
            if(mCircleBitmapRadius <= minNum){
                scaleBitmap(minNum, matrix)
                mCircleBitmapRadius = (minNum * 0.8).toInt()
                mStokeWidth = (minNum * 0.1).toFloat()
                mPaint.strokeWidth = mStokeWidth
            }else{
                val matrix = Matrix()
                scaleBitmap(minNum, matrix)
                mStokeWidth = (minNum*0.1).toFloat()
                mPaint.strokeWidth = mStokeWidth
            }
        setMeasuredDimension(viewWidth,viewHeight)
    }

    private fun scaleBitmap(minNum: Int, matrix: Matrix): Float {
        val scaleNum: Float = (minNum.toFloat() / mCircleBitmapRadius.toFloat())
        matrix.postScale((scaleNum * 0.8).toFloat(), (scaleNum * 0.8).toFloat())
        mCircleBitmap = Bitmap.createBitmap(
            mCircleBitmap,
            0,
            0,
            mCircleBitmap.width,
            mCircleBitmap.height,
            matrix,
            false
        )
        return scaleNum
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()
        canvas?.translate((viewWidth/2).toFloat(), (viewHeight/2).toFloat())
        canvas?.drawBitmap(mCircleBitmap,-(mCircleBitmap.width/2).toFloat(),-(mCircleBitmap.height/2).toFloat(),mPaint)
        val rectF = RectF(
            -(mCircleBitmapRadius + mStokeWidth/2),
            -(mCircleBitmapRadius + mStokeWidth/2),
            (mCircleBitmapRadius + mStokeWidth/2),
            (mCircleBitmapRadius + mStokeWidth/2)
        )
        mPaint.color = mContext.resources.getColor(R.color.circle_background)
        canvas?.drawArc(rectF,0f,360f,false,mPaint)
        mPaint.color = mCircleColor
        canvas?.drawArc(rectF,0f,270f,false,mPaint)
        canvas?.restore()
    }


}