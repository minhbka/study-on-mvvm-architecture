package com.minhbka.studyonmvvmarchitecture.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
private const val KEY_SAVE_AT = "key_saved_at"
class PreferenceProvider(
    context:Context
) {
    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun savelastSaveAt(savedAt:String){
        preference.edit().putString(KEY_SAVE_AT, savedAt)
            .apply()
    }

    fun getlastSavedAt():String?{
        return preference.getString(KEY_SAVE_AT, null)
    }
}