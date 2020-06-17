package com.maxfin.kvartirkatest.api

import com.maxfin.kvartirkatest.model.FlatDetail
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject

class FlatsDetailApi {

    fun getFlats(idFlat: String?): FlatDetail {
        val okHttpClient = OkHttpClient()
        val url = "https://api.beta.kvartirka.pro/client/1.4/flats?flat_ids=[$idFlat]"
        val request: Request = Request.Builder().url(url).build()
        val response: Response = okHttpClient.newCall(request).execute()
        val json = response.body()?.string()
        return if (json != null) {
            val temp = (JSONObject(json).getJSONArray("flats"))
            val currency = JSONObject(json).getJSONObject("currency").get("label").toString()
            val jsO = temp.getJSONObject(0)
            val photos = mutableListOf<String>()
            val jsonPhotos = jsO.getJSONArray("photos")
            for (i in 0 until jsonPhotos.length()) {
                photos.add(jsonPhotos.getJSONObject(i).get("url").toString())
            }
            FlatDetail(
                jsO.get("id").toString(),
                jsO.get("title").toString(),
                jsO.getJSONObject("prices").get("day").toString(),
                currency,
                jsO.get("address").toString(),
                jsO.get("rooms").toString(),
                jsO.get("building_type").toString(),
                jsO.get("description").toString(),
                jsO.get("description_full").toString(),
                photos
            )
        } else
            FlatDetail(
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                null
            )
    }

}