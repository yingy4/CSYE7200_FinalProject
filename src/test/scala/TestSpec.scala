import org.scalatest.{FlatSpec, Matchers}

class TestSpec extends FlatSpec with Matchers{
      "hello" should "match hi" in {
        Test.hello shouldBe "hi"
      }

    }
