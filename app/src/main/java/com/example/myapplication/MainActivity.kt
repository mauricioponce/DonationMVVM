package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.viewmodel.DonationOk
import com.example.myapplication.viewmodel.DonationViewModel
import com.example.myapplication.viewmodel.EmptyDonation
import com.example.myapplication.viewmodel.InvalidAmount
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: DonationViewModel
    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(DonationViewModel::class.java)

        btDonation.setOnClickListener {
            val text = etNewDonation.text.toString()
            try {
                viewModel.saveDonation(text.toInt())
            }catch (exc: Exception) {

            }
        }

        viewModel.status.observe(this, {
            when (it) {
                is DonationOk -> {
                    tvTotalAmount.text = it.amount.toString()
                    etNewDonation.text.clear()
                    Snackbar.make(btDonation, R.string.confirmation_message, Snackbar.LENGTH_LONG).show()
                }

                is EmptyDonation -> Log.d(tag, "empty donation")

                is InvalidAmount -> Snackbar.make(etNewDonation, R.string.error_message, Snackbar.LENGTH_LONG).show()
            }
        })
    }
}