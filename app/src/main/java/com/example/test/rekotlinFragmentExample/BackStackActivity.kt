package com.example.test.rekotlinFragmentExample

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.test.reKotlinFragmentExample.R
import org.rekotlinrouter.SetRouteAction
import org.rekotlinrouter.SetRouteSpecificData
import java.lang.ref.WeakReference

class BackStackActivity : AppCompatActivity() {

    private var btnFrag1WithBack: Button? = null
    private var btnFrag2WithBack: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back_stack)

        btnFrag1WithBack = findViewById(R.id.btnfr1with) as Button
        btnFrag2WithBack = findViewById(R.id.btnfr2with) as Button

        btnFrag1WithBack!!.setOnClickListener {
            startOneFragment(WeakReference(this))
            //addFragment(OneFragment(), true, "one1")
        }

        btnFrag2WithBack!!.setOnClickListener {
            startTwoFragment(WeakReference(this))
           // addFragment(TwoFragment(), true, "two2")
        }
    }

    fun addFragment(fragment: Fragment, addToBackStack: Boolean, tag: String) {
        val manager = supportFragmentManager
        val ft = manager.beginTransaction()

        if (addToBackStack) {
            ft.addToBackStack(tag)
        }
        ft.replace(R.id.container_frame_back_stack, fragment, tag)
        ft.commitAllowingStateLoss()
    }

//    override fun onBackPressed() {
//
//        super.onBackPressed()
//        handleBackOrCloseAction()
//    }

    private fun handleBackOrCloseAction() {
        super.onBackPressed()
        mainStore.dispatch(DismissBackStackActivityAction())
        val mainActivityRoutes = arrayListOf(mainActivityRoute)
        val mainActivityAction = SetRouteAction(route = mainActivityRoutes)
        mainStore.dispatch(mainActivityAction)
    }


    private fun startOneFragment(activity: WeakReference<AppCompatActivity>) {

        // TODO: Fix this with appending the existing route
        val routes = arrayListOf(mainActivityRoute, backStackActivityRoute, oneFragmentRoute)
        val action = SetRouteAction(route = routes)
        val actionData = SetRouteSpecificData(route= routes,data = FragmentDataValue(activity, true))
        mainStore.dispatch(actionData)
        mainStore.dispatch(action)
    }

    private fun startTwoFragment(activity: WeakReference<AppCompatActivity>) {
        // TODO: Fix this with appending the existing route
        val routes = arrayListOf(mainActivityRoute, backStackActivityRoute, twoFragmentRoute)
        val action = SetRouteAction(route = routes)
        val actionData = SetRouteSpecificData(route= routes,data = FragmentDataValue(activity, true))
        mainStore.dispatch(actionData)
        mainStore.dispatch(action)
    }
}
