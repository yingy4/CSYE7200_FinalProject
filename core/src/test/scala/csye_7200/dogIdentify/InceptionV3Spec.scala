package csye_7200.dogIdentify

import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

class InceptionV3Spec extends FlatSpec with Matchers with BeforeAndAfter{
  val incept = new InceptionV3("model")

  behavior of "getBytes"
  it should "work for model/retrained_graph.pb" in {
    incept.getBytes.length shouldBe 87497560
  }

  behavior of "getLabelOf"
  it should "work for label.txt" in {
    val simis: Array[Float] = Array(0.01f, 0.01f, 0.91f, 0.01f, 0.01f,0.01f, 0.01f,0.01f, 0.01f,0.01f)
    incept.getLabelOf(simis, 5).head.label shouldBe "chihuahua"
  }
}
