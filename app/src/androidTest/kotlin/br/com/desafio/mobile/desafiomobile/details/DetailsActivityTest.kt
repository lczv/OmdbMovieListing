package br.com.desafio.mobile.desafiomobile.details

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.desafio.mobile.desafiomobile.R
import br.com.desafio.mobile.desafiomobile.data.model.Movie
import br.com.desafio.mobile.desafiomobile.ui.details.DetailsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsActivityTest {

    @Rule
    @JvmField
    val activityRule: IntentsTestRule<DetailsActivity> = IntentsTestRule<DetailsActivity>(DetailsActivity::class.java, false, false)

    @Test
    fun startHome_showMovieData() {
        startWithMovieNotStored()
        onView(ViewMatchers.withText("Amadeus")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("Biography, Drama, History, Music")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("1984")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("160 min")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun saveMovie_showMessage() {
        startWithMovieNotStored()
        onView(ViewMatchers.withId(R.id.fbFavourite)).perform(ViewActions.click())
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.movie_saved_success)))
    }

    @Test
    fun deleteMovie_showMessage() {
        startWithMovieStored()
        onView(ViewMatchers.withId(R.id.fbFavourite)).perform(ViewActions.click())
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.movie_removed_success)))
    }

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }

    private fun startWithMovieStored() {
        val intent = Intent()
        val fakeMovie = Movie(title = "Amadeus",
                genre = "Biography, Drama, History, Music",
                year = "1984",
                runtime = "160 min",
                stored = true)

        intent.putExtra(EXTRA_MOVIE, fakeMovie)
        activityRule.launchActivity(intent)
    }

    private fun startWithMovieNotStored() {
        val intent = Intent()
        val fakeMovie = Movie(title = "Amadeus",
                genre = "Biography, Drama, History, Music",
                year = "1984",
                runtime = "160 min")

        intent.putExtra(EXTRA_MOVIE, fakeMovie)
        activityRule.launchActivity(intent)
    }

}