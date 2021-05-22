package com.biodun.roomassignment.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.biodun.roomassignment.db.entities.LoginEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginRepository(context: Context) {
    private val db = LoginDatabase.invoke(context)

    fun getAllLogin() : LiveData<List<LoginEntity>> = db.loginDao().selectAllLogin()

    fun getUserByEmail(email: String) : LiveData<LoginEntity?> {
        val loginEntity: MutableLiveData<LoginEntity?> = MutableLiveData()
        GlobalScope.launch {
            loginEntity.postValue(db.loginDao().findLoginEntityByEmail(email))
        }
        return  loginEntity
    }

    fun saveDetails (loginEntity: LoginEntity) {
        GlobalScope.launch {
            withContext(Dispatchers.IO){
                db.loginDao().insertLogin(loginEntity)
            }
        }
    }
}