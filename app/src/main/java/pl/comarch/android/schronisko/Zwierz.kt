package pl.comarch.android.schronisko

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Zwierz(val name: String, @DrawableRes val imageId: Int, val opis: String = "") : Parcelable

@Entity
data class ZwierzDTO(@PrimaryKey(autoGenerate = true) val id: Int = 0, val name: String, val imageUrl: String)