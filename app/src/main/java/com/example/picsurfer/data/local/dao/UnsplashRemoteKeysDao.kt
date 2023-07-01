package com.example.picsurfer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.picsurfer.model.UnsplashRemoteKeys
/** The remote keys are used to keep track of key values such as id, previous and the next items in a current list of loaded data */


@Dao
interface UnsplashRemoteKeysDao {

    @Query("SELECT * FROM unsplash_remote_keys_table WHERE id =:id")
    suspend fun getRemoteKeys(id: String): UnsplashRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<UnsplashRemoteKeys>)// insert a list of remote keys

    @Query("DELETE FROM unsplash_remote_keys_table")
    suspend fun deleteAllRemoteKeys()// delete all records from remote keys table

}
