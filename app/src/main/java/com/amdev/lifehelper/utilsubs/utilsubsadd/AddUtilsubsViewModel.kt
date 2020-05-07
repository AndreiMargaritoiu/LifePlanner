package com.amdev.lifehelper.utilsubs.utilsubsadd

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.AndroidViewModel
import com.amdev.lifehelper.databases.utilsubsdatabase.Utilsub
import com.amdev.lifehelper.databases.utilsubsdatabase.UtilsubsRepository
import kotlinx.coroutines.*

class AddUtilsubsViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var repository: UtilsubsRepository = UtilsubsRepository(application)

    suspend fun insert(utilsub: Utilsub) {
        withContext(Dispatchers.IO) {
            repository.insert(utilsub)
        }
    }

    fun onSave(provider: String, deadline: Int, amount: Int) {
        uiScope.launch {
            val utilsub = Utilsub(provider, deadline, amount)
            insert(utilsub)
        }
    }

    fun hideKeyboardFrom(context: Context, view: View) {
        val imm =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
