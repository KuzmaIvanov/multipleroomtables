package com.example.multipleroomtables

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.multipleroomtables.entities.Director
import com.example.multipleroomtables.entities.School
import com.example.multipleroomtables.entities.Student
import com.example.multipleroomtables.entities.Subject
import com.example.multipleroomtables.entities.relations.StudentSubjectCrossRef

@Database(
    entities = [
        School::class,
        Student::class,
        Subject::class,
        Director::class,
        StudentSubjectCrossRef::class
    ],
    version = 1
)
abstract class SchoolDatabase: RoomDatabase() {
    abstract fun schoolDao(): SchoolDao

    companion object {
        @Volatile
        private var INSTANCE: SchoolDatabase? = null

        fun getInstance(context: Context): SchoolDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    SchoolDatabase::class.java,
                    "school_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}
