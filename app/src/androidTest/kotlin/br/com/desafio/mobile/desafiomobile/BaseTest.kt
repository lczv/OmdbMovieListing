package br.com.desafio.mobile.desafiomobile

import androidx.test.platform.app.InstrumentationRegistry
import br.com.desafio.mobile.desafiomobile.data.datasource.ApiInstance
import okhttp3.mockwebserver.MockWebServer
import org.junit.BeforeClass

open class BaseTest {
    companion object {
        lateinit var mockWebServer: MockWebServer

        @BeforeClass
        @JvmStatic
        fun setupMockServer() {
            mockWebServer = MockWebServer()
            ApiInstance.create(InstrumentationRegistry.getInstrumentation().context, mockWebServer.url("/").toString())
        }
    }
}