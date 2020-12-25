package com.damir.android.cv

import android.app.Application
import com.damir.android.cv.data.local.AppDatabase

class App : Application() {

    override fun onCreate() {
        AppDatabase.init(this)
        UserManager.init(this)
        super.onCreate()
    }

    /*
    Detail resume(процентаж)
    Фильтр по ЗП
    Откликнуться
    Заблы пароль(Регистрация через БД)
    Рейтинг компаний
     */
}