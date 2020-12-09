package es.kiketurry.talkingabout.ui.dialogfragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import es.kiketurry.talkingabout.databinding.FragmentDialogErrorBinding

class TextButtonDialogFragment : DialogFragment() {

    interface TextButtonDialogFragmentClickButtonListener {
        fun onTextButtonDialogFragmentClickButton()
    }

    companion object {
        const val TEXT_BUTTON_DIALOG_FRAGMENT_TAG: String = "TEXT_BUTTON_DIALOG_FRAGMENT_TAG"
    }

    private lateinit var binding: FragmentDialogErrorBinding

    private lateinit var text: String
    private lateinit var textButton: String
    private lateinit var textButtonDialogFragmentClickButtonListener: TextButtonDialogFragmentClickButtonListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        binding = FragmentDialogErrorBinding.inflate(LayoutInflater.from(context));
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setOnKeyListener(DialogInterface.OnKeyListener { _, keyCode, _ -> return@OnKeyListener (keyCode == KeyEvent.KEYCODE_BACK) })

        showErrorInfo()

//        binding.btAcceptError.setOnClickListener { dismiss() }

        return dialog
    }

    private fun showErrorInfo() {
        binding.tvTitleError.text = text
        binding.tvMesaggeError.text = textButton
        binding.btAcceptError.setOnClickListener {
            Log.i("TextButtonDialogFragmen", "l> vamos a llamar al listener")
            textButtonDialogFragmentClickButtonListener.onTextButtonDialogFragmentClickButton()
        }
    }

    fun setValue(
        text: String,
        textButton: String,
        textButtonDialogFragmentClickButtonListener: TextButtonDialogFragmentClickButtonListener
    ) {
        this.text = text
        this.textButton = textButton
        this.textButtonDialogFragmentClickButtonListener = textButtonDialogFragmentClickButtonListener
    }
}