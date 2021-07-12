package com.androidy.hungerbox

import android.app.Application
import androidx.fragment.app.Fragment
import com.androidy.hungerbox.core.BuildConfig
import com.androidy.hungerbox.core.di.CoreComponent
import com.androidy.hungerbox.core.di.DaggerCoreComponent
import com.androidy.hungerbox.core.di.modules.ContextModule
import com.androidy.hungerbox.di.DaggerAppComponent
import timber.log.Timber


/**
 * Created by Ankit
 */


class HungerBoxApplication : Application() {

    lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()

        initCoreDi()
        initAppDi()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    private fun initAppDi() {
        DaggerAppComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }

    private fun initCoreDi() {
      coreComponent = DaggerCoreComponent
            .builder()
            .context(this)
            .build()
    }

}

fun Fragment.coreComponent()= (requireContext().applicationContext as HungerBoxApplication).coreComponent