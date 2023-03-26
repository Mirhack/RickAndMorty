package com.mirhack.rickandmorty.data.db.model


import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.mirhack.rickandmorty.data.db.COLUMN_CHARACTER_ID
import com.mirhack.rickandmorty.data.db.COLUMN_LOCATION_LINK_NAME
import com.mirhack.rickandmorty.data.db.crossrefs.CharacterToLocationLinkCrossRef

data class CharacterFull(
    @Embedded val character: CharacterEntity,

    @Relation(
        parentColumn = COLUMN_CHARACTER_ID,
        entityColumn = COLUMN_LOCATION_LINK_NAME,
        associateBy = Junction(CharacterToLocationLinkCrossRef::class)
    )
    val origin: LocationLinkEntity,
    @Relation(
        parentColumn = COLUMN_CHARACTER_ID,
        entityColumn = COLUMN_LOCATION_LINK_NAME,
        associateBy = Junction(CharacterToLocationLinkCrossRef::class)
    )
    val location: LocationLinkEntity,
)