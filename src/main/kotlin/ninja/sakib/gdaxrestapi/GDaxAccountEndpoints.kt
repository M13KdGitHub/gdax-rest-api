package ninja.sakib.gdaxrestapi

import com.eclipsesource.json.Json
import com.eclipsesource.json.JsonArray

fun getAccounts(): JsonArray? {
    val requestPath = "/accounts"
    val signature = getSignature(requestPath, "get", "")
    val request = getCustomRequestBuilder(signature).url("${getBaseUrl()}$requestPath").build()
    val response = getOkHttpClient().newCall(request).execute()
    if (response != null) {
        val body = response.body()
        if (body != null) {
            return Json.parse(body.string()).asArray()
        }
    }
    return null
}
