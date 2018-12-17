package br.com.desafio.mobile.desafiomobile.ui

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.desafio.mobile.desafiomobile.R
import com.google.android.material.snackbar.Snackbar

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    open fun setupObservers() {}

    open fun setupViews() {}

    open fun setupToolbar() {}

    open fun showSnackbar(rootView: View, message: String) {
        val snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
        val textView = snackbar.view.findViewById<TextView>(R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(this, R.color.inverseTextColor))
        snackbar.show()
    }
}