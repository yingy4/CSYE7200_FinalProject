package csye_7200.faceDetect

import org.bytedeco.javacpp.opencv_imgcodecs
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

class ImageConversionSpec extends FlatSpec with Matchers with BeforeAndAfter{

  behavior of "toFrame"

  it should "work for Golden-Retriever-with-family.jpg" in {
    val frame = ImageConversion.toFrame(opencv_imgcodecs.imread("Golden-Retriever-with-family.jpg"))
    frame.imageWidth shouldBe 600
    frame.imageHeight shouldBe 399
    frame.imageChannels shouldBe 3
    frame.imageDepth shouldBe 8
  }

  behavior of "toMat"

  it should "work for Golden-Retriever-with-family.jpg" in {
    val mat1 = opencv_imgcodecs.imread("Golden-Retriever-with-family.jpg")
    val mat2 = ImageConversion.toMat(ImageConversion.toFrame(mat1))
    mat1 shouldBe mat2
  }

}