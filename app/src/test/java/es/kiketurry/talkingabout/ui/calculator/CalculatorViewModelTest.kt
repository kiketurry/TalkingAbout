package es.kiketurry.talkingabout.ui.calculator

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.*


class CalculatorViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

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
        calculatorViewModel = CalculatorViewModel(Application())
        System.out.println("l> Se ha ejecutado el método con la etiqueta @Before")
    }

    @Test
    fun calculadoraNotNullTest() {
        Assert.assertNotNull("l> Calculadora debe ser not null", calculatorViewModel)
        System.out.println("l> Se ha ejecutado el test calculadoraNotNullTest")
    }

    @Test
    fun getTAG() {
        Assert.assertEquals("El TAG esta mal seteado", calculatorViewModel.TAG, CalculatorViewModel::class.qualifiedName)
        Assert.assertEquals(calculatorViewModel.TAG, CalculatorViewModel::class.qualifiedName)
        System.out.println("l> Se ha ejecutado el test calculadoraNullTest")
    }

    @Test
    fun add() {
        Assert.assertEquals(calculatorViewModel.add("10", "20"), 30f)
        System.out.println("l> Se ha ejecutado el test add Test")
    }

    @Test
    fun subtract() {
        Assert.assertEquals(calculatorViewModel.subtract("10", "20"), -10f)
        Assert.assertEquals(
            calculatorViewModel.subtract("10", "20"),
            -9.5f,
            0.5f
        )// El delta es curioso deja un margen de desviación por encima o por debajo del esperado por si alguna operación matematica aceptara cierta desviación cómo válida.
        System.out.println("l> Se ha ejecutado el test subtract Test")
    }

    @Test
    fun multiply() {
        Assert.assertEquals(calculatorViewModel.multiply("2.5", "3.2"), 8f)
        System.out.println("l> Se ha ejecutado el test multiply Test")
    }

    @Test
    fun divide() {
        Assert.assertEquals(calculatorViewModel.divide("10", "4"), 10f / 4f)
        System.out.println("l> Se ha ejecutado el test divide() Test")
    }

    @After //Método que se va a ejecutar despues de cada test. Normalmente para liberar recursos, se le suele llamar tearDown
    fun tearDown() {
        System.out.println("l> Se ha ejecutado el método con la etiqueta @After")
    }

    @Test(expected = ArithmeticException::class) //Con expected decimos al metodo la excepción que se espera para que lo marque como bueno si se recibe esa excepción.
    fun dividePerZero() {
        calculatorViewModel.dividePerZero("10", "0")
        System.out.println("l> Se ha ejecutado el test dividePerZero() Test")
    }

    @Test(timeout = 1010) //Tiempo máximo que se le da a un test para ejecutarse.
    fun operationLarteTimeout() {
        calculatorViewModel.largeOperation()
    }

    @Ignore("Método no listo. Ignorar por ahora. Esperando solucionar la división por cero")
    @Test
    fun dividePerZeroWithoutControl() {
        Assert.assertEquals(calculatorViewModel.divide("10", "0"), 10f / 0f)
        System.out.println("l> Se ha ejecutado el test divide() Test")
    }
}