package thediscprog.utillibrary.hashing

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PBKDF2HashingServiceTest extends AnyFlatSpec with Matchers {

  private val sut = PBKDF2HashingService()

  it should "generate secure hash for a given string" in {
    val secretString = "secretKey"
    val salt = "somesaltingforts".getBytes

    val result = sut.generateHash(secretString, salt, 1000)

    result shouldBe "1000:736f6d6573616c74696e67666f727473:97c6234feb4c4ea3f609aa1eda179f8b8aa911d6289441de1caf3f2e1f4a740aa567f6269c4ea1bcfa90bf4b0f8144b47fa507584f0e5ba8c5a6769fd4e0d57a"
  }

  it should "validate secure hash for a given string" in {
    val secretKey = "password1234!"

    val hash = sut.generateHash(secretKey)

    sut.validateHash(secretKey, hash) shouldBe true
  }
}
