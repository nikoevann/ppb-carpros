package com.e.porsche.managers

import com.e.porsche.App

object Injector {
    var application: App? = null

    lateinit var permissionManager: PermissionManager
    lateinit var storageManager: StorageManager
    lateinit var imageManager: ImageManager


    fun initData(){
        permissionManager = PermissionManager()
        storageManager = StorageManager()
        imageManager = ImageManager()
    }
}
