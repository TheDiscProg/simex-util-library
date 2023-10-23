package thediscprog.utillibrary.caching

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import dapex.messaging.DapexMessage
import dapex.messaging.Method.{INSERT, SELECT, UPDATE}
import dapex.test.DapexMessageFixture
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ScaffeineCachingServiceTest
    extends AnyFlatSpec
    with Matchers
    with ScalaFutures
    with DapexMessageFixture {

  val sut = CachingService.cachingService[IO]()

  val selectMsg: DapexMessage = getMessage(SELECT)
  val updateMsg = getMessage(UPDATE)
  val insertMsg = getMessage(INSERT)

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
