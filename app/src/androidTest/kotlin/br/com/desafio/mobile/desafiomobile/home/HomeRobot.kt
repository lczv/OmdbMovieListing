package br.com.desafio.mobile.desafiomobile.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import br.com.desafio.mobile.desafiomobile.R
import br.com.desafio.mobile.desafiomobile.TestConstants
import br.com.desafio.mobile.desafiomobile.data.model.Movie
import br.com.desafio.mobile.desafiomobile.ui.details.DetailsActivity
import br.com.desafio.mobile.desafiomobile.ui.home.MovieAdapter
import br.com.desafio.mobile.desafiomobile.utils.SearchViewQuery
import com.orhanobut.hawk.Hawk
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

class HomeRobot(val server: MockWebServer) {
    fun mockSavedMovie() {
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

    fun deleteSavedMovies() = Hawk.delete("KEY_SAVED_MOVIES")

    fun mockMovieSearchResponse() =
            server.enqueue(MockResponse().setResponseCode(200).setBody(TestConstants.MOVIE_SEARCH_RESPONSE))

    fun checkEmptyListIsDisplayed(){
        onView(withId(R.id.tvNoMovieFound)).check(matches(isDisplayed()))
    }

    fun checkSavedMovieIsDisplayed() {
        onView(ViewMatchers.withText("Amadeus")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("Biography, Drama, History, Music")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("1984")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("160 min")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun checkRemoteMovieIsDisplayed() {
        onView(ViewMatchers.withText("Papillon")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("Biography, Crime, Drama")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("1973")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("151 min")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun clickMoviesList(position: Int) =
            onView(ViewMatchers.withId(R.id.rvMoviesList)).perform(RecyclerViewActions
                    .actionOnItemAtPosition<MovieAdapter.ViewHolder>(position, ViewActions.click()))

    fun searchRemoteMovie() {
        onView(ViewMatchers.withId(R.id.svMovieSearch))
                .perform(SearchViewQuery("papillon"))
    }

    fun checkDetailsActivityCalled() = intended(IntentMatchers.hasComponent(DetailsActivity::class.java.name))
}