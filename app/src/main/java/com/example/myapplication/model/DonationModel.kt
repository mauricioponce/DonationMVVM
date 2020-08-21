package com.example.myapplication.model

class DonationModel {
    var totalAmount = 0

    fun saveDonation(donation: Int) {
        totalAmount += donation
    }
}
