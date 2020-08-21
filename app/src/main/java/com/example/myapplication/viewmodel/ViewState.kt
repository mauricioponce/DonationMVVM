package com.example.myapplication.viewmodel

sealed class ViewStatus
data class DonationOk(var amount: Int): ViewStatus()
object EmptyDonation: ViewStatus()
object InvalidAmount: ViewStatus()