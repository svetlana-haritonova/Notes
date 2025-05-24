package ru.haritonova.saturday10.presentation.model

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.haritonova.saturday10.data.api.NotesApi
import ru.haritonova.saturday10.data.api.SignInRequest

class AuthViewModel(
    private val api: NotesApi,
    private val prefs: SharedPreferences
) : ViewModel() {

    fun login(
        login: String,
        password: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) = viewModelScope.launch {
        try {
            val response = api.login(SignInRequest(login, password))
            prefs.edit().putString("token", response.token).apply()
            onSuccess(response.token)
        } catch (e: Exception) {
            onError("Ошибка входа: ${e.message}")
        }
    }
}
