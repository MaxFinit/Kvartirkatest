package com.maxfin.kvartirkatest.api

import com.maxfin.kvartirkatest.model.Flat
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject

class FlatsApi {

    fun getFlats(idTown: String?, offset: String): MutableList<Flat> {
        return try {
            val okHttpClient = OkHttpClient()
            val url =
                "https://api.beta.kvartirka.pro/client/1.4/flats?city_id=$idTown&offset=$offset"
            val request: Request = Request.Builder().url(url).build()
            val response: Response = okHttpClient.newCall(request).execute()
            val json = response.body()?.string()
            val temp = (JSONObject(json).getJSONArray("flats"))
            var list: MutableList<Flat> = mutableListOf()
            val currency = JSONObject(json).getJSONObject("currency").get("label").toString()
            for (i in 0 until temp.length()) {
                val jsO = temp.getJSONObject(i)
                list.add(
                    Flat(
                        jsO.get("id").toString(),
                        jsO.get("address").toString(),
                        jsO.getJSONObject("photo_default").get("url").toString(),
                        jsO.getJSONObject("prices").get("day").toString(),
                        currency
                    )
                )
            }
            return list
        } catch (ex: Exception) {
            return mutableListOf()
        }
    }
}