package pl.comarch.android.schronisko

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

object NetworkService {

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://my-json-server.typicode.com/kgluszczyk/fake-server-shelter/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val zwierzakiService = retrofit.create(ZwierzakiService::class.java)

    interface ZwierzakiService {

        @GET("pets")
        fun getZwierzaki(): Call<List<ZwierzDTO>>
    }
}