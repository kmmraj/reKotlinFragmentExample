package com.example.test.rekotlinFragmentExample

/**
 * Created by Mohanraj Karatadipalayam on 03/05/18.
 */
import android.app.Application
import com.squareup.leakcanary.LeakCanary
import org.rekotlinrouter.NavigationState
import org.rekotlinrouter.Router
import tw.geothings.rekotlin.Store

var mainStore = Store(state = null,
        reducer = ::appReducer,
        middleware = emptyList())

var router: Router<BasicAppState>? = null


class AppController : Application() {



    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)


        mInstance = this

        val testState = TestState()


        val state = BasicAppState(navigationState = NavigationState(),
                testAppState = testState)
        mainStore = Store(state = state,
                reducer = ::appReducer,
                middleware = emptyList(),
                automaticallySkipRepeats = true)
        router = Router(store = mainStore,
                rootRoutable = RootRoutable(context = applicationContext),
                stateTransform = { subscription ->
                    subscription.select { stateType ->
                        stateType.navigationState
                    }
                })

    }




    companion object {

        @get:Synchronized var mInstance: AppController? = null
            private set
//        @get:Synchronized var router: Router<BasicAppState>? = null
//            private set

    }


}