package es.kiketurry.talkingabout.ui.calculator

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class CalculatorParameterizedTest(var numberOne: String, var numberTwo: String, var result: String) {
    //Para que puedan correr los test con viewmodels.
    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var calculatorViewModel: CalculatorViewModel

    companion object {
        @JvmStatic //Clave si no no tira.
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf("6", "2", "3.0"),
                arrayOf("6", "-2", "-3.0"),
                arrayOf("-6", "2", "-3.0"),
                arrayOf("-6", "-2", "3.0"),
                arrayOf("6", "0", "Infinity"),
                arrayOf("10", "2", "5.0")
            )
        }
    }

    @Before //Método que se va a ejecutar antes de cada test. Normalmente para setear variables por eso se le suele llamar setup
    fun setup() {
        calculatorViewModel = CalculatorViewModel(Dispatchers.IO, Application())
        System.out.println("l> Se ha ejecutado el método con la etiqueta @Before")
    }

    @Test
    fun dividirTest() {
        calculatorViewModel.divide(numberOne, numberTwo)
        Assert.assertEquals(result, calculatorViewModel.resultMutableLiveData.value)
    }

}