package br.com.desafio.mobile.desafiomobile.utils

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import org.hamcrest.Matcher

class SearchViewQuery(val text: String) : ViewAction {
    override fun getDescription(): String {
        return ""
    }

    override fun getConstraints(): Matcher<View> {
        return isAssignableFrom(SearchView::class.java);
    }

    override fun perform(uiController: UiController?, view: View?) {
        (view as SearchView).setQuery(text, true)
    }
}