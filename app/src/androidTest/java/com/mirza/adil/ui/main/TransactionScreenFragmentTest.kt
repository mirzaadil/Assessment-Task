package com.mirza.adil.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
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
        navController = mock(NavController::class.java)
    }
    @ExperimentalCoroutinesApi
    @Test
    fun checkDefaultValueForMaskNumberEditText_returnAED0_point_00(){
        launchFragmentInHiltContainer<TransactionScreenFragment>(){
            Navigation.setViewNavController(requireView(),navController)
        }
        val maskNumberEditText: ViewInteraction = onView(withId(R.id.etPassCodeText))
        val value = maskNumberEditText.getText()
        assertEquals("AED0.00",value)
    }
    /**
     * The purpose of this test case is to check when we enter number in thousands, it should returns comma separated
     * formatted string
    * */

    @Test
    fun checkClickOnButton4561_returnAED4_comma_561_point_00(){
        launchFragmentInHiltContainer<TransactionScreenFragment>(){
            Navigation.setViewNavController(requireView(),navController)
        }
        val maskNumberEditText: ViewInteraction = onView(withId(R.id.etPassCodeText))
        onView(withId(R.id.button4)).perform(click())
        onView(withId(R.id.button5)).perform(click())
        onView(withId(R.id.button6)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        val value = maskNumberEditText.getText()
        assertEquals("AED4,561.00",value)
    }

    /**
     * The purpose of this test case is to check when we enter number in tens of thousands, it should returns comma separated
     * formatted string
     * */
    @Test
    fun checkClickOnButton94561_returnAED94_comma_561_point_00(){
        launchFragmentInHiltContainer<TransactionScreenFragment>(){
            Navigation.setViewNavController(requireView(),navController)
        }
        val maskNumberEditText: ViewInteraction = onView(withId(R.id.etPassCodeText))
        onView(withId(R.id.button9)).perform(click())
        onView(withId(R.id.button4)).perform(click())
        onView(withId(R.id.button5)).perform(click())
        onView(withId(R.id.button6)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        val value = maskNumberEditText.getText()
        assertEquals("AED94,561.00",value)
    }
    /**
     * The purpose of this test case is to check when we enter the number in lacs, it should
     * return comma separated formatted string
     * */
    @Test
    fun checkClickOnButton945611_returnAED945_comma_611_point_00(){
        launchFragmentInHiltContainer<TransactionScreenFragment>(){
            Navigation.setViewNavController(requireView(),navController)
        }
        val maskNumberEditText: ViewInteraction = onView(withId(R.id.etPassCodeText))
        onView(withId(R.id.button9)).perform(click())
        onView(withId(R.id.button4)).perform(click())
        onView(withId(R.id.button5)).perform(click())
        onView(withId(R.id.button6)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        val value = maskNumberEditText.getText()
        assertEquals("AED945,611.00",value)
    }
    /**
     * The purpose of this test case is to check when we enter the number in tens of lacs(millions), it should
     * return comma separated formatted string
     * */
    @Test
    fun checkClickOnButton9456123_returnAED9_comma_456_comma_123_point_00(){
        launchFragmentInHiltContainer<TransactionScreenFragment>(){
            Navigation.setViewNavController(requireView(),navController)
        }
        val maskNumberEditText: ViewInteraction = onView(withId(R.id.etPassCodeText))
        onView(withId(R.id.button9)).perform(click())
        onView(withId(R.id.button4)).perform(click())
        onView(withId(R.id.button5)).perform(click())
        onView(withId(R.id.button6)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.button3)).perform(click())
        val value = maskNumberEditText.getText()
        assertEquals("AED9,456,123.00",value)
    }
    /**
     * The purpose of this test case is to check when we enter the number in cror, it should
     * return comma separated formatted string
     * */
    @Test
    fun checkClickOnButton94561234_returnAED94_comma_561_comma_234_point_00(){
        launchFragmentInHiltContainer<TransactionScreenFragment>(){
            Navigation.setViewNavController(requireView(),navController)
        }
        val maskNumberEditText: ViewInteraction = onView(withId(R.id.etPassCodeText))
        onView(withId(R.id.button9)).perform(click())
        onView(withId(R.id.button4)).perform(click())
        onView(withId(R.id.button5)).perform(click())
        onView(withId(R.id.button6)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button4)).perform(click())
        val value = maskNumberEditText.getText()
        assertEquals("AED94,561,234.00",value)
    }
    /**
     * The purpose of this test case is to check when we enter the number in cror with decimal point values, it should
     * return comma separated formatted string
     * */
    @Test
    fun checkClickOnButton94561234dot78_returnAED94_comma_561_comma_234_point_78(){
        launchFragmentInHiltContainer<TransactionScreenFragment>(){
            Navigation.setViewNavController(requireView(),navController)
        }
        val maskNumberEditText: ViewInteraction = onView(withId(R.id.etPassCodeText))
        onView(withId(R.id.button9)).perform(click())
        onView(withId(R.id.button4)).perform(click())
        onView(withId(R.id.button5)).perform(click())
        onView(withId(R.id.button6)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button4)).perform(click())
        onView(withId(R.id.buttonDot)).perform(click())
        onView(withId(R.id.button7)).perform(click())
        onView(withId(R.id.button8)).perform(click())

        val value = maskNumberEditText.getText()
        assertEquals("AED94,561,234.78",value)
    }



    @After
    fun tearDown() {
    }
}