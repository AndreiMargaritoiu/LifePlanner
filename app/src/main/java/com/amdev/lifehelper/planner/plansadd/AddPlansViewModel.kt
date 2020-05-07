package com.amdev.lifehelper.planner.plansadd

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.amdev.lifehelper.databases.plansdatabase.Plan
import com.amdev.lifehelper.databases.plansdatabase.PlansRepository
import kotlinx.coroutines.*

class AddPlansViewModel (
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var repository: PlansRepository = PlansRepository(application)

    suspend fun insert(plan: Plan) {
        withContext(Dispatchers.IO) {
            repository.insert(plan)
        }
    }

    fun onSave(planName: String, day: Int, month: Int, details: String) {
        uiScope.launch {
            val plan = Plan(planName, day, month, details)
            insert(plan)
        }
    }

    fun hideKeyboardFrom(context: Context, view: View) {
        val imm =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
