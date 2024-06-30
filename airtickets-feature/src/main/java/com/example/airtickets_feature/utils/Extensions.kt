package com.example.airtickets_feature.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.coroutines.*

fun EditText.afterTextChanged(delayMillis: Long = 700, afterTextChanged: (String) -> Unit) {
    var job: Job? = null

    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            job?.cancel()
        }

        override fun afterTextChanged(s: Editable?) {
            job = CoroutineScope(Dispatchers.Main).launch {
                delay(delayMillis)
                afterTextChanged(s.toString().trim())
            }
        }
    })
}