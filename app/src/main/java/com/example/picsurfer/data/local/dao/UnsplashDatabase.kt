package com.example.picsurfer.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.picsurfer.model.UnsplashImage
import com.example.picsurfer.model.UnsplashRemoteKeys

@Database(entities = [UnsplashImage::class, UnsplashRemoteKeys::class], version = 1)
abstract class UnsplashDatabase : RoomDatabase() {

    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao

}