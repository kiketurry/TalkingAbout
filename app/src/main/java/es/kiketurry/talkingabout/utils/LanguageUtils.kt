package es.kiketurry.talkingabout.utils

import android.util.Log
import com.google.mlkit.nl.languageid.LanguageIdentification
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel
import es.kiketurry.talkingabout.data.repository.bbdd.AppDatabase

class LanguageUtils {

    companion object {
        private val TAG: String? = LanguageUtils::class.simpleName

        fun getSpanishStringFromList(appDatabase: AppDatabase, thingBGGModel: ThingBGGModel): String {
            var spanishString = ""

            return spanishString
        }

        private fun detectLanguage(text: String) {
            val languageIdentifier = LanguageIdentification.getClient()
            languageIdentifier.identifyLanguage(text)
                .addOnSuccessListener { languageCode ->
                    if (languageCode == "und") {
                        Log.i(TAG, "l> Can't identify language, $text")
                    } else {
                        Log.i(TAG, "l> Language, $text -> $languageCode")
                    }
                }
                .addOnFailureListener {
                    Log.i(TAG, "l> detectLanguage error con el texto, $text: ${it.message}")
                }
        }

        private fun posibleLanguage(text: String) {
            var language = ""
            var confidence: Float
            val languageIdentifier = LanguageIdentification.getClient()
            languageIdentifier.identifyPossibleLanguages(text)
                .addOnSuccessListener { identifiedLanguages ->
                    for (identifiedLanguage in identifiedLanguages) {
                        language = identifiedLanguage.languageTag
                        confidence = identifiedLanguage.confidence
                        Log.i(TAG, "l> posible lenguaje: $language confianza de: $confidence")
                    }
                }
                .addOnFailureListener {
                    Log.i(TAG, "l> posibleLanguage error con el texto, $text: ${it.message}")
                }
        }

    }
}