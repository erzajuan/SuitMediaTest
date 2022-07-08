package com.bastilla.suitmediaapp.utils
//
//import androidx.lifecycle.LiveData
//import androidx.paging.*
//import com.bastilla.suitmediaapp.database.UserDatabase
//import com.bastilla.suitmediaapp.thirdscreen.pagging.StoryRemoteMediator
//import com.bastilla.suitmediaapp.api.ApiService
//import com.bastilla.suitmediaapp.api.DataItem
//
//class Repository(
//    private val apiService: ApiService,
//    private val userDatabase: UserDatabase
//) {
//    fun getPaggingStories(
//    ): LiveData<PagingData<DataItem>> {
//
//        @OptIn(ExperimentalPagingApi::class)
//        return Pager(
//            config = PagingConfig(
//                pageSize = 5
//            ),
//            remoteMediator = StoryRemoteMediator(userDatabase, apiService),
//            pagingSourceFactory = {
//                userDatabase.storyDao().getAllLocalStory()
//            }
//        ).liveData
//    }
//}