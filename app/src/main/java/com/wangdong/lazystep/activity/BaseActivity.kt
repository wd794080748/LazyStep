package com.wangdong.lazystep.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import com.wangdong.lazystep.application.MyApplication

/**
 *@author wangdong
 *@descripiton
 *@date 2020/4/7 15:16
 */
abstract class BaseActivity :AppCompatActivity(){
    private val isHideAppTitle:Boolean = true
    private val isHideSysTitle:Boolean = false
    public lateinit var handler:Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        this.onInitVariable()
        super.onCreate(savedInstanceState)
        if(this.isHideSysTitle){
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        setScreenRote(true)
        ButterKnife.bind(this)
        setContentView(getLayoutId())
        if(isHideAppTitle){
            getSupportActionBar()?.hide();
        }
        this.onInitView()
        this.onRequestData()
        MyApplication.getInstance().addActivity(this)
    }

    protected abstract fun getLayoutId(): Int

    /*
    * 初始化变量
    **/
    protected abstract fun onInitVariable();

    /*
    * 初始化布局
    **/
    protected abstract fun onInitView();

    /*
    * 请求数据
    **/
    protected abstract fun onRequestData();


    /*
    * 设置横竖屏切换
    * */
    @SuppressLint("SourceLockedOrientationActivity")
    protected fun setScreenRote(screenRoate:Boolean){
        requestedOrientation = if(screenRoate){
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }else{
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }
    /*
    * 页面跳转
    * */
    fun startActivity(clz:Class<*>){
        var intent = Intent()
        intent.setClass(this,clz)
        startActivity(intent)
    }

    /*
    * 携带参数的页面跳转
    * */
    fun startActivity(clz: Class<*>, bundle:Bundle){
        var intent = Intent()
        intent.setClass(this,clz)
        if(bundle != null){
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /*
    * 含有Bundle通过Class打开编辑界面
    * */
    public fun startActivityForResult(clz:Class<*>,bundle: Bundle,requestCode:Int){
        var intent = Intent();
        intent.setClass(this,clz)
        if(bundle != null){
            intent.putExtras(bundle)
        }
        startActivityForResult(intent,requestCode)
    }

    fun showToast(msg:String,duration:Int){
        Toast.makeText(this,msg,duration)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        ButterKnife.bind(this).unbind()
        MyApplication.getInstance().removeActivity(this)
    }
}