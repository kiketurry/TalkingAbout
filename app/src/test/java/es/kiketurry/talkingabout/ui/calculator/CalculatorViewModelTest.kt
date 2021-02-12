package es.kiketurry.talkingabout.ui.calculator

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*


class CalculatorViewModelTest {
    //Para que puedan correr los test con viewmodels.
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    lateinit var calculatorViewModel: CalculatorViewModel

    //Se ejecuta una vez antes de lanzar todos los test a diferentecia de @before que se ejecuta antes de cada test.
    // Se suele usar para inicializar una base de datos con datos por ejemplo.
    // Los metodos marcados con esta anotación tienen que ser estaticos.
    //Si modificaras las variables statics, los cambios son acumulativos por lo que tendrías que tenerlo en cuenta en los tests.
    //****No es compatible con kotlin****  https://kotlinlang.org/api/latest/kotlin.test/kotlin.test/-before-class/
//    @BeforeClass
//    fun setupBeforeClass() {
//        calculatorViewModel = CalculatorViewModel(Application())
//        System.out.println("l> Se ha ejecutado el método con la etiqueta @BeforeClass")
//    }

    //@AfterClass //Con after class pasa lo mismo pero tampoco esta soportado.


    @Before //Método que se va a ejecutar antes de cada test. Normalmente para setear variables por eso se le suele llamar setup
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        calculatorViewModel = CalculatorViewModel(Dispatchers.IO, Application())
        System.out.println("l> Se ha ejecutado el método con la etiqueta @Before")
    }

    @After //Método que se va a ejecutar despues de cada test. Normalmente para liberar recursos, se le suele llamar tearDown
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
        System.out.println("l> Se ha ejecutado el método con la etiqueta @After")
    }

    @Test
    fun calculadoraNotNullTest() {
        Assert.assertNotNull("l> Calculadora debe ser not null", calculatorViewModel)
        System.out.println("l> Se ha ejecutado el test calculadoraNotNullTest")
    }

    @Test
    fun getTAG() {
        Assert.assertEquals("El TAG esta mal seteado", CalculatorViewModel::class.qualifiedName, calculatorViewModel.TAG)
        Assert.assertEquals(CalculatorViewModel::class.qualifiedName, calculatorViewModel.TAG)
        System.out.println("l> Se ha ejecutado el test calculadoraNullTest")
    }

    @Test
    fun add() {
        calculatorViewModel.add("10", "20")
        Assert.assertEquals("30.0", calculatorViewModel.resultMutableLiveData.value)
        System.out.println("l> Se ha ejecutado el test add Test")
    }

    @Test
    fun `add observe test`() {
        val observerMock = mock<Observer<String>>()
        testDispatcher.runBlockingTest {
            calculatorViewModel.resultMutableLiveData.observeForever(observerMock)
            calculatorViewModel.add("4", "9")
            verify(observerMock).onChanged("13.0")
        }
    }

    @Test
    fun subtract() {
        calculatorViewModel.subtract("10", "20")
        Assert.assertEquals("-10.0", calculatorViewModel.resultMutableLiveData.value)
        System.out.println("l> Se ha ejecutado el test subtract Test")
    }

    @Test
    fun `subtract observe test`() {
        val observerMock = mock<Observer<String>>()
        testDispatcher.runBlockingTest {
            calculatorViewModel.resultMutableLiveData.observeForever(observerMock)
            calculatorViewModel.subtract("4", "9")
            verify(observerMock).onChanged("-5.0")
        }
    }

    @Test
    fun addWithDeviation() {
        Assert.assertEquals(
            30f,
            calculatorViewModel.addWithDeviation(10f, 20f),
            0.5f
        )// El delta es curioso deja un margen de desviación por encima o por debajo del esperado por si alguna operación matematica aceptara cierta desviación cómo válida.
        System.out.println("l> Se ha ejecutado el test add Test")
    }


    @Test
    fun multiply() {
        calculatorViewModel.multiply("2.5", "3.2")
        Assert.assertEquals("8.0", calculatorViewModel.resultMutableLiveData.value)
        System.out.println("l> Se ha ejecutado el test multiply Test")
    }

    @Test
    fun `multiply observe test`() {
        val observerMock = mock<Observer<String>>()
        testDispatcher.runBlockingTest {
            calculatorViewModel.resultMutableLiveData.observeForever(observerMock)
            calculatorViewModel.multiply("4", "9")
            verify(observerMock).onChanged("36.0")
        }
    }

    @Test
    fun divide() {
        calculatorViewModel.divide("10", "4")
        Assert.assertEquals("2.5", calculatorViewModel.resultMutableLiveData.value)
        System.out.println("l> Se ha ejecutado el test divide() Test")
    }

    @Test
    fun `divide observe test`() {
        val observerMock = mock<Observer<String>>()
        testDispatcher.runBlockingTest {
            calculatorViewModel.resultMutableLiveData.observeForever(observerMock)
            calculatorViewModel.divide("10", "4")
            verify(observerMock).onChanged("2.5")
        }
    }

    @Test(expected = ArithmeticException::class) //Con expected decimos al metodo la excepción que se espera para que lo marque como bueno si se recibe esa excepción.
    fun dividePerZero() {
        calculatorViewModel.dividePerZero("10", "0")
        System.out.println("l> Se ha ejecutado el test dividePerZero() Test")
    }

    @Test(timeout = 1050) //Tiempo máximo que se le da a un test para ejecutarse.
    fun operationLarteTimeout() {
        calculatorViewModel.largeOperation()
    }

    @Ignore("Método no listo. Ignorar por ahora. Esperando solucionar la división por cero")
    @Test
    fun dividePerZeroWithoutControl() {
        calculatorViewModel.divide("10", "0")
        Assert.assertEquals("NaN", calculatorViewModel.resultMutableLiveData.value)
        System.out.println("l> Se ha ejecutado el test divide() Test")
    }
}