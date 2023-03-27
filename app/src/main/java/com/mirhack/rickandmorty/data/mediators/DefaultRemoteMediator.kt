package com.mirhack.rickandmorty.data.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.RemoteMediator.InitializeAction.LAUNCH_INITIAL_REFRESH
import androidx.paging.RemoteMediator.InitializeAction.SKIP_INITIAL_REFRESH
import com.mirhack.rickandmorty.data.db.dao.RemoteKeyDao
import com.mirhack.rickandmorty.data.db.model.RemoteKeyEntity
import com.mirhack.rickandmorty.domain.model.Character

@OptIn(ExperimentalPagingApi::class)
abstract class DefaultRemoteMediator(
    private val remoteKeysDao: RemoteKeyDao,
) : RemoteMediator<Int, Character>() {
    abstract val query: String

    /**
     * @return next page key
     */
    abstract suspend fun loadData(loadKey: Int): Int?

    abstract fun onRefresh()

    override suspend fun initialize(): InitializeAction {
        return if (getRemoteKey() == null)
            LAUNCH_INITIAL_REFRESH
        else
            SKIP_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>,
    ): MediatorResult {
        if (loadType == LoadType.REFRESH) {
            remoteKeysDao.deleteByQuery(query)
            onRefresh()
        }

        val loadKey = getLoadKey(loadType) ?: return MediatorResult.Success(true)
        val nextKey = loadData(loadKey)

        remoteKeysDao.insertOrReplace(
            RemoteKeyEntity(query, nextKey)
        )

        return MediatorResult.Success(nextKey == null)
    }

    private suspend fun getRemoteKey() = remoteKeysDao.remoteKeyByQuery(query)

    private suspend fun getLoadKey(loadType: LoadType): Int? {
        return when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> null
            LoadType.APPEND -> getRemoteKey()?.nextKey
        }
    }
}