package com.example.composition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entity.GameResult

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswer(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, percentage: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        percentage
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(gameResult)
    )
}

private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countOfQuestion == 0) {
        0
    } else {
        ((countOfRightAnswer / countOfQuestion.toDouble()) * 100).toInt()
    }
}

@BindingAdapter("resultEmoji")
fun bindResultEmoji(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getEmojiId(winner))
}

private fun getEmojiId(winner: Boolean): Int {
    return if (winner) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }
}

@BindingAdapter("progressBar")
fun bindProgress(progressBar: ProgressBar, progress: Int) {
    progressBar.setProgress(progress, true)
}

@BindingAdapter("progressBarSecondaryProgress")
fun bindSecondaryProgress(progressBar: ProgressBar, progress: Int) {
    progressBar.secondaryProgress = progress
}

@BindingAdapter("progressBarColor")
fun bindProgressColor(progressBar: ProgressBar, progress: Boolean) {
    val color = getColor(progressBar.context, progress)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("answersProgressColor")
fun bindAnswersColor(textView: TextView, progress: Boolean) {
    textView.setTextColor(getColor(textView.context, progress))
}

private fun getColor(context: Context, boolean: Boolean): Int {
    val colorResId = if (boolean) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}