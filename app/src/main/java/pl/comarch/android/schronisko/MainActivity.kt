package pl.comarch.android.schronisko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    var clicksCount = 0
    val stateKey = "StateKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clicksCount = savedInstanceState?.getParcelable<Zwierz>(stateKey)?.name?.toInt() ?: clicksCount
        Log.d("LIFECYCLE", "onCreate-$this")
        setContentView(R.layout.activity_main)
        val logo = findViewById<ImageView>(R.id.logo)

        logo.setOnClickListener {
            clicksCount++
            Log.d("CLICK", "Click!$clicksCount")
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(stateKey, Zwierz(name = "$clicksCount"))
        super.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "onDestroy")
    }
}