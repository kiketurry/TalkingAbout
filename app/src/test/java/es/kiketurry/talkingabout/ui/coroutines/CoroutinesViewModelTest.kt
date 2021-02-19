package es.kiketurry.talkingabout.ui.coroutines

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*

@ExperimentalCoroutinesApi
class CoroutinesViewModelTest {
    //En esta clase testeamos coroutines con creando el testDispatcher y teniendo cuidado en el setup y el tearDown de establecerlo como dispatchers y finalmente resetearlo.
    //En el futuro veremos como hacer esto de forma más simple creando una regla propia CoroutinesTestRule.


    //Obliga a todos los livedata en los test a ejecutarse en el hilo de test en vez de en el hilo principal de android que no existe en los test y entonces el test finalizaría antes de observar nada y daría una excepción.
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    lateinit var coroutinesViewModel: CoroutinesViewModel

    @Before //Método que se va a ejecutar antes de cada test. Normalmente para setear variables por eso se le suele llamar setup
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        coroutinesViewModel = CoroutinesViewModel(testDispatcher, Application())
        System.out.println("l> Se ha ejecutado el método con la etiqueta @Before")
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    //Con comillas invertidas se pueden usar nombres de metodos más humanos para luego verlos e identificarlos en el listado mejor, pero solo funciona en los test unitarios, no en los de integración.
    @Test
    fun `success if observe return true`() {
        val observerMock = mock<Observer<Boolean>>()
        testDispatcher.runBlockingTest {
            coroutinesViewModel.resultMutableLiveData.observeForever(observerMock)
            coroutinesViewModel.comeOn()
            verify(observerMock).onChanged(true)
        }
    }

    @Test
    fun `success if observe return true not mock observer`() {
        val myObserver = Observer<Boolean> {
            Assert.assertTrue("", it)
        }
        testDispatcher.runBlockingTest {
            coroutinesViewModel.resultMutableLiveData.observeForever(myObserver)
            coroutinesViewModel.comeOn()
        }
    }

}