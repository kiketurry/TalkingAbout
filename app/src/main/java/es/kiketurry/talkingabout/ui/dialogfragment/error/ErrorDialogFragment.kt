package es.kiketurry.talkingabout.ui.dialogfragment.error

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel
import es.kiketurry.talkingabout.databinding.FragmentDialogErrorBinding
import es.kiketurry.talkingabout.databinding.FragmentDialogLoadingBinding

class ErrorDialogFragment : DialogFragment() {
    companion object {
        const val ERROR_DIALOG_FRAGMENT_TAG: String = "ERROR_DIALOG_FRAGMENT_TAG"
    }

    private lateinit var errorModel: ErrorModel
    private lateinit var binding: FragmentDialogErrorBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        binding = FragmentDialogErrorBinding.inflate(LayoutInflater.from(context));
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setOnKeyListener(DialogInterface.OnKeyListener { _, keyCode, _ -> return@OnKeyListener (keyCode == KeyEvent.KEYCODE_BACK) })

        showErrorInfo()

        binding.btAcceptError.setOnClickListener { dismiss() }

        return dialog
    }

    private fun showErrorInfo() {
        binding.tvTitleError.text = errorModel.message
        binding.tvMesaggeError.text = errorModel.message
        if(errorModel.title.isNullOrEmpty()){
            binding.tvTitleError.visibility = GONE
        }
    }

    fun setError(errorModel: ErrorModel) {
        this.errorModel = errorModel
    }

    fun setErrorAndRefreshInfo(errorModel: ErrorModel) {
        this.errorModel = errorModel
        showErrorInfo()
    }
}