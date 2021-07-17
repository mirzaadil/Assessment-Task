package com.mirza.adil.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnitRunner
import com.mirza.adil.R
import com.mirza.adil.utils.getText
import com.mirza.adil.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import javax.inject.Inject

@HiltAndroidTest
@MediumTest
@ExperimentalCoroutinesApi
class TransactionScreenFragmentTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var hiltRule  =  HiltAndroidRule(this)
    private lateinit var navController: NavController
    @Before
    fun setUp() {
        hiltRule.inject()
    }
    @ExperimentalCoroutinesApi
    @Test
    fun checkDefaultValueForMaskNumberEditText_returnAED0_point_00(){
        navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<TransactionScreenFragment>(){
            Navigation.setViewNavController(requireView(),navController)
        }
        val maskNumberEditText: ViewInteraction = onView(withId(R.id.etPassCodeText))
        val name = maskNumberEditText.getText()
        assertEquals("AED0.00",name)
    }
    

    @After
    fun tearDown() {
    }
}