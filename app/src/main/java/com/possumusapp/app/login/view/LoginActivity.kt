package com.possumusapp.app.login.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.possumusapp.R
import com.possumusapp.app.albums.model.AlbumCache
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.app.albums.view.AlbumActivity
import com.possumusapp.app.login.model.LoginCache
import com.possumusapp.app.login.model.UserModel
import com.possumusapp.app.login.viewmodel.LoginViewModel
import com.possumusapp.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    @Inject
    lateinit var loginCache: LoginCache



    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        dropDownMenu()
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
                if (isNetworkAvailable(this)) {
                    intent = Intent(this@LoginActivity, AlbumActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show()
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

    //Funcion que chequea el cache de usuarios antes de iniciar un activity
    private fun cacheCheck(url: String) {
        viewModel.onCreate(url)
        when {
            loginCache.users[url] == null && isNetworkAvailable(this) -> {
                viewModel.userModel.observe(this, androidx.lifecycle.Observer {
                    loginCache.users[url] = it
                    changeActivity(it)
                })
            }
            loginCache.users[url] != null-> changeActivity(loginCache.users[url]!!)
            else -> Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show()
        }

        if (loginCache.users[url] != null) {
            changeActivity(loginCache.users[url]!!)
        } else {
            viewModel.userModel.observe(this, androidx.lifecycle.Observer {
                loginCache.users[url] = it
                changeActivity(it)

            })
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

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }

}


