package pl.comarch.android.schronisko

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Zwierz(val name:String, @DrawableRes val imageId: Int, val opis: String = "") : Parcelable

data class ZwierzDTO(val name:String, val imageUrl: String)