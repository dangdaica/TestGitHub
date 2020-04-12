package com.example.myapplication

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.firebase.ml.vision.common.FirebaseVisionImage

private class YourImageAnalyzer : ImageAnalysis.Analyzer {
    override fun analyze(imageProxy: ImageProxy?, rotationDegrees: Int) {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val mediaImage = imageProxy?.image
       // val imageRotation = degreesToFirebaseRotation(rotationDegrees)
        if (mediaImage != null) {
            val image = FirebaseVisionImage.fromMediaImage(mediaImage, rotationDegrees)
            // Pass image to an ML Kit Vision API
            // ...
        }

    }
}