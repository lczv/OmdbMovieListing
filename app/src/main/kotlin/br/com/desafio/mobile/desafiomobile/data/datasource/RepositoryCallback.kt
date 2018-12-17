package br.com.desafio.mobile.desafiomobile.data.datasource

interface RepositoryCallback {
    fun onSuccess(result: Any? = null)
    fun onFailure(errorResponse: ErrorResponse? = null)
}