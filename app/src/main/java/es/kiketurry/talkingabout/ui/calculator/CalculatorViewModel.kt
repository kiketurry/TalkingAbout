package es.kiketurry.talkingabout.ui.calculator

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import es.kiketurry.talkingabout.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CalculatorViewModel(ioDispatcher: CoroutineDispatcher = Dispatchers.IO, application: Application) : BaseViewModel(ioDispatcher, application) {
    override val TAG: String? get() = CalculatorViewModel::class.qualifiedName

    var resultMutableLiveData: MutableLiveData<String> = MutableLiveData()

    var operatorOneFloat: Float = 0f
    var operatorTwoFloat: Float = 0f

    private fun operatorsStringToFloat(operatorOne: String, operatorTwo: String) {
        try {
            operatorOneFloat = operatorOne.toFloat()
            operatorTwoFloat = operatorTwo.toFloat()
        } catch (exception: NumberFormatException) {
            Log.e(TAG, "l> Se ha producido una excepción convirtiendo los operadores a tipo float: ${exception.message}")
            operatorOneFloat = 0f
            operatorTwoFloat = 0f
        }
    }

    fun add(operatorOne: String, operatorTwo: String) {
        operatorsStringToFloat(operatorOne, operatorTwo)
        resultMutableLiveData.postValue((operatorOneFloat + operatorTwoFloat).toString())
    }

    fun subtract(operatorOne: String, operatorTwo: String) {
        operatorsStringToFloat(operatorOne, operatorTwo)
        resultMutableLiveData.postValue((operatorOneFloat - operatorTwoFloat).toString())
    }

    fun multiply(operatorOne: String, operatorTwo: String) {
        operatorsStringToFloat(operatorOne, operatorTwo)
        resultMutableLiveData.postValue((operatorOneFloat * operatorTwoFloat).toString())
    }

    fun divide(operatorOne: String, operatorTwo: String) {
        operatorsStringToFloat(operatorOne, operatorTwo)
        resultMutableLiveData.postValue((operatorOneFloat / operatorTwoFloat).toString())
    }

    @Throws(ArithmeticException::class)
    fun dividePerZero(operatorOne: String, operatorTwo: String) {
        operatorsStringToFloat(operatorOne, operatorTwo)
        if (operatorTwoFloat == 0f) {
            throw ArithmeticException("No se puede dividir por cero.")
        }
        resultMutableLiveData.postValue((operatorOneFloat / operatorTwoFloat).toString())
    }

    fun addWithDeviation(operatorOne: Float, operatorTwo: Float): Float {
        return operatorOne + operatorTwo + 0.25f
    }

    fun largeOperation() {
        Thread.sleep(1000)
    }
}