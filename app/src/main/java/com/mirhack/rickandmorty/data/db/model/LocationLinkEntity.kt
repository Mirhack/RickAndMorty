package com.mirhack.rickandmorty.data.db.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mirhack.rickandmorty.data.db.COLUMN_LOCATION_LINK_NAME
import com.mirhack.rickandmorty.data.db.COLUMN_LOCATION_LINK_URL
import com.mirhack.rickandmorty.data.db.TABLE_LAST_LOCATIONS

@Entity(tableName = TABLE_LAST_LOCATIONS)
data class LocationLinkEntity(
    @ColumnInfo(name = COLUMN_LOCATION_LINK_NAME)
    @PrimaryKey
    val name: String,
    @ColumnInfo(name = COLUMN_LOCATION_LINK_URL)
    val url: String,
)