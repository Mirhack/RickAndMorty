package com.mirhack.rickandmorty.data.db.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mirhack.rickandmorty.data.db.COLUMN_CHARACTER_CREATED
import com.mirhack.rickandmorty.data.db.COLUMN_CHARACTER_EPISODE
import com.mirhack.rickandmorty.data.db.COLUMN_CHARACTER_GENDER
import com.mirhack.rickandmorty.data.db.COLUMN_CHARACTER_ID
import com.mirhack.rickandmorty.data.db.COLUMN_CHARACTER_IMAGE
import com.mirhack.rickandmorty.data.db.COLUMN_CHARACTER_NAME
import com.mirhack.rickandmorty.data.db.COLUMN_CHARACTER_SPECIES
import com.mirhack.rickandmorty.data.db.COLUMN_CHARACTER_STATUS
import com.mirhack.rickandmorty.data.db.COLUMN_CHARACTER_TYPE
import com.mirhack.rickandmorty.data.db.COLUMN_CHARACTER_URL
import com.mirhack.rickandmorty.data.db.TABLE_CHARACTERS

@Entity(tableName = TABLE_CHARACTERS)
data class CharacterEntity(
    @ColumnInfo(name = COLUMN_CHARACTER_ID)
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = COLUMN_CHARACTER_NAME)
    val name: String,
    @ColumnInfo(name = COLUMN_CHARACTER_STATUS)
    val status: String,
    @ColumnInfo(name = COLUMN_CHARACTER_SPECIES)
    val species: String,
    @ColumnInfo(name = COLUMN_CHARACTER_TYPE)
    val type: String,
    @ColumnInfo(name = COLUMN_CHARACTER_GENDER)
    val gender: String,
    @ColumnInfo(name = COLUMN_CHARACTER_IMAGE)
    val image: String,
    @ColumnInfo(name = COLUMN_CHARACTER_EPISODE)
    val episode: List<String>,
    @ColumnInfo(name = COLUMN_CHARACTER_URL)
    val url: String,
    @ColumnInfo(name = COLUMN_CHARACTER_CREATED)
    val created: String,
)