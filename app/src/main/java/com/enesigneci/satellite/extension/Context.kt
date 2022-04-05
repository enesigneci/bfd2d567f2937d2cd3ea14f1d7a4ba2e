package com.enesigneci.satellite.extension

import android.app.AlertDialog
import android.content.Context
import com.enesigneci.satellite.R

fun Context.showErrorDialog(message: String) {
    AlertDialog.Builder(this)
        .setTitle(getString(R.string.error_dialog_description_text))
        .setMessage(message)
        .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
            dialog?.dismiss()
        }
        .create()
        .show()
}
