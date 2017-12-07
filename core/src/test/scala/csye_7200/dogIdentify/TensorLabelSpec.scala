package csye_7200.dogIdentify

import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

class TensorLabelSpec extends FlatSpec with Matchers with BeforeAndAfter{
  behavior of "jpgToBytes"

  it should "work for Golden-Retriever-with-family.jpg" in {
    val bytes = TensorLabel.jpgToBytes("Golden-Retriever-with-family.jpg")
    bytes.toList.head shouldBe -1
  }

  behavior of "detectBreed"
  it should "work for Golden-Retriever-with-family.jpg" in {
    val label = TensorLabel.detectBreed("Golden-Retriever-with-family.jpg")
    label shouldBe "golden retriever"
  }
}
