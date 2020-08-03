package com.kakaopay.assignment.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kakaopay.assignment.R
import com.kakaopay.assignment.databinding.ActivityMainBinding
import com.kakaopay.assignment.presentation.util.setContentViewBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentViewBinding<ActivityMainBinding>(R.layout.activity_main).apply {
            viewModel = this@MainActivity.viewModel
        }
    }
}