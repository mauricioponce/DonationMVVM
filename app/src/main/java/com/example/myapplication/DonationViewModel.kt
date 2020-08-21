package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.DonationModel

sealed class ViewStatus
data class DonationOk(var amount: Int): ViewStatus()
object EmptyDonation: ViewStatus()
object InvalidAmount: ViewStatus()

class DonationViewModel: ViewModel() {
    private val max = 30
    private var model: DonationModel

    var status = MutableLiveData<ViewStatus>()

    init {
        model = DonationModel()
    }

    fun saveDonation(amount: Int) {
        when(amount) {
            0 -> status.postValue(EmptyDonation)
            !in 1..max -> status.postValue(InvalidAmount)
            else -> {
                model.saveDonation(amount)
                status.postValue(DonationOk(model.totalAmount))
            }
        }
    }
}