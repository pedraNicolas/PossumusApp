package com.possumusapp.app.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.possumusapp.R
import com.possumusapp.app.albums.model.AlbumCache
import com.possumusapp.app.albums.view.AlbumActivity
import com.possumusapp.app.error.ErrorActivity
import com.possumusapp.app.login.model.LoginCache
import com.possumusapp.app.login.model.UserModel
import com.possumusapp.app.login.viewmodel.LoginViewModel
import com.possumusapp.commons.NetworkStatusInterface
import com.possumusapp.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.system.exitProcess


@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    @Inject
    lateinit var loginCache: LoginCache

    @Inject
    lateinit var networkStatusInterface: NetworkStatusInterface

    @Inject
    lateinit var albumCache: AlbumCache

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        dropDownMenu()
        super.onResume()
    }

    //Creacion del DropDown Menu
    private fun dropDownMenu() {
        val endpoints = resources.getStringArray(R.array.users)
        val adapter = ArrayAdapter(
            this,
            R.layout.drop_down_item,
            endpoints
        )
        with(binding.loginAutoCompleteTextView) {
            setAdapter(adapter)
            binding.loginAutoCompleteTextView.inputType = InputType.TYPE_NULL
            onItemClickListener = this@LoginActivity
        }
    }

    //Funcion que escucha al DropDown y determina que usuario ha sido seleccionado.
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        when (parent?.getItemAtPosition(position).toString()) {
            "All Users" -> {
                val cache = albumCache.albums["/albums"] == null
                val connection = networkStatusInterface.isNetworkAvailable(this)
                if (!cache || connection) {
                    intent = Intent(this@LoginActivity, AlbumActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show()
                    intent = Intent(this@LoginActivity, ErrorActivity::class.java)
                    startActivity(intent)
                }
            }
            "Leanne Graham" -> cacheCheck("/users/1")
            "Ervin Howell" -> cacheCheck("/users/2")
            "Clementine Bauch" -> cacheCheck("/users/3")
            "Patricia Lebsack" -> cacheCheck("/users/4")
            "Chelsey Dietrich" -> cacheCheck("/users/5")
            "Mrs. Dennis Schulist" -> cacheCheck("/users/6")
            "Kurtis Weissnat" -> cacheCheck("/users/7")
            "Nicholas Runolfsdottir V" -> cacheCheck("/users/8")
            "Glenna Reichert" -> cacheCheck("/users/9")
            "Clementina DuBuque" -> cacheCheck("/users/10")
        }

    }
    //Funcion que chequea el cache de usuarios y la conexion a internet antes de iniciar un activity
    private fun cacheCheck(url: String) {
        viewModel.onCreate(url)
        val connection = networkStatusInterface.isNetworkAvailable(this)
        val cache = loginCache.users[url] != null
        when {
            !cache && connection -> {
                viewModel.userModel.observe(this, Observer {
                    val code = it.code()
                    val userModel = it.body()
                    when {
                        code != 200 -> {
                            intent = Intent(this@LoginActivity, ErrorActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "Error: $code", Toast.LENGTH_SHORT).show()
                        }
                        code == 200 && userModel == null -> {
                            intent = Intent(this@LoginActivity, ErrorActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "User is Empty", Toast.LENGTH_SHORT).show()
                        }
                        code == 200 && userModel != null -> {
                            loginCache.users[url] = userModel
                            changeActivity(userModel)
                        }
                    }
                })
            }
            cache -> changeActivity(loginCache.users[url]!!)
            else -> {
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show()
                intent = Intent(this@LoginActivity, ErrorActivity::class.java)
                startActivity(intent)
            }
        }
    }

    //Funcion que inicia el Activity de albums dependiendo el usuario seleccionado.
    private fun changeActivity(user: UserModel) {
        intent = Intent(this@LoginActivity, AlbumActivity::class.java).apply {
            putExtra("user", user)
        }
        startActivity(intent)
    }

    private var backPressedTime: Long = 0
    lateinit var backToast: Toast
    override fun onBackPressed() {
        backToast = Toast.makeText(this, "Press back again to close the app.", Toast.LENGTH_LONG)
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel()
            super.onBackPressed()
            finishAffinity()
            exitProcess(0)
        } else {
            backToast.show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}


