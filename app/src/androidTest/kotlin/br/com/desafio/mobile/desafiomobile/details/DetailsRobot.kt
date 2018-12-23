package br.com.desafio.mobile.desafiomobile.details

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import br.com.desafio.mobile.desafiomobile.R
import br.com.desafio.mobile.desafiomobile.data.model.Movie
import br.com.desafio.mobile.desafiomobile.ui.details.DetailsActivity

class DetailsRobot(val rule: IntentsTestRule<DetailsActivity>) {

    fun checkMovieDisplayed() {
        onView(ViewMatchers.withText("Amadeus")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("Biography, Drama, History, Music")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("1984")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("160 min")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("The life, success and troubles of Wolfgang Amadeus Mozart, as told by Antonio Salieri," +
                " the contemporary composer who was insanely jealous of Mozart's talent and claimed to have murdered him.")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun clickToggleSaveMovie() = onView(withId(R.id.fbFavourite)).perform(click())


    fun checkMovieSavedMessageIsDisplayed() {
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.movie_saved_success)))
    }

    fun checkMovieDeletedMessageIsDisplayed() {
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.movie_removed_success)))
    }

    fun startWithMovieStored() {
        val intent = Intent()
        val fakeMovie = Movie(title = "Amadeus",
                genre = "Biography, Drama, History, Music",
                year = "1984",
                runtime = "160 min",
                plot = "The life, success and troubles of Wolfgang Amadeus Mozart, as told by Antonio Salieri," +
                        " the contemporary composer who was insanely jealous of Mozart's talent and claimed to have murdered him.",
                stored = true)

        intent.putExtra(EXTRA_MOVIE, fakeMovie)
        rule.launchActivity(intent)
    }

    fun startWithMovieNotStored() {
        val intent = Intent()
        val fakeMovie = Movie(title = "Amadeus",
                genre = "Biography, Drama, History, Music",
                year = "1984",
                runtime = "160 min",
                plot = "The life, success and troubles of Wolfgang Amadeus Mozart," +
                        " as told by Antonio Salieri, the contemporary composer who was insanely jealous of Mozart's talent and claimed to have murdered him.")

        intent.putExtra(EXTRA_MOVIE, fakeMovie)
        rule.launchActivity(intent)
    }

    companion object {
        private const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }
}