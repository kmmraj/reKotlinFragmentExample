package com.example.test.rekotlinFragmentExample

import android.support.v7.app.AppCompatActivity
import org.rekotlinrouter.HasNavigationState
import org.rekotlinrouter.NavigationState
import tw.geothings.rekotlin.StateType
import java.lang.ref.WeakReference

/**
 * Created by Mohanraj Karatadipalayam on 03/05/18.
 */


data class TestState(val name: String? = null,
                                   var isFetching: Boolean = false,
                                   var isCompleted: Boolean = false,
                                   var isUserRequested: Boolean = false) : StateType

data class BasicAppState(override var navigationState: NavigationState,
                        var testAppState: TestState): StateType, HasNavigationState

class FragmentDataValue(val activity: WeakReference<AppCompatActivity>, val addToBackStack: Boolean)