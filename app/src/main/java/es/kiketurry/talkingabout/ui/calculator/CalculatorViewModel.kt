package es.kiketurry.talkingabout.ui.calculator

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import es.kiketurry.talkingabout.ui.base.BaseViewModel

class CalculatorViewModel(application: Application) : BaseViewModel(application) {
    override val TAG: String? get() = CalculatorViewModel::class.qualifiedName

    var resultMutableLiveData: MutableLiveData<String> = MutableLiveData()

    var operatorOneFloat: Float = 0f
    var operatorTwoFloat: Float = 0f
    var result: Float = 0f

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

    fun add(operatorOne: String, operatorTwo: String): Float {
        operatorsStringToFloat(operatorOne, operatorTwo)
        result = operatorOneFloat + operatorTwoFloat
        resultMutableLiveData.postValue(result.toString())
        return result
    }

    fun subtract(operatorOne: String, operatorTwo: String): Float {
        operatorsStringToFloat(operatorOne, operatorTwo)
        result = operatorOneFloat - operatorTwoFloat
        resultMutableLiveData.postValue(result.toString())
        return result
    }

    fun multiply(operatorOne: String, operatorTwo: String): Float {
        operatorsStringToFloat(operatorOne, operatorTwo)
        result = operatorOneFloat * operatorTwoFloat
        resultMutableLiveData.postValue(result.toString())
        return result
    }

    fun divide(operatorOne: String, operatorTwo: String): Float {
        operatorsStringToFloat(operatorOne, operatorTwo)
        result = operatorOneFloat / operatorTwoFloat
        resultMutableLiveData.postValue(result.toString())
        return result
    }

    @Throws(ArithmeticException::class)
    fun dividePerZero(operatorOne: String, operatorTwo: String): Float {
        operatorsStringToFloat(operatorOne, operatorTwo)
        if (operatorTwoFloat == 0f) {
            throw ArithmeticException("No se puede dividir por cero.")
        }
        result = operatorOneFloat / operatorTwoFloat
        resultMutableLiveData.postValue(result.toString())
        return result
    }

    fun largeOperation() {
        Thread.sleep(1000)
    }
}