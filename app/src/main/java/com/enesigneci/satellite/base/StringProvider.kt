package com.enesigneci.satellite.base

import android.content.Context
import javax.inject.Inject

class StringProvider @Inject constructor(private val context: Context) : Provider<String> {
    override fun get(id: Int): String {
        return context.getString(id)
    }

}