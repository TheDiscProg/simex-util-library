package io.github.thediscprog.utillibrary.caching

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import io.github.thediscprog.simexmessaging.messaging.Datum
import io.github.thediscprog.simexmessaging.messaging.Method.{INSERT, SELECT, UPDATE}
import io.github.thediscprog.simexmessaging.test.SimexTestFixture
import io.github.thediscprog.slogic.Xor

class ScaffeineCachingServiceTest
    extends AnyFlatSpec
    with Matchers
    with ScalaFutures
    with SimexTestFixture {

  val sut = CachingService.cachingService[IO]()

  val selectMsg = authenticationRequest
  val updateMsg =
    getMessage(UPDATE, Some("Person"), Vector(Datum("status", None, Xor.applyLeft("single"))))
  val insertMsg =
    getMessage(
      INSERT,
      Some("Person"),
      Vector(Datum("status", Some("UPDATE"), Xor.applyLeft("married")))
    )

  it should "store items in cache" in {
    val smsg = sut.storeInCache(SELECT.value, selectMsg)
    val umsg = sut.storeInCache(UPDATE.value, updateMsg)
    val imsg = sut.storeInCache(INSERT.value, insertMsg)

    s"$smsg:$umsg:$imsg" shouldBe "IO(select):IO(update):IO(insert)"
  }

  it should "retrieve items in cache" in {
    val msg = sut.getFromCache("update").unsafeToFuture()

    whenReady(msg) { m =>
      m shouldBe Some(updateMsg)
    }
  }

  it should "retrieve all keys in cache" in {
    val keys = sut.getAllKeys.unsafeToFuture()

    whenReady(keys) { k =>
      k should contain theSameElementsAs List("select", "insert", "update")
    }
  }
}
