package pl.comarch.android.schronisko

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.parcelize.Parcelize
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var clicksCount = 0
    val stateKey = "StateKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clicksCount = savedInstanceState?.getParcelable<State>(stateKey)?.clicksCount ?: clicksCount
        Log.d("LIFECYCLE", "onCreate-$this")
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.listeZwierzat).apply {
            adapter = ZwierzakiAdapter(zwierzaki = DataSource.getZwierzaki())
        }

        val logo = findViewById<ImageView>(R.id.logo)
        logo.setOnClickListener {
            clicksCount++
            Log.d("CLICK", "Click!$clicksCount")
            Toast.makeText(this, DataSource.getZwierzaki().toString(), Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.cta).run {
            setOnClickListener {
                Intent(this@MainActivity, DetailedActivity::class.java).apply {
                    putExtra(
                        DetailedActivity.zwierzKey,
                        DataSource.getZwierzaki().shuffled().first()
                    )
                }.let {
                    startActivity(it)
                }
            }
        }
        fetchData()
    }

    private fun fetchData() {
        NetworkService.zwierzakiService.getZwierzaki().enqueue(object: Callback<List<ZwierzDTO>> {
            override fun onResponse(call: Call<List<ZwierzDTO>>, response: Response<List<ZwierzDTO>>) {
                val zwierzaki = response.body()
                Log.d("RESTFUL API", zwierzaki.toString())
                //Użyć Korutyny, używanie wątków może wydawać się dobrym rozwiązaniem, ale odpalając go, nie mamy nad nim większej kontroli(cancelowanie)
                Thread {
                    zwierzaki?.let {
                        App.database.zwierzakiDao().delete()
                        App.database.zwierzakiDao().insert(it)
                    }
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, DataSource.getZwierzaki().toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }.start()
            }

            override fun onFailure(call: Call<List<ZwierzDTO>>, t: Throwable) {
                Log.e("RESTFUL API", "Failed to fetch zwierzaki", t)
            }

        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(stateKey, State(clicksCount = clicksCount))
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

@Parcelize
data class State(val clicksCount: Int = 0) : Parcelable