package com.example.test.rekotlinFragmentExample

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.test.reKotlinFragmentExample.R
import org.rekotlinrouter.Routable
import org.rekotlinrouter.RouteElementIdentifier
import org.rekotlinrouter.RoutingCompletionHandler


/**
 * Created by Mohanraj Karatadipalayam on 03/05/18.
 */
val mainActivityRoute: RouteElementIdentifier = "MainActivity"
val backStackActivityRoute: RouteElementIdentifier = "backStackActivity"
val oneFragmentRoute: RouteElementIdentifier = "oneFragment"
val twoFragmentRoute: RouteElementIdentifier = "twoFragment"



// Routes helper methods

object RoutableHelper {

    fun mainActivityRoutable(context: Context): MainActivityRoutable {
        //val mainActivityIntent = Intent(context, MainActivity::class.java)
        //mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        //context.startActivity(mainActivityIntent)
        return MainActivityRoutable(context)
    }

    fun backStackActivityRoutable(context: Context): BackStackActivityRoutable {
        val backStackActivityIntent = Intent(context, BackStackActivity::class.java)
        backStackActivityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(backStackActivityIntent)
        return BackStackActivityRoutable(context)
    }

    fun fragmentRoutable(fragment: Fragment,tag: String): FragmentRoutable {
        val currentRoute = mainStore.state.navigationState.route
        val intentData: FragmentDataValue =
                mainStore.state.navigationState
                        .getRouteSpecificState<FragmentDataValue>(currentRoute)!!
        val activity = intentData.activity.get()!!
        val addToBackStack = intentData.addToBackStack
       // addFragment(fragment,activity,false,"OneFragment")
        changeFragment(fragment, activity, addToBackStack, tag, R.id.container_frame)

        return FragmentRoutable(activity.applicationContext)
    }

    fun backStackFragmentRoutable(fragment: Fragment,tag: String): FragmentRoutable {
        val currentRoute = mainStore.state.navigationState.route
        val intentData: FragmentDataValue =
                mainStore.state.navigationState
                        .getRouteSpecificState<FragmentDataValue>(currentRoute)!!
        val activity = intentData.activity.get()!!
        val addToBackStack = intentData.addToBackStack
        addFragment(fragment, activity, addToBackStack, tag,R.id.container_frame_back_stack)
        //changeFragment(fragment,activity,addToBackStack,tag,R.id.container_frame_back)

        return FragmentRoutable(activity.applicationContext)
    }
}


class RootRoutable(val context: Context): Routable {
    override fun popRouteSegment(routeElementIdentifier: RouteElementIdentifier,
                                 animated: Boolean,
                                 completionHandler: RoutingCompletionHandler) {
    }

    override fun pushRouteSegment(routeElementIdentifier: RouteElementIdentifier,
                                  animated: Boolean,
                                  completionHandler: RoutingCompletionHandler): Routable {

        return RoutableHelper.mainActivityRoutable(context)
    }

    override fun changeRouteSegment(from: RouteElementIdentifier,
                                    to: RouteElementIdentifier,
                                    animated: Boolean,
                                    completionHandler: RoutingCompletionHandler): Routable {
        TODO("not implemented")
    }

}


class MainActivityRoutable (val context: Context): Routable {
    val TAG = "MainActivityRoutable"
    override fun changeRouteSegment(from: RouteElementIdentifier, to: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler): Routable {
        when (to) {
            backStackActivityRoute -> return RoutableHelper.backStackActivityRoutable(context)
            oneFragmentRoute -> {
                return RoutableHelper.fragmentRoutable(fragment = OneFragment(),
                        tag = "OneFragment")
            }

            twoFragmentRoute -> {
                return RoutableHelper.fragmentRoutable(fragment = TwoFragment(),
                        tag = "TwoFragment")
            }
            else -> {
                TODO("not implemented")
                return BackStackActivityRoutable(context)
            }
        }
    }

    override fun popRouteSegment(routeElementIdentifier: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler) {
        Log.d(TAG, "DO Nothing --- Inside from popRouteSegment() routeElementIdentifier is ${routeElementIdentifier} ")
    }

    override fun pushRouteSegment(routeElementIdentifier: RouteElementIdentifier,
                                  animated: Boolean,
                                  completionHandler: RoutingCompletionHandler): Routable {
        when (routeElementIdentifier) {
            backStackActivityRoute -> return RoutableHelper.backStackActivityRoutable(context)
            oneFragmentRoute -> {
                return RoutableHelper.fragmentRoutable(fragment = OneFragment(),
                        tag = "OneFragment")
            }

            twoFragmentRoute -> {
                return RoutableHelper.fragmentRoutable(fragment = TwoFragment(),
                        tag = "TwoFragment")
            }
            else -> {
                TODO("not implemented")
                return BackStackActivityRoutable(context)
            }
        }
    }
}

class FragmentRoutable(context: Context) : Routable {
    val TAG = "FragmentRoutable"
    override fun changeRouteSegment(from: RouteElementIdentifier, to: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler): Routable {
        TODO("not implemented")
    }

    override fun popRouteSegment(routeElementIdentifier: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler) {
        Log.d(TAG,"FragmentRoutable popRouteSegment")
    }

    override fun pushRouteSegment(routeElementIdentifier: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler): Routable {
        TODO("not implemented")
    }

}

class BackStackActivityRoutable(val context: Context): Routable {
    val TAG = "BackStackActivityRoute"
    override fun changeRouteSegment(from: RouteElementIdentifier, to: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler): Routable {
        when (to) {
            backStackActivityRoute -> return RoutableHelper.backStackActivityRoutable(context)
            oneFragmentRoute -> {
                return RoutableHelper.backStackFragmentRoutable(fragment = OneFragment(),
                        tag = "OneFragment")
            }

            twoFragmentRoute -> {
                return RoutableHelper.backStackFragmentRoutable(fragment = TwoFragment(),
                        tag = "TwoFragment")
            }
            else -> {
                TODO("not implemented")
            }
        }
    }

    override fun popRouteSegment(routeElementIdentifier: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler) {
        Log.d(TAG,"BackStackActivityRoutable popRouteSegment")
    }

    override fun pushRouteSegment(routeElementIdentifier: RouteElementIdentifier,
                                  animated: Boolean,
                                  completionHandler: RoutingCompletionHandler): Routable {
        when (routeElementIdentifier) {
            backStackActivityRoute -> return RoutableHelper.backStackActivityRoutable(context)
            oneFragmentRoute -> {
                return RoutableHelper.backStackFragmentRoutable(fragment = OneFragment(),
                        tag = "OneFragment")
            }

            twoFragmentRoute -> {
                return RoutableHelper.backStackFragmentRoutable(fragment = TwoFragment(),
                        tag = "TwoFragment")
            }
            else -> {
                TODO("not implemented")
            }
        }
    }

}

fun addFragment(fragment: Fragment?,
                appCompatActivity: AppCompatActivity,
                addToBackStack: Boolean,
                tag: String,
                containerId: Int) {


//    val manager = appCompatActivity.supportFragmentManager
//    val ft = manager.beginTransaction()
//
//    if (addToBackStack) {
//        ft.addToBackStack(tag)
//    }
//    ft.replace(R.id.container_frame, fragment, tag)
//    ft.commitAllowingStateLoss()

    fragment?.let {
        appCompatActivity.loadFragment(fragment,
                containerId,
                tag,
                addToBackStack){}
    }

}


fun changeFragment(fragment: Fragment?,
                   appCompatActivity: AppCompatActivity,
                   addToBackStack: Boolean,
                   tag: String,
                   containerId: Int) {

    fragment?.let {
        appCompatActivity.transact {
            replace(containerId, fragment)
            if(addToBackStack){
                addToBackStack(tag)
            }
        }
    }
}