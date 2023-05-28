package com.example.entregaandroidavanzadov2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.entregaandroidavanzadov2.data.local.model.LocalHero


@Database(entities = [LocalHero::class], version = 1)
abstract class HeroDatabase: RoomDatabase() {

    abstract fun heroDao(): IHeroDAO
}