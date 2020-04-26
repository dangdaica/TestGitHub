package com.example.myapplication

import android.content.Context
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import java.util.concurrent.TimeUnit

class YourImageAnalyzer (label :LinearLayout , context : Context)  : ImageAnalysis.Analyzer {
    private var mLabel : LinearLayout
    private val mContext : Context
    init {
        mLabel = label
        mContext = context
    }
    private var lastAnalyzedTimestamp = 0L
     private fun degreesToFirebaseRotation(degrees: Int): Int = when(degrees) {
         0 -> FirebaseVisionImageMetadata.ROTATION_0
         90 -> FirebaseVisionImageMetadata.ROTATION_90
         180 -> FirebaseVisionImageMetadata.ROTATION_180
         270 -> FirebaseVisionImageMetadata.ROTATION_270
         else -> throw Exception("Rotation must be 0, 90, 180, or 270.")
     }
    override fun analyze(imageProxy: ImageProxy?, rotationDegrees: Int) {
        val currentTimestamp = System.currentTimeMillis()
        if (currentTimestamp - lastAnalyzedTimestamp <
            TimeUnit.SECONDS.toMillis(7)){
            return
        }
        lastAnalyzedTimestamp = currentTimestamp
        Log.d("vandang.ng", "Start analyzing !!!!!!!!!!!!!!!!!!!!! ")

        val mediaImage = imageProxy?.image
        val imageRotation = degreesToFirebaseRotation(rotationDegrees)
        if (mediaImage != null) {
            val image = FirebaseVisionImage.fromMediaImage(mediaImage, imageRotation)
            val labeler = FirebaseVision.getInstance().getOnDeviceImageLabeler()
            labeler.processImage(image).
                addOnSuccessListener { labels ->
                    mLabel.removeAllViewsInLayout()
                    for (label in labels) {
                        Log.d("vandang.ng", " this is  " + label.text )
                        val textView = TextView(mContext)
                        textView.text = label.text
                        textView.textSize = mContext.resources.getDimension(R.dimen.text_size)
                        textView.setTextColor(mContext.resources.getColor(R.color.colorPrimaryDark))
                        val params = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.MATCH_PARENT
                        )
                        textView.layoutParams = params
                        mLabel.addView(textView)


                    }

                    Log.d("vandang.ng", "Eng analyzing !!!!!!!!!!!!!!!!!!!!! ")
                }
                .addOnFailureListener { e->
                        Log.d("vandang.ng", "text" + e)
                }
        }

    }
}