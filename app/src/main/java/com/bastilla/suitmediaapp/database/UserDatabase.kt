package com.bastilla.suitmediaapp.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.bastilla.suitmediaapp.api.DataItem
//
//@Database(
//    //error: Entity class must be annotated with @Entity
//    entities = [DataItem::class, RemoteKeys::class],
//    version = 2,
//    exportSchema = false
//)
//abstract class UserDatabase : RoomDatabase() {
//
//    abstract fun storyDao(): UserDao
//    abstract fun remoteKeysDao(): RemoteKeysDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: UserDatabase? = null
//
//        @JvmStatic
//        fun getDatabase(context: Context): UserDatabase {
//            return INSTANCE ?: synchronized(this) {
//                INSTANCE ?: Room.databaseBuilder(
//                    context.applicationContext,
//                    UserDatabase::class.java, "list_story_db"
//                )
//                    .fallbackToDestructiveMigration()
//                    .build()
//                    .also { INSTANCE = it }
//            }
//        }
//    }
//
//}