package com.example.myapplication

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityBonaireBinding
import com.example.myapplication.databinding.ActivityNcBinding

class bonaire_activity : AppCompatActivity() {
    private lateinit var binding: ActivityBonaireBinding
    private var mediaPlayer: MediaPlayer? = null
    private var position: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nc)

        binding = ActivityBonaireBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bonaire_Hollister = Tienda("Hollister","Venden ropa")
        var bonaire_Alcampo = Tienda("Alcampo","Venden productos de alimentación")
        var bonaire_Fnac = Tienda("Fnac","Venden libros,musica,juegos...")
        var bonaire_LibrosIdeas = Tienda("Libros e ideas","Venden libros")



        binding.t1.text = bonaire_Hollister.nombre
        binding.t12.text = bonaire_Hollister.descripcion

        binding.t2.text = bonaire_Alcampo.nombre
        binding.t22.text = bonaire_Alcampo.descripcion

        binding.t3.text = bonaire_Fnac.nombre
        binding.t32.text = bonaire_Fnac.descripcion

        binding.t4.text = bonaire_LibrosIdeas.nombre
        binding.t42.text = bonaire_LibrosIdeas.descripcion
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