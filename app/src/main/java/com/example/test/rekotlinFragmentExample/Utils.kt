package com.example.test.rekotlinFragmentExample

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

/**
 * Created by Mohanraj Karatadipalayam on 03/05/18.
 */
fun AppCompatActivity.loadFragment(fragment: Fragment, containerId: Int, afterCommit: () -> Unit) {
    val transaction = this.supportFragmentManager.beginTransaction()
    //TODO: Change the container frame as input
    transaction
            .replace(containerId, fragment)
            .runOnCommit { afterCommit.invoke() }
            .commit()
}

inline fun AppCompatActivity.transact(action: FragmentTransaction.() -> Unit) {
    supportFragmentManager.beginTransaction().apply {
        action()
    }.commit()
}
