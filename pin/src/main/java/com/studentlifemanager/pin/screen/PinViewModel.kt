package com.studentlifemanager.pin.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studentlifemanager.common.data.entity.PinEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This is the viewModel for PIN Fragment
 * Here we are managing the pin view related data
 *
 *
 * @author SandeepK
 * @version 2.0
 *
 * */

@HiltViewModel
class PinViewModel @Inject constructor(private val repository: PinRepository) : ViewModel() {

    private val viewModelSate = MutableStateFlow(PinScreenUiState(PinUiState.Loading))

    // response is returned in the form of state
    // in compose state is used to update the UI
    val uiState = viewModelSate.map {
        it.pinUiState
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        viewModelSate.value.pinUiState
    )

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     * used to insert the user pin in pin table
     *
     * @param pinEntity : this is the model class object which
     * has all information
     */
    fun insert(pinEntity: PinEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val isInserted = async { repository.insert(pinEntity) }
            isInserted.await()
            if (isInserted.isCompleted) {
                getPinData()
            }
        }
    }

    /**
     * To get pin data, which will in be the list
     * of PinEntity model
     */
    fun getPinData() {

        // get pin data
        viewModelScope.launch(Dispatchers.IO) {
            val pinData = repository.getAllPins()
            pinData.collect {
                viewModelSate.update { state ->
                    state.copy(
                        pinUiState = PinUiState.Success(it)
                    )
                }
            }
        }

    }

    sealed interface PinUiState {
        data class Success(val pinList: List<PinEntity>) : PinUiState
        data class Error(val throwable: Throwable) : PinUiState
        object Loading : PinUiState
    }

    data class PinScreenUiState(
        val pinUiState: PinUiState
    )
}