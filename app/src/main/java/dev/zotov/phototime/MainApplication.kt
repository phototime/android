package dev.zotov.phototime

import android.app.Application
import dev.zotov.phototime.core.coreModule
import dev.zotov.phototime.state.stateModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MainApplication)
            modules(listOf(coreModule, stateModule))
        }
    }
}