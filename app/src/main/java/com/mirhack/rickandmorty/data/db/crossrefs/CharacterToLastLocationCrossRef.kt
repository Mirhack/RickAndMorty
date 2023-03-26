package com.mirhack.rickandmorty.data.db.crossrefs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import com.mirhack.rickandmorty.data.db.COLUMN_CHARACTER_ID
import com.mirhack.rickandmorty.data.db.COLUMN_LOCATION_LINK_NAME
import com.mirhack.rickandmorty.data.db.TABLE_CHARACTER_TO_LAST_LOCATION

@Entity(
    tableName = TABLE_CHARACTER_TO_LAST_LOCATION,
    primaryKeys = [COLUMN_CHARACTER_ID, COLUMN_LOCATION_LINK_NAME],
    indices = [Index(COLUMN_LOCATION_LINK_NAME)],

    )
data class CharacterToLastLocationCrossRef(
    @ColumnInfo(name = COLUMN_CHARACTER_ID) val characterId: Int,
    @ColumnInfo(name = COLUMN_LOCATION_LINK_NAME) val locationLinkName: String,
)