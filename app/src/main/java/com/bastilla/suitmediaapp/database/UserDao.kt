package com.bastilla.suitmediaapp.database

//
//import androidx.paging.PagingSource
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.bastilla.suitmediaapp.api.DataItem
//
//
//@Dao
//interface UserDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addStory(story: List<DataItem>?)
//
//    @Query("SELECT * FROM storiesTable")
//    fun getAllLocalStory(): PagingSource<Int, DataItem>
//
//    @Query("DELETE FROM storiesTable")
//    suspend fun deleteAll()
//}