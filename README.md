## ReKotlin Fragment Example

A Fragment example, build with [ReKotlin](https://github.com/ReKotlin/ReKotlin) and [ReKotlin-Router](https://github.com/ReKotlin/rekotlin-router)



## Installation

1. Clone this repository
2. Open in android studio.
3. Build & Run.

# Details

### Dispatching actions that request routing Fragments

You can dispatch the actions that invoke the `Fragments` similar to `Activity` .
```Kotlin
val routes = arrayListOf(mainActivityRoute, backStackActivityRoute, oneFragmentRoute)
val action = SetRouteAction(route = routes)
val actionData = SetRouteSpecificData(route= routes,data = FragmentDataValue(activity, true))
mainStore.dispatch(actionData)
mainStore.dispatch(action)
```
The function `SetRouteSpecificData` passes the data to Router to create the fragment.

###### What kind data need to be passed?

The signature of `FragmentDataValue` looks like
```Kotlin
class FragmentDataValue(val activity: WeakReference<AppCompatActivity>, val addToBackStack: Boolean)
```

We need to pass the `WeakReference` of the activity.

### Routable that creates Fragments

You should override the functions to create the Fragments
```Kotlin
 override fun pushRouteSegment(routeElementIdentifier: RouteElementIdentifier,
                                  animated: Boolean,
                                  completionHandler: RoutingCompletionHandler): Routable {
        when (routeElementIdentifier) {
            oneFragmentRoute -> {
                return RoutableHelper.backStackFragmentRoutable(fragment = OneFragment(),
                        tag = "OneFragment")
            }

            twoFragmentRoute -> {
                return RoutableHelper.backStackFragmentRoutable(fragment = TwoFragment(),
                        tag = "TwoFragment")
            }
        }
    }
```

Let's look at the function `backStackFragmentRoutable`

```Kotlin
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
```

It does the following
- get the current route from `navigationState`
- From the current route, get the fragment's `intentData`
- With the `intentData`, get the activity from its `WeakReference` and Boolean value whether to add to stack.
- Call the function `addFragment`, helper method to create the fragment.







## Unit Testing
1. TBD