package pl.comarch.android.schronisko

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ZwierzakiAdapter(var zwierzaki: List<Zwierz>) : RecyclerView.Adapter<ZwierzViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZwierzViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.zwierz_item, parent, false)
        return ZwierzViewHolder(view)
    }

    override fun onBindViewHolder(holder: ZwierzViewHolder, position: Int) {
        val zwierz = zwierzaki[position]
        holder.bind(zwierz, this)
    }

    override fun getItemCount() = zwierzaki.size
}

class ZwierzViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(zwierz: Zwierz, adapter: ZwierzakiAdapter) {
        with(view) {
            findViewById<TextView>(R.id.zwierz_name).text = zwierz.name
            val opisEditText = findViewById<EditText>(R.id.zwierz_opis)
            opisEditText.setText(zwierz.opis)
            opisEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.toString() != "") {
                        adapter.zwierzaki = adapter.zwierzaki.map {
                            Zwierz(it.name, it.imageId, if (zwierz == it) s.toString() else it.opis)
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
            findViewById<View>(R.id.zwierz_container).setBackgroundResource(zwierz.imageId)
        }
    }
}