package com.mirhack.rickandmorty.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.mirhack.rickandmorty.data.db.TABLE_CHARACTERS
import com.mirhack.rickandmorty.data.db.model.CharacterFull
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CharactersDao {

    @Transaction
    @Query("SELECT * FROM $TABLE_CHARACTERS")
    abstract fun getAll(): Flow<CharacterFull>
}