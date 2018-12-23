package br.com.desafio.mobile.desafiomobile

import android.app.Application
import br.com.desafio.mobile.desafiomobile.data.datasource.ApiInstance
import com.orhanobut.hawk.Hawk
import io.appflate.restmock.RESTMockServer

class CustomTestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

//        ApiInstance.create(this, RESTMockServer.getUrl())

        Hawk.init(this).build()
    }
}