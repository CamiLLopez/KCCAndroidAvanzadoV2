package com.example.entregaandroidavanzadov2.utils

import android.content.Context
import androidx.room.Room
import com.example.entregaandroidavanzadov2.data.local.HeroDatabase
import com.example.entregaandroidavanzadov2.data.local.IHeroDAO
import com.example.entregaandroidavanzadov2.data.local.ILocalDataSource
import com.example.entregaandroidavanzadov2.data.local.model.LocalHero
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {


    @Provides
    fun providesSuperheroDatabase(@ApplicationContext context: Context): HeroDatabase {
        val db = Room.databaseBuilder(
            context,
            HeroDatabase::class.java, "superhero-db"
        ).build()
        return db
    }

    @Provides
    fun providesDao(db: HeroDatabase): IHeroDAO {
        val dao = db.heroDao()
        return dao
    }
}