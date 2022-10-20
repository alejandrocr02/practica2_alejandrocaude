package com.example.myapplication

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityAquaBinding
import com.example.myapplication.databinding.ActivityNcBinding

class aqua_activity : AppCompatActivity() {
    private lateinit var binding: ActivityAquaBinding
    private var mediaPlayer: MediaPlayer? = null
    private var position: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nc)

        binding = ActivityAquaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var aqua_Pull =Tienda("Pull and Bear","Venden ropa")
        var aqua_100montaditos = Tienda("100 montaditos","restauración")
        var aqua_Game = Tienda("Game","Venden consolas,videojuegos...")
        var aqua_alehop = Tienda("Alehop","Venden accesorios, y suvenirs")



        binding.t1.text = aqua_Pull.nombre
        binding.t12.text = aqua_Pull.descripcion

        binding.t2.text = aqua_Game.nombre
        binding.t22.text = aqua_Game.descripcion

        binding.t3.text = aqua_100montaditos.nombre
        binding.t32.text = aqua_100montaditos.descripcion

        binding.t4.text = aqua_alehop.nombre
        binding.t42.text = aqua_alehop.descripcion
    }

    override fun onStart() {
        super.onStart()

        mediaPlayer = MediaPlayer.create(this, R.raw.neverita2)
    }

    override fun onResume() {
        super.onResume()

        val extras = intent.extras
        if (extras != null) {
            position = extras.getInt("musicPosition")
        }

        mediaPlayer?.seekTo(position)
        mediaPlayer?.start()
    }

    override fun onPause() {
        super.onPause()

        mediaPlayer?.pause()
        if (mediaPlayer != null) {
            position = mediaPlayer!!.currentPosition
        }
    }

    override fun onStop() {
        super.onStop()

        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val returnIntent = Intent()
        returnIntent.putExtra("musicPosition", position)
        setResult(RESULT_OK, returnIntent)
        finish()
    }

}