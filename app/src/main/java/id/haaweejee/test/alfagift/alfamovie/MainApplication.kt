package id.haaweejee.test.alfagift.alfamovie

import android.app.Application
import id.haaweejee.test.alfagift.alfamovie.data.di.databaseModule
import id.haaweejee.test.alfagift.alfamovie.data.di.ktorModule
import id.haaweejee.test.alfagift.alfamovie.data.di.repositoryModule
import id.haaweejee.test.alfagift.alfamovie.domain.di.useCaseModule
import id.haaweejee.test.alfagift.alfamovie.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MainApplication)
            //Load Modules
            modules(
                ktorModule,
                databaseModule,
                repositoryModule,
                useCaseModule,
                presentationModule
            )
        }
    }
}