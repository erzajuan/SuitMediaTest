package com.bastilla.suitmediaapp.thirdscreen.pagging
//
//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import androidx.room.withTransaction
//import com.bastilla.suitmediaapp.database.RemoteKeys
//import com.bastilla.suitmediaapp.database.UserDatabase
//import com.bastilla.suitmediaapp.api.ApiService
//import com.bastilla.suitmediaapp.api.DataItem
//
//@OptIn(ExperimentalPagingApi::class)
//class StoryRemoteMediator(
//    private val database: UserDatabase,
//    private val apiService: ApiService,
//) : RemoteMediator<Int, DataItem>() {
//
//    override suspend fun initialize(): InitializeAction {
//        return InitializeAction.LAUNCH_INITIAL_REFRESH
//    }
//
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, DataItem>
//    ): MediatorResult {
//        val page = when (loadType) {
//            LoadType.REFRESH -> {
//                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
//                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
//            }
//            LoadType.PREPEND -> {
//                val remoteKeys = getRemoteKeyForFirstItem(state)
//                val prevKey = remoteKeys?.prevKey
//                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
//                prevKey
//            }
//            LoadType.APPEND -> {
//                val remoteKeys = getRemoteKeyForLastItem(state)
//                val nextKey = remoteKeys?.nextKey
//                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
//                nextKey
//            }
//        }
//
//        try {
//            val responseData = apiService.getUser(
////                page,
//            ).data as List<DataItem>
//            val endOfPaginationReached = responseData.isEmpty()
//
//            database.withTransaction {
//                if (loadType == LoadType.REFRESH) {
//                    database.remoteKeysDao().deleteRemoteKeys()
//                    database.storyDao().deleteAll()
//                }
//                val prevKey = if (page == 1) null else page - 1
//                val nextKey = if (endOfPaginationReached) null else page + 1
//                val keys = responseData.map {
//                    RemoteKeys(id = it.id.toString(), prevKey = prevKey, nextKey = nextKey)
//                }
//                database.remoteKeysDao().insertAll(keys)
//                database.storyDao().addStory(responseData)
//            }
//            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
//        } catch (exception: Exception) {
//            return MediatorResult.Error(exception)
//        }
//    }
//
//    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, DataItem>): RemoteKeys? {
//        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { data ->
//            database.remoteKeysDao().getRemoteKeysId(data.id.toString())
//        }
//    }
//
//    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, DataItem>): RemoteKeys? {
//        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { data ->
//            database.remoteKeysDao().getRemoteKeysId(data.id.toString())
//        }
//    }
//
//    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, DataItem>): RemoteKeys? {
//        return state.anchorPosition?.let { position ->
//            state.closestItemToPosition(position)?.id?.let { id ->
//                database.remoteKeysDao().getRemoteKeysId(id.toString())
//            }
//        }
//    }
//
//
//    private companion object {
//        const val INITIAL_PAGE_INDEX = 1
//    }
//
//}