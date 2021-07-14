

package com.mirza.adil.di.module

import android.content.Context
import com.mirza.adil.data.local.datastore.UIModeDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/*
* The class FrameworkResolver
*
* @author  Mirza Adil
* @email mirza.adil@gmail.com
* @version 1.0
* @since 14 Jul 2021
*
* This class is used to resolve all the hard android framework dependent objects
*/
@Module
@InstallIn(SingletonComponent::class)
object FrameworkResolver {

    @Singleton
    @Provides
    fun providesUIModelDataStore(@ApplicationContext context: Context): UIModeDataStore {
        return UIModeDataStore(context)
    }


}
