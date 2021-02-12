package es.kiketurry.talkingabout.ui.coroutines

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import es.kiketurry.talkingabout.ui.supportclasses.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CoroutinesViewModelRuleTest {
    //En esta clase testeamos coroutines con nuestra regla CoroutinesTestRule que nos facilita la vida a la hora de establecer el dispatcher.

    //Obliga a todos los livedata en los test a ejecutarse en el hilo de test en vez de en el hilo principal de android que no existe en los test y entonces el test finalizaría antes de observar nada y daría una excepción.
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    lateinit var coroutinesViewModel: CoroutinesViewModel

    @Before //Método que se va a ejecutar antes de cada test. Normalmente para setear variables por eso se le suele llamar setup
    fun setup() {
        coroutinesViewModel = CoroutinesViewModel(coroutinesTestRule.testDispatcher, Application())
        System.out.println("l> Se ha ejecutado el método con la etiqueta @Before")
    }

    //Con comillas invertidas se pueden usar nombres de metodos más humanos para luego verlos e identificarlos en el listado mejor, pero solo funciona en los test unitarios, no en los de integración.
    @Test
    fun `success if observe return true test`() {
        val observerMock = mock<Observer<Boolean>>()
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coroutinesViewModel.resultMutableLiveData.observeForever(observerMock)
            coroutinesViewModel.comeOn()
            verify(observerMock).onChanged(true)
        }
    }

}