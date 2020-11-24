package es.kiketurry.talkingabout.injection.holder

open class SingletonHolderTwoParameters<out T : Any, in A, B>(creator: (A, B) -> T) {
    private var creator: ((A, B) -> T)? = creator
    @Volatile
    private var instance: T? = null

    fun getInstance(argOne: A, argTwo: B): T {
        val checkInstance = instance
        if (checkInstance != null) {
            return checkInstance
        }

        return synchronized(this) {
            val checkInstanceAgain = instance
            if (checkInstanceAgain != null) {
                checkInstanceAgain
            } else {
                val created = creator!!(argOne, argTwo)
                instance = created
                creator = null
                created
            }
        }
    }
}