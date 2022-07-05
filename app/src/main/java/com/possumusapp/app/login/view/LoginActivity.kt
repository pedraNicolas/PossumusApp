package com.possumusapp.app.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.possumusapp.R
import com.possumusapp.app.albums.view.AlbumActivity
import com.possumusapp.app.login.model.UserModel
import com.possumusapp.app.login.viewmodel.LoginViewModel
import com.possumusapp.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        dropDownMenu()
    }
//Funciones del DropDown Menu
    private fun dropDownMenu() {
        val endpoints = resources.getStringArray(R.array.users)
        val adapter = ArrayAdapter(
            this,
            R.layout.drop_down_item,
            endpoints
        )
        with(binding.loginAutoCompleteTextView) {
            setAdapter(adapter)
            binding.loginAutoCompleteTextView.inputType= InputType.TYPE_NULL
            onItemClickListener = this@LoginActivity
        }
    }
//Funcion que escucha al DropDown y determina que usuario ha sido seleccionado.
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.getItemAtPosition(position).toString()) {
            "All Users" -> {
                intent = Intent(this@LoginActivity, AlbumActivity::class.java)
                finish()
                startActivity(intent)
            }
            "Leanne Graham" -> {
                viewModel.fetchLoginData("/users/1")
                viewModel.userModel.observe(this, androidx.lifecycle.Observer {
                    changeActivity(it)
                })
            }
            "Ervin Howell" -> {
                viewModel.fetchLoginData("/users/2")
                viewModel.userModel.observe(this, androidx.lifecycle.Observer {
                    changeActivity(it)
                })
            }
            "Clementine Bauch" -> {
                viewModel.fetchLoginData("/users/3")
                viewModel.userModel.observe(this, androidx.lifecycle.Observer {
                    changeActivity(it)
                })
            }
            "Patricia Lebsack" -> {
                viewModel.fetchLoginData("/users/4")
                viewModel.userModel.observe(this, androidx.lifecycle.Observer {
                    changeActivity(it)
                })
            }
            "Chelsey Dietrich" -> {
                viewModel.fetchLoginData("/users/5")
                viewModel.userModel.observe(this, androidx.lifecycle.Observer {
                    changeActivity(it)
                })
            }
            "Mrs. Dennis Schulist" -> {
                viewModel.fetchLoginData("/users/6")
                viewModel.userModel.observe(this, androidx.lifecycle.Observer {
                    changeActivity(it)
                })
            }
            "Kurtis Weissnat" -> {
                viewModel.fetchLoginData("/users/7")
                viewModel.userModel.observe(this, androidx.lifecycle.Observer {
                    changeActivity(it)
                })
            }
            "Nicholas Runolfsdottir V" -> {
                viewModel.fetchLoginData("/users/8")
                viewModel.userModel.observe(this, androidx.lifecycle.Observer {
                    changeActivity(it)
                })
            }
            "Glenna Reichert" -> {
                viewModel.fetchLoginData("/users/9")
                viewModel.userModel.observe(this, androidx.lifecycle.Observer {
                    changeActivity(it)
                })
            }
            "Clementina DuBuque" -> {
                viewModel.fetchLoginData("/users/10")
                viewModel.userModel.observe(this, androidx.lifecycle.Observer {
                    changeActivity(it)
                })
            }
        }

    }
//Funcion que inicia el Activity de albums dependiendo el usuario seleccionado.
    private fun changeActivity(user: UserModel) {
        intent = Intent(this@LoginActivity, AlbumActivity::class.java).apply {
            putExtra("user", user)
        }
        finish()
        startActivity(intent)
    }

}


