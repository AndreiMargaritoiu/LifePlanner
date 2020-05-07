package com.amdev.lifehelper.bdays.bdaysadd

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.AndroidViewModel
import com.amdev.lifehelper.databases.bdaysdatabase.Bday
import com.amdev.lifehelper.databases.bdaysdatabase.BdaysRepository
import kotlinx.coroutines.*

class AddBdaysViewModel (
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var repository: BdaysRepository = BdaysRepository(application)

    suspend fun insert(bday: Bday) {
        withContext(Dispatchers.IO) {
            repository.insert(bday)
        }
    }

    fun onSave(person: String, day: Int, month: Int, details: String) {
        uiScope.launch {
            val bday = Bday(person, day, month, details)
            insert(bday)
        }
    }

    fun hideKeyboardFrom(context: Context, view: View) {
        val imm =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}