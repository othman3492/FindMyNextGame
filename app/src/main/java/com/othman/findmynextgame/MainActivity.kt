package com.othman.findmynextgame




import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import coil.api.load
import com.othman.findmynextgame.util.Resource
import com.othman.findmynextgame.viewmodel.GameViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val viewModel: GameViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        get_game_button.setOnClickListener { display() }

    }


    fun display() {

        viewModel.gameList.observe(this, Observer {

            when (it) {

                is Resource.Success -> {
                    if (it.data != null) {
                        val game = it.data[(0..it.data.size).random()]
                        game_image.load(game.background_image)
                        result_count.text = game.name
                    }
                }

                is Resource.Loading -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }

                is Resource.Error -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        })


    }

}