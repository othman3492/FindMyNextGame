package com.othman.findmynextgame




import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.net.toUri
import coil.api.load
import com.othman.findmynextgame.data.api.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        get_game_button.setOnClickListener { executeRequest() }

    }


    fun executeRequest() = CoroutineScope(Dispatchers.Main).launch {

        try {

            val response = ApiService.apiClient.getGamesList()
            if (response.isSuccessful && response.body() != null) {

                val data = response.body()
                game_image.load(data!!.results[0].background_image)
            } else {

                result_count.text = response.errorBody().toString()
            }


        } catch (e: Exception) {

            Log.d("TAG", e.message.toString())
        }
    }
}