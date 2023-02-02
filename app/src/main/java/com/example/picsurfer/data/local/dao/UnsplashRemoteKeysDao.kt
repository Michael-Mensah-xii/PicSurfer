package com.example.picsurfer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.picsurfer.model.UnsplashRemoteKeys


@Dao
interface UnsplashRemoteKeysDao {

    @Query("SELECT * FROM `unsplash_remote_keys _table ` WHERE id =:id")
    suspend fun getRemoteKeys(id: String): UnsplashRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<UnsplashRemoteKeys>)

    @Query("DELETE FROM `unsplash_remote_keys _table `")
    suspend fun deleteAllRemoteKeys()

}