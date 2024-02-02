package com.morning.auth.common.utils

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.security.DigestException
import java.security.MessageDigest
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@ConfigurationProperties(prefix = "crypto")
@Component
object CryptoUtils {
    @Value("\${crypto.password}")
    var password: String = ""

    @Value("\${crypto.byte-array}")
    var byteArray: Int = 0

    @Value("\${crypto.secret-key-algorithm}")
    var secretKeyAlgorithm: String = ""

    @Value("\${crypto.transformation}")
    var transformation: String = ""

    @Value("\${crypto.digest-algorithm}")
    var digestAlgorithm: String = ""

    // Function to perform encryption
    fun encryptText(value: String): String {
        val cipherEnc = Cipher.getInstance(transformation)
        val keySpec = SecretKeySpec(hashString(password), secretKeyAlgorithm)
        val iv = ByteArray(byteArray)
        cipherEnc.init(Cipher.ENCRYPT_MODE, keySpec, IvParameterSpec(iv))
        val byteEncryptedValue = cipherEnc.doFinal(value.toByteArray())
        val base64EncryptedValue = Base64.getEncoder().encodeToString(byteEncryptedValue)
        return base64EncryptedValue
    }

    fun decryptText(encryptedText: String): String {
        val cipherDec = Cipher.getInstance(transformation)
        val keySpec = SecretKeySpec(hashString(password), secretKeyAlgorithm)
        val iv = ByteArray(byteArray)
        cipherDec.init(Cipher.DECRYPT_MODE, keySpec, IvParameterSpec(iv))
        val byteDecryptedText = cipherDec.doFinal(Base64.getDecoder().decode(encryptedText))
        return String(byteDecryptedText)
    }

    fun digestText(input: String): String {
        val bytes = input.toByteArray()
        val md = MessageDigest.getInstance(digestAlgorithm)
        val digest = md.digest(bytes)

        // byte 배열을 16진수 문자열로 변환
        val result = StringBuilder()
        for (b in digest) {
            result.append(String.format("%02x", b))
        }
        return result.toString()
    }

    fun hashString(msg: String): ByteArray {
        val hash: ByteArray
        try {
            val md = MessageDigest.getInstance(digestAlgorithm)
            md.update(msg.toByteArray())
            hash = md.digest()
        } catch (e: CloneNotSupportedException) {
            throw DigestException("couldn't make digest of partial content")
        }
        return hash
    }
}
