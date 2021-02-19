package es.kiketurry.talkingabout.utils

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import es.kiketurry.talkingabout.extensions.toast

class WhatsappUtils {
    companion object {
        private val TAG: String? = WhatsappUtils::class.simpleName

        fun sendWhatsapp(activity: AppCompatActivity, nameThingBGG: String) {
            val whatsappInstalled: Boolean = appInstalled(activity, "com.whatsapp")
            val mobileNumber = 34666777888
            val messageWhatsapp = "He descubierto que tienes el juego: '$nameThingBGG', lo probamos???"

            if (whatsappInstalled) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("http://api.whatsapp.com/send?phone=$mobileNumber&text=$messageWhatsapp")
                activity.startActivity(intent)
            } else {
                activity.toast("Whatsapp not installed on your device")
            }
        }

        private fun appInstalled(activity: AppCompatActivity, packageString: String): Boolean {
            return try {
                activity.packageManager.getPackageInfo(packageString, PackageManager.GET_ACTIVITIES)
                true
            } catch (exception: Exception) {
                Log.w(TAG, "l> Problemas revisando si la app esta instalada: $exception.message")
                false
            }
        }
    }
}