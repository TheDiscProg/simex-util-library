package thediscprog.utillibrary.jwt

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.time.{LocalDateTime, ZoneOffset}

class JwtSecurityTokenServiceTest extends AnyFlatSpec with Matchers {

  private val username = "test@test.com"
  private val secretKey = "secretkey"

  private val fixedDate = LocalDateTime.of(2023, 6, 13, 12, 0, 0)
  private val fixedInstant = fixedDate.toInstant(ZoneOffset.UTC)
  private val sut = SecurityTokenService(secretKey)

  it should "generate authorisation JWT token" in {
    val result = sut.generateAuthorisationBearerToken(username, fixedInstant)

    result shouldBe "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODY2NTc5MDAsImlhdCI6MTY4NjY1NzYwMCwidXNlcm5hbWUiOiJ0ZXN0QHRlc3QuY29tIiwibGV2ZWwiOiJiYXNpYyJ9.bi0xsUlzuos9y4XrsCIAwzuF3wJbeLGPgQMek7lFru0"
  }
}
