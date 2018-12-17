package br.com.desafio.mobile.desafiomobile.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.desafio.mobile.desafiomobile.R
import br.com.desafio.mobile.desafiomobile.data.model.Movie
import br.com.desafio.mobile.desafiomobile.ui.details.DetailsActivity
import br.com.desafio.mobile.desafiomobile.ui.home.HomeActivity
import br.com.desafio.mobile.desafiomobile.ui.home.MovieAdapter
import com.orhanobut.hawk.Hawk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    val activityRule: IntentsTestRule<HomeActivity> = IntentsTestRule<HomeActivity>(HomeActivity::class.java)

    @Before
    fun setup() {
        Hawk.put("KEY_SAVED_MOVIES", listOf(
                Movie(
                        title = "Amadeus",
                        genre = "Biography, Drama, History, Music",
                        year = "1984",
                        runtime = "160 min",
                        stored = true
                )
        ))
    }

    @Test
    fun startHome_showMovieList() {
        onView(withText("Amadeus")).check(matches(isDisplayed()))
        onView(withText("Biography, Drama, History, Music")).check(matches(isDisplayed()))
        onView(withText("1984")).check(matches(isDisplayed()))
        onView(withText("160 min")).check(matches(isDisplayed()))
    }

    @Test
    fun clickMovieList_startDetailsActivity() {
        onView(withId(R.id.rvMoviesList)).perform(RecyclerViewActions
                .actionOnItemAtPosition<MovieAdapter.ViewHolder>(0, click()))
        Intents.intended(hasComponent(DetailsActivity::class.java.name))
    }
}