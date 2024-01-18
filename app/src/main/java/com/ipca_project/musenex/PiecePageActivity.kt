    package com.ipca_project.musenex

    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.view.View
    import android.widget.Button
    import android.widget.ImageView
    import android.widget.TextView
    import com.squareup.picasso.Picasso
    import model.Piece
    import com.google.android.exoplayer2.ExoPlayer
    import com.google.android.exoplayer2.MediaItem
    import com.google.android.exoplayer2.ui.StyledPlayerView

    class PiecePageActivity : AppCompatActivity() {
        private lateinit var imageView: ImageView
        private lateinit var expandedTextView: TextView
        private lateinit var piece: Piece
        private lateinit var btnPt: Button
        private lateinit var btnEng: Button
        private lateinit var btnShowPlayer: Button
        private val exoPlayer by lazy { ExoPlayer.Builder(this).build() }
        private lateinit var styledPlayer: StyledPlayerView
        private var isPlayerVisible = false

        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_piece_page)

            piece = intent.getSerializableExtra("piece") as Piece

            //appBar
            setSupportActionBar(findViewById(R.id.my_toolbar))
            getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
            getSupportActionBar()?.setTitle(piece.name)

            //ImageView
            imageView = findViewById(R.id.imagePiece)
            Picasso.get().load(piece.foto_url).into(imageView)

            //Expanded TextView
            btnPt = findViewById(R.id.btnPT)
            btnEng = findViewById(R.id.btnENG)
            expandedTextView = findViewById(R.id.expand_tv)

            btnPt.setOnClickListener {
                expandedTextView.text = piece.description
            }

            btnEng.setOnClickListener {
                expandedTextView.text = piece.engDescription
            }

            btnPt.performClick()

            btnShowPlayer=findViewById(R.id.btnShowPlayer)

            btnShowPlayer.setOnClickListener()
            {
                if (!isPlayerVisible) {
                    setupMediaAudio()
                    isPlayerVisible = true
                }
            }
        }

        //Exoplayer
        fun setupMediaAudio() {
            val firestoreAudioUrl = piece.audio_url
            val mediaItem = MediaItem.fromUri(firestoreAudioUrl)
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.play()

            styledPlayer = findViewById(R.id.styledPlayer)
            styledPlayer.player = exoPlayer
            styledPlayer.controllerShowTimeoutMs = 0
            styledPlayer.controllerHideOnTouch = false
        }
    }