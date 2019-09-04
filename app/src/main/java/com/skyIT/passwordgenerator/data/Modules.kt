package com.skyIT.passwordgenerator.data

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Application){


    @Provides
    @Singleton
    fun providesApplication() : Application {
        return application
    }
}

@Module
class RoomModule(application: Application) {

    private var database: AppDatabase = AppDatabase.invoke(application)

    @Singleton
    @Provides
    fun providesRoomDatabase() : AppDatabase {
        return database
    }

    @Provides
    @Singleton
    fun providesPasswordsDao(database: AppDatabase) : PasswordDao {
        return database.passwordDao()
    }

    @Provides
    @Singleton
    fun providesRepository(passwordDao: PasswordDao) : Repository {
        return Repository(passwordDao)
    }
}