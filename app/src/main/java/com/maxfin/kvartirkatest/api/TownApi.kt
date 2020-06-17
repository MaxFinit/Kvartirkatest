package com.maxfin.kvartirkatest.api

import com.maxfin.kvartirkatest.model.Location
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject

class TownApi {

    fun determineByCoordinate(lon: String, lat: String): Location? {
        return try {
            val okHttpClient = OkHttpClient()
            val url = "https://api.beta.kvartirka.pro/client/1.4/cities?lat=$lat&lng=$lon"
            val request: Request = Request.Builder().url(url).build()
            val response: Response = okHttpClient.newCall(request).execute()
            val json = response.body()?.string()
            val temp = (JSONObject(json).getJSONArray("cities").getJSONObject(0))
            Location(temp.get("name").toString(), temp.get("id").toString())

        } catch (ex: Exception) {
            Location("Москва", "18")
        }


    }


}