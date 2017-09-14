package ninja.sakib.gdaxrestapi

import java.security.InvalidKeyException
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import javax.management.RuntimeErrorException

fun getSignature(requestPath: String, method: String, body: String): String {
    try {
        val prehash = getTimestamp() + method.toUpperCase() + requestPath + body
        val secretDecoded = Base64.getDecoder().decode(getSecreteKey())
        val keyspec = SecretKeySpec(secretDecoded, "HmacSHA256")
        val sha256 = getSharedMac().clone() as Mac
        sha256.init(keyspec)
        return Base64.getEncoder().encodeToString(sha256.doFinal(prehash.toByteArray()))
    } catch (var9: InvalidKeyException) {
        var9.printStackTrace()
        throw RuntimeErrorException(Error("Cannot set up authentication headers."))
    } catch (var9: CloneNotSupportedException) {
        var9.printStackTrace()
        throw RuntimeErrorException(Error("Cannot set up authentication headers."))
    }
}
