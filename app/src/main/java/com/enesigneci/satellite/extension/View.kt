package com.enesigneci.satellite.extension

import android.text.Editable
import android.widget.EditText
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
fun EditText.debouncedChangeListener(): Flow<String> = callbackFlow {
    val textWatcher = object : android.text.TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            trySend(s.toString()).isSuccess
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }
    this@debouncedChangeListener.addTextChangedListener(textWatcher)
    awaitClose { this@debouncedChangeListener.removeTextChangedListener(textWatcher) }
}