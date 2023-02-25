package com.star.wars.utility

import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned

@Suppress("DEPRECATION")
class HtmlUtility {
    fun fromHtml(html: String?): Spanned? = if (html == null) SpannableString("")
    else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) Html.fromHtml(
        html, Html.FROM_HTML_MODE_LEGACY
    )
    else Html.fromHtml(html)
}