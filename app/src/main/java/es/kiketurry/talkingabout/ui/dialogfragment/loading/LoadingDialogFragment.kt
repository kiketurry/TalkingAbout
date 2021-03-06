package es.kiketurry.talkingabout.ui.dialogfragment.loading

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.databinding.FragmentDialogLoadingBinding
import java.util.*

class LoadingDialogFragment : DialogFragment() {
    companion object {
        const val LOADING_DIALOG_FRAGMENT_TAG: String = "LOADING_DIALOG_FRAGMENT_TAG"
    }

    private lateinit var binding: FragmentDialogLoadingBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        binding = FragmentDialogLoadingBinding.inflate(LayoutInflater.from(context));
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        if (Random().nextBoolean()) {
            binding.ivLoadign.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.loading_rotate_clockwise))
        } else {
            binding.ivLoadign.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.loading_rotate_anticlockwise))
        }
        dialog.setOnKeyListener(DialogInterface.OnKeyListener { _, keyCode, _ -> return@OnKeyListener (keyCode == KeyEvent.KEYCODE_BACK) })
        dialog.window?.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

    override fun getTheme(): Int {
        return android.R.style.Theme_DeviceDefault_NoActionBar
    }
}