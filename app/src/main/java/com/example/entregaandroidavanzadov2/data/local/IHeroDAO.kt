package com.example.entregaandroidavanzadov2.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.entregaandroidavanzadov2.data.local.model.LocalHero

@Dao
interface IHeroDAO {
    @Query("SELECT * FROM heros")
    suspend fun getAll(): List<LocalHero>

    @Query("SELECT * FROM heros WHERE heros.id==:idFragment")
    suspend fun getHeroByID(idFragment: String): LocalHero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllVararg(vararg users: LocalHero)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllList(users: List<LocalHero>)

    @Query("UPDATE  heros SET favorite=:favorite  WHERE heros.id==:id")
    suspend fun  updateHero(id: String, favorite: Boolean)
    @Delete
    suspend fun delete(user: LocalHero)


}