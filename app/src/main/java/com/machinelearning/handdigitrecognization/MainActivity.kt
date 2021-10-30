package com.machinelearning.handdigitrecognization

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

import com.nex3z.fingerpaintview.FingerPaintView
import android.widget.Toast
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var fpvPaint: FingerPaintView
    private lateinit var tvPrediction: TextView
    private lateinit var tvProbability: TextView
    private lateinit var timeCost: TextView
    private var classifier: Classifier? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            classifier = Classifier(this)
        } catch (e: IOException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }

        fpvPaint = findViewById(R.id.fpv_paint)
        tvPrediction = findViewById(R.id.prediction)
        tvProbability = findViewById(R.id.probability)
        timeCost = findViewById(R.id.timecost)

        val detect: Button = findViewById(R.id.btn_detect)
        val clear: Button = findViewById(R.id.btn_clear)

        detect.setOnClickListener {
            val bitmap = fpvPaint.exportToBitmap(Classifier.IMG_WIDTH, Classifier.IMG_HEIGHT)
           classifier?.classify(bitmap)?.let {
                tvProbability.text = "Probability: ${it.probability}"
                tvPrediction.text = "Prediction: ${it.number}"
                timeCost.text = "TimeCost: ${it.timeCost}"
            }

        }
        clear.setOnClickListener {
            fpvPaint.clear()
            tvPrediction.text = ""
            tvProbability.text = ""
            timeCost.text = ""
        }


    }
}