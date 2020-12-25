package com.damir.android.cv

import android.app.Activity
import android.os.Build
import android.text.Editable
import android.text.Html
import android.text.Spanned
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import org.xml.sax.XMLReader
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object {
        private val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        fun format(mills: Long): String {
            return dateFormat.format(Date(mills))
        }
        fun format(dateString: String): String {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateString)
            return dateFormat.format(date ?: "")
        }
    }
}

fun String.toHtml(): Spanned {
    return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(this)
    }
}

fun AppCompatActivity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
}