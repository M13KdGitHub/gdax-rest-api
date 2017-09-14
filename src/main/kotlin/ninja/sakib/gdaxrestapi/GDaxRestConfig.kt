package ninja.sakib.gdaxrestapi

import okhttp3.OkHttpClient
import okhttp3.Request
import java.time.Instant
import javax.crypto.Mac

private var baseUrl = ""
private var passPhrase = ""
private var apiKey = ""
private var secreteKey = ""

fun getSharedMac(): Mac {
    return Mac.getInstance("HmacSHA256")
}

fun setBaseUrl(value: String) {
    baseUrl = value
}

fun getBaseUrl(): String {
    return baseUrl
}

fun setPassPhrase(value: String) {
    passPhrase = value
}

fun getPassPhrase(): String {
    return passPhrase
}

fun setApiKey(value: String) {
    apiKey = value
}

fun getApiKey(): String {
    return apiKey
}

fun setSecreteKey(value: String) {
    secreteKey = value
}

fun getSecreteKey(): String {
    return secreteKey
}

fun getTimestamp(): String {
    return Instant.now().epochSecond.toString()
}

fun getCustomRequestBuilder(signature: String): Request.Builder {
    return Request.Builder()
            .addHeader("CB-ACCESS-KEY", getApiKey())
            .addHeader("CB-ACCESS-SIGN", signature)
            .addHeader("CB-ACCESS-TIMESTAMP", getTimestamp())
            .addHeader("CB-ACCESS-PASSPHRASE", getPassPhrase())
}

fun getOkHttpClient(): OkHttpClient {
    return OkHttpClient()
}
