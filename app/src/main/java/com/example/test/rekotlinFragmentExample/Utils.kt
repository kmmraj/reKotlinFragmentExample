package com.example.test.rekotlinFragmentExample

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.example.test.reKotlinFragmentExample.R

/**
 * Created by Mohanraj Karatadipalayam on 03/05/18.
 */
fun AppCompatActivity.loadFragment(fragment: Fragment,
                                   containerId: Int,
                                   tag: String,
                                   isBackStackAllowed: Boolean,
                                   afterCommit: () -> Unit) {
//    val transaction = this.supportFragmentManager.beginTransaction()
//    //TODO: Change the container frame as input
//    transaction
//            .replace(containerId, fragment)
//            .addToBackStack(tag)
//            .runOnCommit { afterCommit.invoke() }
//            .commit()


    val manager = this.supportFragmentManager
    val ft = manager.beginTransaction()

    if (isBackStackAllowed) {
        ft.addToBackStack(tag)
    }
    ft.replace(containerId, fragment, tag)
//    ft.runOnCommit{afterCommit.invoke()}
    ft.commitAllowingStateLoss()

}

inline fun AppCompatActivity.transact(action: FragmentTransaction.() -> Unit) {
    supportFragmentManager.beginTransaction().apply {
        action()
    }.commit()
}
