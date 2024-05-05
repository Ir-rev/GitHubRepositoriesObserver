package ru.marina.githubrepositoriesobserver.database

import android.content.Context
import android.content.SharedPreferences
import java.lang.IllegalStateException
import javax.inject.Inject
import javax.inject.Singleton
import ru.marina.githubrepositoriesobserver.Const

private const val APP_PREFERENCES = "mySetting"
private const val TOKEN_KEY = "myToken"
private const val ERROR_DB = "errorDB"

@Singleton
class DatabaseSaveToken @Inject constructor() {

    //метод гет токен и сет токен
    var db: SharedPreferences? = null

    fun getToken(): String {
        val currentBd = db ?: throw IllegalStateException(ERROR_DB)
        return currentBd.getString(TOKEN_KEY, "") ?: ""
    }

    fun setToken(token: String) {
        val currentBd = db ?: throw IllegalStateException(ERROR_DB)
        currentBd.edit().putString(TOKEN_KEY, token).apply()
    }

    // инициализация бд
    fun bindDB(context: Context): SharedPreferences? {
        db = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        return db
    }

    //зануление бд
    fun releaseDB() {
        db = null
    }


}