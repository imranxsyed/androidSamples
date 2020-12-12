package com.isyed.samples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.google.android.material.tabs.TabLayout

class PrimaryActivity : AppCompatActivity() , TabLayout.OnTabSelectedListener{
    private lateinit var rootContainer : FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primary)

        rootContainer = findViewById(R.id.primary_container)
        findViewById<TabLayout>(R.id.bottom_nav).addOnTabSelectedListener(this)
    }


    override fun onTabReselected(tab: TabLayout.Tab?) {}
    override fun onTabUnselected(tab: TabLayout.Tab?){
        rootContainer.removeAllViews()

    }
    override fun onTabSelected(tab: TabLayout.Tab?) {
        println("On Tab Selected")
        when (tab?.text.toString().toUpperCase()) {
            "PROFILE" -> viewProfileTab()
            "MENU" -> viewMenuTab()
            "ORDERS" -> viewOrdersTab()
            "CART" -> viewCartTab()
        }
    }

    private fun viewProfileTab(){
        supportFragmentManager.beginTransaction()
                .add(R.id.primary_container, ProfileFragment.newInstance())
                .commit()

    }
    private fun viewMenuTab(){
        println("On Tab Selected")
        supportFragmentManager.beginTransaction()
            .add(R.id.primary_container, MenuFragment.newInstance())
            .commit()
    }
    private fun viewOrdersTab(){

    }
    private fun viewCartTab(){

    }
}