package br.com.desafio.mobile.desafiomobile.ui

import android.app.Application
import br.com.desafio.mobile.desafiomobile.data.datasource.ApiInstance
import com.orhanobut.hawk.Hawk

class DesafioMobileApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ApiInstance.create(this, "http://www.omdbapi.com/")

        Hawk.init(this).build()

    }

    companion object {
        const val KEY_SAVED_MOVIES = "KEY_SAVED_MOVIES"
    }
}