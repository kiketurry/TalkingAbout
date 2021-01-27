package es.kiketurry.talkingabout.ui.dialogfragment.info

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import es.kiketurry.talkingabout.databinding.FragmentDialogInfoBinding
import es.kiketurry.talkingabout.extensions.gone

class InfoDialogFragment : DialogFragment() {

    interface InfoDialogFragmentClickButtonListener {
        fun onInfoDialogFragmentClickPositive()
        fun onInfoDialogFragmentClickNegative()
    }

    companion object {
        const val INFO_DIALOG_FRAGMENT_TAG: String = "INFO_DIALOG_FRAGMENT_TAG"
    }

    private lateinit var binding: FragmentDialogInfoBinding
    private lateinit var title: String
    private lateinit var message: String
    private lateinit var infoDialogFragmentClickButtonListener: InfoDialogFragmentClickButtonListener

    private var drawableIcon: Drawable? = null
    private var textButtonPositive: String? = null
    private var textButtonNegative: String? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        binding = FragmentDialogInfoBinding.inflate(LayoutInflater.from(context));
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setOnKeyListener(DialogInterface.OnKeyListener { _, keyCode, _ -> return@OnKeyListener (keyCode == KeyEvent.KEYCODE_BACK) })

        showErrorInfo()

        return dialog
    }

    private fun showErrorInfo() {
        binding.tvTitle.text = title

        drawableIcon?.let {
            binding.ivIcon.setImageDrawable(it)
        }

        binding.tvMesagge.text = message

        if (textButtonPositive != null) {
            binding.btPositive.text = textButtonPositive
            binding.btPositive.setOnClickListener {
                infoDialogFragmentClickButtonListener.onInfoDialogFragmentClickPositive()
                dismiss()
            }
        } else {
            binding.btPositive.gone()
        }
        if (textButtonNegative != null) {
            binding.btNegative.text = textButtonNegative
            binding.btNegative.setOnClickListener {
                infoDialogFragmentClickButtonListener.onInfoDialogFragmentClickNegative()
                dismiss()
            }
        } else {
            binding.btNegative.gone()
        }
    }

    fun setValue(
        infoDialogFragmentClickButtonListener: InfoDialogFragmentClickButtonListener,
        title: String,
        message: String,
        drawableIcon: Drawable? = null,
        textButtonPositive: String? = null,
        textButtonNegative: String? = null
    ) {
        this.infoDialogFragmentClickButtonListener = infoDialogFragmentClickButtonListener
        this.title = title
        this.message = message
        this.drawableIcon = drawableIcon
        this.textButtonPositive = textButtonPositive
        this.textButtonNegative = textButtonNegative
    }
}