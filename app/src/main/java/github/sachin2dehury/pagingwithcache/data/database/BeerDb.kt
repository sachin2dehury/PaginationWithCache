package github.sachin2dehury.pagingwithcache.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BeerEntity::class],
    version = 1
)
abstract class BeerDb : RoomDatabase() {
    abstract val dao: BeerDao
}