package br.com.desafio.mobile.desafiomobile

import android.app.Application
import android.content.Context
import io.appflate.restmock.android.RESTMockTestRunner

class CustomTestRunner : RESTMockTestRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, CustomTestApplication::class.java.name, context)
    }
}