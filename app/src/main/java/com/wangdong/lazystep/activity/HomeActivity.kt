package com.wangdong.lazystep.activity

import android.os.Bundle
import com.wangdong.lazystep.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_welcome.*

class HomeActivity :BaseActivity() {
    private var energy_value = 0.00f
    private var sport_time = 0
    private var kilometre = 0.00f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun onInitVariable() {
    }

    override fun onInitView() {
        tv_energy_value.text = energy_value.toString() + resources.getString(R.string.energy_value)
        tv_sport_value.text = sport_time.toString() + resources.getString(R.string.minute)
        tv_kilometre_value.text = kilometre.toString()
    }

    override fun onRequestData() {
    }
}
