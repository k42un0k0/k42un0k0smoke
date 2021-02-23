package com.example.k42un0k0smoke

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.k42un0k0smoke.implement.repository.QuitResultRepositoryImpl
import com.example.k42un0k0smoke.implement.repository.SettingRepositoryImpl
import com.example.k42un0k0smoke.implement.repository.StateRepositoryImpl
import com.example.k42un0k0smoke.infrastructure.room.AppDatabase
import com.example.k42un0k0smoke.infrastructure.room.QuitResultDao
import com.example.k42un0k0smoke.repository.QuitResultRepository
import com.example.k42un0k0smoke.repository.SettingRepository
import com.example.k42un0k0smoke.repository.StateRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(ActivityComponent::class)
abstract class RootModule {
    @Binds
    abstract fun bindQuitResultRepository(quitResultRepositoryImpl: QuitResultRepositoryImpl): QuitResultRepository

    @Binds
    abstract fun bindSettingRepository(settingRepositoryImpl: SettingRepositoryImpl): SettingRepository

    @Binds
    abstract fun bindStateRepository(stateRepositoryImpl: StateRepositoryImpl): StateRepository

    companion object {
        @Provides
        fun provideQuitResultDao(appDatabase: AppDatabase): QuitResultDao {
            return appDatabase.quitResultDao()
        }

        @Provides
        fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
            return Room.databaseBuilder(
                appContext,
                AppDatabase::class.java,
                "quit_smoking"
            ).build()
        }

        @Provides
        fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
            return context.getSharedPreferences("QuitSmoking", Context.MODE_PRIVATE);
        }
    }
}