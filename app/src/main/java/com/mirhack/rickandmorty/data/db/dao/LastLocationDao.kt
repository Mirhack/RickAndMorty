package com.mirhack.rickandmorty.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import com.mirhack.rickandmorty.data.db.TABLE_LAST_LOCATIONS
import com.mirhack.rickandmorty.data.db.model.LocationLinkEntity

@Dao
abstract class LastLocationDao {
    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    abstract fun insertAll(links: List<LocationLinkEntity>)

    @RawQuery
    fun clearTable() {
        SimpleSQLiteQuery("DELETE FROM $TABLE_LAST_LOCATIONS")
    }
}