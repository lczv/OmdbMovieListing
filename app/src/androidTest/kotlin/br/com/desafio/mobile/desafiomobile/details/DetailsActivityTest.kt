package br.com.desafio.mobile.desafiomobile.details

import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.desafio.mobile.desafiomobile.BaseTest
import br.com.desafio.mobile.desafiomobile.ui.details.DetailsActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsActivityTest : BaseTest() {

    @Rule
    @JvmField
    val activityRule: IntentsTestRule<DetailsActivity> = IntentsTestRule<DetailsActivity>(DetailsActivity::class.java, false, false)

    lateinit var detailsRobot: DetailsRobot

    @Before
    fun setUp() {
        detailsRobot = DetailsRobot(activityRule)
    }

    @Test
    fun startHome_showMovieData() {
        with(detailsRobot) {
            startWithMovieNotStored()
            checkMovieDisplayed()
        }
    }

    @Test
    fun saveMovie_showMessage() {
        with(detailsRobot) {
            startWithMovieNotStored()
            checkMovieDisplayed()
            clickToggleSaveMovie()
            checkMovieSavedMessageIsDisplayed()
        }
    }

    @Test
    fun deleteMovie_showMessage() {
        with(detailsRobot) {
            startWithMovieStored()
            checkMovieDisplayed()
            clickToggleSaveMovie()
            checkMovieDeletedMessageIsDisplayed()
        }
    }

}