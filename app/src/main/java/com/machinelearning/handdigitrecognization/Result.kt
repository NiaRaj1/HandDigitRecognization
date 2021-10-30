package com.machinelearning.handdigitrecognization

class Result( probs: FloatArray,val timeCost: Long) {
    val number = argmax(probs)
    val probability = probs[number]

    private fun argmax(probs: FloatArray): Int {
        var maxIdx = -1
        var maxProb = 0.0f
        for (i in probs.indices) {
            if (probs[i] > maxProb) {
                maxProb = probs[i]
                maxIdx = i
            }
        }
        return maxIdx
    }
}