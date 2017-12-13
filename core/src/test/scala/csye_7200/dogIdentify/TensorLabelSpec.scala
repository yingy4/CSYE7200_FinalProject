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
    val label = TensorLabel.detectBreed("Golden-Retriever-with-family.jpg").head.label
    label shouldBe "golden retriever"
  }

  behavior of "checkCorrect"
  it should "work for folder_test/02ff77af410e966b7b661f6f0789d947.jpg" in {
    val label1 = Label("golden retriever", 0.9366391f)
    val label2 = Label("labrador retriever", 0.028792022f)
    val label3 = Label("cocker spaniel", 0.0085327225f)
    val labels = Seq(label1, label2, label3)
    val res = TensorLabel.checkCorrect("golden_retriever", labels)
    res shouldBe true
  }
}
