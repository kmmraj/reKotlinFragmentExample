package com.example.test.rekotlinFragmentExample

import org.rekotlinrouter.NavigationReducer
import tw.geothings.rekotlin.Action

/**
 * Created by Mohanraj Karatadipalayam on 03/05/18.
 */


fun appReducer(action: Action, oldState: BasicAppState?) : BasicAppState {

    // if no state has been provided, create the default state
    val state = oldState ?: BasicAppState(
            navigationState = NavigationReducer.handleAction(action = action, state = oldState?.navigationState),
            testAppState = TestState())

    return state.copy(
            navigationState = NavigationReducer.reduce(action = action, oldState = state.navigationState),
            testAppState = (::testAppReducer)(action, state.testAppState))
}

fun testAppReducer(action: Action, state: TestState?): TestState {
    val newState =  state ?: TestState()
    when (action) {
        is GetCountAction -> {
            return newState.copy(isUserRequested = true)
        }

    }
    return newState
}
