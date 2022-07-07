package com.possumusapp.app.error

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.possumusapp.databinding.ActivityErrorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ErrorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityErrorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}