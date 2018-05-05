package com.example.test.rekotlinFragmentExample

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.test.reKotlinFragmentExample.R
import org.rekotlinrouter.SetRouteAction
import org.rekotlinrouter.SetRouteSpecificData
import java.lang.ref.WeakReference



class MainActivity : AppCompatActivity() {

    private var btnFrag1WithoutBack: Button? = null
    private var btnFrag2WithoutBack: Button? = null
    private var btnWithBackStack: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnFrag1WithoutBack = findViewById(R.id.btnfr1without) as Button
        btnFrag2WithoutBack = findViewById(R.id.btnfr2without) as Button
        btnWithBackStack = findViewById(R.id.btnWithBackStack) as Button

        btnWithBackStack!!.setOnClickListener {
//            val intent = Intent(this@MainActivity, BackStackActivity::class.java)
//            startActivity(intent)
            startBackStackActivity()
        }

        btnFrag1WithoutBack!!.setOnClickListener {
            //addFragment(OneFragment(), false, "one")
            startOneFragment(WeakReference(this))
        }

        btnFrag2WithoutBack!!.setOnClickListener {
            startTwoFragment(WeakReference(this))
//            addFragment(TwoFragment(), false, "two")
        }

    }



    fun addFragment(fragment: Fragment,
                    addToBackStack: Boolean,
                    tag: String) {



        val manager = supportFragmentManager
        val ft = manager.beginTransaction()

        if (addToBackStack) {
            ft.addToBackStack(tag)
        }
        ft.replace(R.id.container_frame, fragment, tag)
        ft.commitAllowingStateLoss()
    }


    private fun startBackStackActivity() {
        val routes = arrayListOf(mainActivityRoute, backStackActivityRoute)
        val action = SetRouteAction(route = routes)
        mainStore.dispatch(action)
    }

    private fun startOneFragment(activity: WeakReference<AppCompatActivity>) {
        val routes = arrayListOf(mainActivityRoute, oneFragmentRoute)
        val action = SetRouteAction(route = routes)
        val actionData = SetRouteSpecificData(route= routes,data = FragmentDataValue(activity, false))
        mainStore.dispatch(actionData)
        mainStore.dispatch(action)
    }

    private fun startTwoFragment(activity: WeakReference<AppCompatActivity>) {
        val routes = arrayListOf(mainActivityRoute, twoFragmentRoute)
        val action = SetRouteAction(route = routes)
        val actionData = SetRouteSpecificData(route= routes,data = FragmentDataValue(activity, false))
        mainStore.dispatch(actionData)
        mainStore.dispatch(action)
    }

}