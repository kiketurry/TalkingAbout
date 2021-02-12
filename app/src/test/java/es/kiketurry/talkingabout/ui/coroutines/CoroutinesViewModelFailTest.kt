package es.kiketurry.talkingabout.ui.coroutines

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CoroutinesViewModelFailTest {
    //Obliga a todos los livedata en los test a ejecutarse en el hilo de test en vez de en el hilo principal de android que no existe en los test y entonces el test finalizaría antes de observar nada y daría una excepción.
    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var coroutinesViewModel: CoroutinesViewModel

    @Before //Método que se va a ejecutar antes de cada test. Normalmente para setear variables por eso se le suele llamar setup
    fun setup() {
        coroutinesViewModel = CoroutinesViewModel(application = Application())
        System.out.println("l> Se ha ejecutado el método con la etiqueta @Before")
    }

    //Prueba test fail
    @Test
    fun `success if observe return true test force fail`() {
        val observerMock = mock<Observer<Boolean>>()
        coroutinesViewModel.resultMutableLiveData.observeForever(observerMock)
        coroutinesViewModel.comeOn()
        verify(observerMock).onChanged(true)
    }

}