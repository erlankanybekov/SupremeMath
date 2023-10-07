package com.example.suprememath.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suprememath.utils.Either
import com.example.suprememath.utils.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * Базовый класс для всех ViewModel, который расширяет [ViewModel] из Android Jetpack.
 *
 * @author Erlan
 * @since 1.0v
 */
abstract class BaseViewModel : ViewModel() {
    /**
     * Создает [MutableStateFlow] и возвращает его.
     * Метод используется для сохранения состояния экрана, которое будет использоваться в LiveData/StateFlow
     * @return [MutableStateFlow] для хранения состояния экрана.
     */
    protected fun <T> mutableUiStateFlow() = MutableStateFlow<UIState<T>>(UIState.Idle())

    /**
     * Собирает результаты запроса [Either] и обрабатывает их в [UIState].
     * @param state [MutableStateFlow] [UIState] для обработки состояний загрузки данных.
     * @param mappedData Функция, которая принимает данные типа [T] и возвращает данные типа [S].
     * @return [Unit].
     */
    protected fun <T, S> Flow<Either<String, T>>.gatherRequest(
        state: MutableStateFlow<UIState<S>>,
        mappedData: (data: T) -> S
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = UIState.Loading()
            this@gatherRequest.collect {
                when (it) {
                    is Either.Left -> state.value = UIState.Error(it.value)
                    is Either.Right -> state.value = UIState.Success(mappedData(it.value))
                }
            }
        }
    }
}