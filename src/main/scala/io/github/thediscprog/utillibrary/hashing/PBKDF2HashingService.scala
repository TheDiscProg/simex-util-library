package io.github.thediscprog.utillibrary.hashing

import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class PBKDF2HashingService() extends HashingServiceAlgebra {
  private val hexRadix = 16
  private val iterations = 1000
  private val keyLength = 64 * 8
  private val saltLength = 16
  private val secureRandom = SecureRandom.getInstance("SHA1PRNG")
  private val keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")

  override def generateHash(
      secret: String,
      salt: Array[Byte] = getSalt,
      numberOfIterations: Int = iterations
  ): String = {
    val keySpec = new PBEKeySpec(secret.toCharArray, salt, iterations, keyLength)
    val hash = keyFactory.generateSecret(keySpec).getEncoded
    convertToHashString(iterations, toHexString(salt), toHexString(hash))
  }

  override def validateHash(secret: String, existingHash: String): Boolean = {
    val tokens = existingHash.split(":")
    if (tokens.length != 3) {
      false
    } else {
      val numberOfIterations = tokens(0).toInt
      val originalSaltArray = fromHexString(tokens(1))
      val generatedHash = generateHash(secret, originalSaltArray, numberOfIterations)
      existingHash.equals(generatedHash)
    }
  }

  private def convertToHashString(numberOfIterations: Int, salt: String, hash: String) =
    s"$numberOfIterations:$salt:$hash"

  private def getSalt: Array[Byte] =
    secureRandom.generateSeed(saltLength)

  private def toHexString(array: Array[Byte]): String = {
    val stringBuilder = new StringBuilder
    for (byte <- array)
      stringBuilder.append(String.format("%02x", byte))
    stringBuilder.toString()
  }

  /* Using BigInt to convert from hex string to byte array.
     If the size is not 16, then we drop the head as this will
     the sign of the BigInteger
   */
  private def fromHexString(hexString: String): Array[Byte] = {
    val array = BigInt(hexString, hexRadix).toByteArray
    if (array.length != saltLength) {
      array.tail
    } else {
      array
    }
  }
}
