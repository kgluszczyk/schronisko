package pl.comarch.android.schronisko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailedActivity : AppCompatActivity() {

    companion object{
        const val zwierzKey = "zwierzKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        val zwierz: Zwierz = intent.extras?.getParcelable(zwierzKey) ?: throw IllegalStateException("Did you forget to pass zwierz?")

        findViewById<TextView>(R.id.name).text = zwierz.name
        findViewById<ImageView>(R.id.image).setImageResource(zwierz.imageId)
    }
}