package com.mirhack.rickandmorty.data.db.model


import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.mirhack.rickandmorty.data.db.COLUMN_CHARACTER_ID
import com.mirhack.rickandmorty.data.db.COLUMN_LOCATION_LINK_NAME
import com.mirhack.rickandmorty.data.db.crossrefs.CharacterToLastLocationCrossRef
import com.mirhack.rickandmorty.data.db.crossrefs.CharacterToOriginCrossRef

data class CharacterFull(
    @Embedded val character: CharacterEntity,

    @Relation(
        parentColumn = COLUMN_CHARACTER_ID,
        entityColumn = COLUMN_LOCATION_LINK_NAME,
        associateBy = Junction(CharacterToOriginCrossRef::class)
    )
    val origin: LocationLinkEntity?,
    @Relation(
        parentColumn = COLUMN_CHARACTER_ID,
        entityColumn = COLUMN_LOCATION_LINK_NAME,
        associateBy = Junction(CharacterToLastLocationCrossRef::class)
    )
    val location: LocationLinkEntity?,
)