package com.example.suprememath.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.suprememath.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Базовый класс фрагмента с общими методами и свойствами для всех фрагментов в приложении.
 * @param ViewModel класс ViewModel, используемой в фрагменте.
 * @param Binding класс ViewBinding, используемой в фрагменте.
 * @param layoutId идентификатор макета фрагмента.
 *
 * @author Erlan
 * @since 1.0v
 */
abstract class BaseFragment<ViewModel : BaseViewModel, Binding : ViewBinding>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    /**
     * Абстрактное свойство, возвращающее экземпляр ViewModel, используемый в фрагменте.
     */
    protected abstract val viewModel: ViewModel

    /**
     * Абстрактное свойство, возвращающее экземпляр ViewBinding, используемый в фрагменте.
     */
    protected abstract val binding: Binding
    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        establishRequest()
        initListeners()
    }

    /**
     * Вызывается при создании фрагмента для инициализации.
     */
    protected open fun initialize() {}
    protected open fun establishRequest() {}
    protected open fun initListeners() {}

//    /**
//     * Собирает данные [PagingData] из [Flow] и выполняет действие [action].
//     * @param lifecycleState состояние жизненного цикла, на котором будет происходить сбор данных.
//     * @param action действие, которое будет выполняться с [PagingData].
//     */
//    protected fun <T : Any> Flow<PagingData<T>>.collectPaging(
//        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
//        action: suspend (value: PagingData<T>) -> Unit
//    ) {
//        safeFlowGather(lifecycleState) { this.collectLatest { action(it) } }
//    }

    /**
     * Безопасно собирает [Flow] и выполняет действие [action].
     * @param lifecycleState состояние жизненного цикла, на котором будет происходить сбор данных.
     * @param action действие, которое будет выполняться при сборе данных.
     */
    protected fun safeFlowGather(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED, action: suspend () -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
                action()
            }
        }
    }

    /**
     * Этот метод служит для наблюдения за состоянием пользовательского интерфейса (UIState) с
     * помощью StateFlow. Он принимает несколько параметров, включая жизненный цикл, состояния UI,
     * функции обратного вызова для каждого состояния и функцию [gatherIfSucceed], которая будет вызвана
     * каждый раз, когда UI переходит в состояние успешного завершения (Success).
     *
     * @param lifecycleState: состояние жизненного цикла для использования при наблюдении за
     * StateFlow. По умолчанию установлено на [Lifecycle.State.STARTED].
     * @param success: функция обратного вызова, которая вызывается при переходе UI в
     * состояние успешного завершения (Success). Принимает параметр data типа T.
     * @param loading: функция обратного вызова, которая вызывается при переходе UI в состояние
     * загрузки (Loading). Принимает параметр data типа UIState.Loading<T>.
     * @param error: функция обратного вызова, которая вызывается при переходе UI в состояние
     * ошибки (Error). Принимает параметр error типа String.
     * @param idle: функция обратного вызова, которая вызывается при переходе UI в состояние простоя
     * (Idle). Принимает параметр idle типа UIState.Idle<T>.
     * @param gatherIfSucceed: функция обратного вызова, которая вызывается каждый раз, когда UI
     * переходит в состояние успешного завершения (Success). Принимает параметр state типа UIState<T>.
     */
    protected fun <T> StateFlow<UIState<T>>.spectateUiState(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        success: ((data: T) -> Unit)? = null,
        loading: ((data: UIState.Loading<T>) -> Unit)? = null,
        error: ((error: String) -> Unit)? = null,
        idle: ((idle: UIState.Idle<T>) -> Unit)? = null,
        gatherIfSucceed: ((state: UIState<T>) -> Unit)? = null,
    ) {
        safeFlowGather(lifecycleState) {
            collect {
                gatherIfSucceed?.invoke(it)
                when (it) {
                    is UIState.Idle -> {
                        idle?.invoke(it)
                    }

                    is UIState.Loading -> {
                        loading?.invoke(it)
                    }

                    is UIState.Error -> {
                        error?.invoke(it.error)
                    }

                    is UIState.Success -> {
                        success?.invoke(it.data)
                    }
                }
            }
        }
    }
}