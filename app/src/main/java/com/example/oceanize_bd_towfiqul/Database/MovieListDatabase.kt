package com.example.oceanize_bd_towfiqul.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.oceanize_bd_towfiqul.Database.Model.MovieListData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */
@Database(entities = [MovieListData::class], version = 1)
abstract class MovieListDatabase : RoomDatabase() {

    abstract fun movieListDao(): MovieListDao

    companion object {
        @Volatile
        private var INSTANCE: MovieListDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): MovieListDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieListDatabase::class.java,
                    "word_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(MovieListDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class MovieListDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE.let { database ->
                    scope.launch(Dispatchers.IO) {
                        if (database != null) {
                            populateDatabase(database.movieListDao())
                        }
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(movieListDao: MovieListDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            movieListDao.deleteAll()
        }
    }
}