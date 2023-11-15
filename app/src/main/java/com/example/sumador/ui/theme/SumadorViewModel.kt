package com.example.sumador.ui.theme

import androidx.lifecycle.ViewModel
import com.example.sumador.data.SumadorUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SumadorViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(SumadorUiState(sumador1 = 0,
        sumador2 = 0, isShowingFirstPage = true))
    val uiState: StateFlow<SumadorUiState> = _uiState

    fun navigateToFirstPage() {
        _uiState.update {
            it.copy(isShowingFirstPage = true)
        }
    }


    fun navigateToSecondPage() {
        _uiState.update {
            it.copy(isShowingFirstPage = false)
        }
    }

    fun updateSumadores(S1: Int, S2: Int) {
        navigateToSecondPage()
        _uiState.update {
            it.copy(sumador1 = S1,
                sumador2 = S2)
        }
    }
}
