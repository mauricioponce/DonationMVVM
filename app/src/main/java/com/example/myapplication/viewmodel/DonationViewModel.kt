package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.DonationModel


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