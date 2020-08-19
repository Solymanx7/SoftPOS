package com.example.android.softpos.data.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Transaction::class], version = 2, exportSchema = false)
abstract class TransactionRoomDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: TransactionRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): TransactionRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TransactionRoomDatabase::class.java,
                    "transaction_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(TransactionDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }

    private class TransactionDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.transactionDao())
                }
            }

        }

        suspend fun populateDatabase(transactionDao: TransactionDao) {
            // Delete all content here.
            transactionDao.deleteAll()

            // Add sample words.
            /*var transaction = Transaction("1100220M", "$490.23", "today")
            transactionDao.insert(transaction)*/

        }
    }
}