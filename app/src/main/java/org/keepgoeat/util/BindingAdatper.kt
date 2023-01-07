package org.keepgoeat.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load
import org.keepgoeat.R
import org.keepgoeat.util.extension.padZero
import java.time.LocalDate

@BindingAdapter("image")
fun ImageView.setImage(imageUrl: String) {
    this.load(imageUrl)
}

@BindingAdapter("visibility")
fun View.setVisibility(isVisible: Boolean?) {
    if (isVisible == null) return
    this.isVisible = isVisible
}

@BindingAdapter(value = ["startDate", "endDate"], requireAll = false)
fun TextView.setDuration(startDate: LocalDate?, endDate: LocalDate?) {
    safeLet(startDate, endDate) { s, e ->
        text = String.format(
            context.getString(R.string.my_goal_duration_format),
            s.year,
            s.monthValue.padZero(2),
            s.dayOfMonth.padZero(2),
            e.year,
            e.monthValue.padZero(2),
            e.dayOfMonth.padZero(2)
        )
    }
}
