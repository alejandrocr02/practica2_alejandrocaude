package com.example.myapplication


import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityScrollingBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapplication.databinding.ActivityNcBinding


class nc_activity : AppCompatActivity() {
    private lateinit var binding: ActivityNcBinding
    private var mediaPlayer: MediaPlayer? = null
    private var position: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nc)

        binding = ActivityNcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var nc_pull = Tienda("Pull and Bear","Venden ropa")
        var nc_game = Tienda("Game","Venden consolas,videojuegos...")
        var nc_alehop = Tienda("Alehop","Venden accesorios, y suvenirs")
        var nc_multiopticas = Tienda("Multiopticas","Venden gafas")



        binding.t1.text = nc_pull.nombre
        binding.t12.text = nc_pull.descripcion

        binding.t2.text = nc_game.nombre
        binding.t22.text = nc_game.descripcion

        binding.t3.text = nc_alehop.nombre
        binding.t32.text = nc_alehop.descripcion

        binding.t4.text = nc_multiopticas.nombre
        binding.t42.text = nc_multiopticas.descripcion




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