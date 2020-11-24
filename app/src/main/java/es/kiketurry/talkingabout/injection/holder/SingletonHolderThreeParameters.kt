package es.kiketurry.talkingabout.injection.holder

open class SingletonHolderThreeParameters<out T : Any, in A, B, C>(creator: (A, B, C) -> T) {
    private var creator: ((A, B, C) -> T)? = creator
    @Volatile
    private var instance: T? = null

    fun getInstance(argOne: A, argTwo: B, argThree: C): T {
        val checkInstance = instance
        if (checkInstance != null) {
            return checkInstance
        }

        return synchronized(this) {
            val checkInstanceAgain = instance
            if (checkInstanceAgain != null) {
                checkInstanceAgain
            } else {
                val created = creator!!(argOne, argTwo, argThree)
                instance = created
                creator = null
                created
            }
        }
    }
}