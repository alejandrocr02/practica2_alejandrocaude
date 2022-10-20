package com.example.myapplication

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityArenaBinding
import com.example.myapplication.databinding.ActivityBonaireBinding

class arena_activity : AppCompatActivity() {
    private lateinit var binding: ActivityArenaBinding
    private var mediaPlayer: MediaPlayer? = null
    private var position: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nc)

        binding = ActivityArenaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var Arena_Pull =Tienda("Pull and Bear","Venden ropa")
        var Arena_100montaditos = Tienda("100 montaditos","restauración")
        var Arena_Game = Tienda("Game","Venden consolas,videojuegos...")
        var Arena_alehop = Tienda("Alehop","Venden accesorios, y suvenirs")

        binding.t1.text = Arena_Pull.nombre
        binding.t12.text = Arena_Pull.descripcion

        binding.t2.text = Arena_100montaditos.nombre
        binding.t22.text = Arena_100montaditos.descripcion

        binding.t3.text = Arena_Game.nombre
        binding.t32.text = Arena_Game.descripcion

        binding.t4.text = Arena_alehop.nombre
        binding.t42.text = Arena_alehop.descripcion
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