package br.com.desafio.mobile.desafiomobile.home

import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.desafio.mobile.desafiomobile.BaseTest
import br.com.desafio.mobile.desafiomobile.ui.home.HomeActivity
import com.orhanobut.hawk.Hawk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest : BaseTest() {

    @Rule
    @JvmField
    val activityRule: IntentsTestRule<HomeActivity> = IntentsTestRule<HomeActivity>(HomeActivity::class.java)

    lateinit var homeRobot: HomeRobot

    @Before
    fun setup() {
        homeRobot = HomeRobot(mockWebServer)
    }

    @Test
    fun startHome_WitNoSavedMovie_showEmptyList() {
        with(homeRobot) {
            checkEmptyListIsDisplayed()
        }
    }

    @Test
    fun clickMovieList_startDetailsActivity() {
        with(homeRobot) {
            mockMovieSearchResponse()
            searchRemoteMovie()
            clickMoviesList(0)
            checkDetailsActivityCalled()
        }
    }

    @Test
    fun searchMovie_returnWithSuccess() {
        with(homeRobot) {
            mockMovieSearchResponse()
            searchRemoteMovie()
            checkRemoteMovieIsDisplayed()
        }
    }

    @After
    fun tearDown() {
        Hawk.deleteAll()
    }


}