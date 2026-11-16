package com.arina.ai

import android.app.Activity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.*
import android.graphics.Color
import java.util.Locale

class MainActivity : Activity() {
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tts = TextToSpeech(this) {
            tts.language = Locale("bn", "BD")
        }

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(25, 50, 25, 25)
        layout.setBackgroundColor(Color.rgb(15, 20, 45))

        val title = TextView(this)
        title.text = "💙 ARINA AI"
        title.textSize = 30f
        title.setTextColor(Color.CYAN)

        val input = EditText(this)
        input.hint = "এখানে লিখুন..."
        input.setTextColor(Color.WHITE)
        input.setHintTextColor(Color.LTGRAY)

        val button = Button(this)
        button.text = "কথা বলো"

        val output = TextView(this)
        output.textSize = 18f
        output.setTextColor(Color.WHITE)

        button.setOnClickListener {
            val message = input.text.toString()
            output.text = "তুমি বলেছ: $message"
            tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, "arina")
        }

        layout.addView(title)
        layout.addView(input)
        layout.addView(button)
        layout.addView(output)

        setContentView(layout)
    }

    override fun onDestroy() {
        tts.shutdown()
        super.onDestroy()
    }
}
