package com.test.placeholdercats.cats.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.entities.ObjectResult
import com.test.entities.Cat
import com.test.usecases.interfaces.IUseCaseCat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelCat @Inject constructor(
    private val useCaseCat: IUseCaseCat,
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _cats = MutableLiveData<List<Cat>>()
    val cats: LiveData<List<Cat>> = _cats

    private val _notResults = MutableLiveData<Boolean>()
    val notResults: LiveData<Boolean> = _notResults

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    fun getCats() {
        viewModelScope.launch {
            _loading.value = true
            _notResults.value = false
            _error.value = false
            val response = useCaseCat.getCats()
            processCatResponse(response)
        }
    }

    private fun processCatResponse(response: ObjectResult<List<Cat>>) {
        _loading.value = false
        if (response is ObjectResult.Success) {
            response.data.let { cats ->
                if (cats.isNotEmpty()) {
                    _cats.value = cats
                } else {
                    _notResults.value = true
                }
            }
        } else if (response is ObjectResult.Failure) {
            _loading.value = false
            _error.value = true
        }
    }
}
