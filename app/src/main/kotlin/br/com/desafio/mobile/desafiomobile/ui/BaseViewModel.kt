package br.com.desafio.mobile.desafiomobile.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.desafio.mobile.desafiomobile.data.datasource.ErrorResponse

open class BaseViewModel: ViewModel(){
    val errorStatus = MutableLiveData<ErrorResponse?>()

    fun clearErrorStatus(){
        errorStatus.postValue(null)
    }
}