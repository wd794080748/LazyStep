package com.wangdong.lazystep.activity

import android.os.Handler
import android.os.Message
import android.view.View
import butterknife.OnClick
import com.wangdong.lazystep.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity :BaseActivity() {
    var time:Int = 3
    override fun getLayoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun onInitVariable() {
        handler = MyHandler()
    }

    override fun onInitView() {
        tv_time.isClickable = false
    }

    override fun onRequestData() {
    }

    override fun onResume() {
        super.onResume()
        handler.sendEmptyMessage(time)
    }

    @OnClick(R.id.tv_time)
    public fun onCick(view: View){
        when(view.id){
            R.id.tv_time -> {
                tv_time.isClickable = true
            startActivity(HomeActivity::class.java)
            }
        }
    }

    inner class MyHandler:Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if(msg?.what!! <= 0){
                tv_time.text = resources.getString(R.string.skip)
                tv_time.isClickable = true
                startActivity(HomeActivity::class.java)
            }else{
                tv_time.text = msg.what.toString()
                time-=1
                handler.sendEmptyMessageDelayed(time,1000)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        time = 5
        tv_time.text = time.toString()
        tv_time.isClickable = false
    }
}
