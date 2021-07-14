package com.mirza.adil.di.module

import com.mirza.adil.data.local.datastore.UIModeDataStore
import com.mirza.adil.data.local.datastore.UIModeMutableStore
import com.mirza.adil.data.local.datastore.UIModeReadStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



/*
* The class DomainResolver
*
* @author  Mirza Adil
* @email mirza.adil@gmail.com
* @version 1.0
* @since 14 Jul 2021
*
* This class is used to transforms hard android framework dependencies to android free logic objects
*/
@Module
@InstallIn(SingletonComponent::class)
abstract class DomainResolver {

    @Binds
    @Singleton
    abstract fun bindUIModeMutableStore(uiModeDataStore: UIModeDataStore): UIModeMutableStore

    @Binds
    @Singleton
    abstract fun bindUIModeReadStore(uiModeDataStore: UIModeDataStore): UIModeReadStore
}
