package faceDetect

import org.bytedeco.javacpp.opencv_imgcodecs
import org.scalatest.{FlatSpec, Matchers}

class ImageFaceDetectorSpec extends FlatSpec with Matchers {

  behavior of "readImg"

  it should "work for Golden-Retriever-with-family.jpg" in {
    val mat = ImageFaceDetector.readImg("Golden-Retriever-with-family.jpg")
    mat.rows() shouldBe 399
    mat.cols() shouldBe 600
    mat.channels() shouldBe 3
  }

  behavior of "greyscale"

  it should "work for Golden-Retriever-with-family.jpg" in {
    val greyMat = ImageFaceDetector.greyscale(opencv_imgcodecs.imread("Golden-Retriever-with-family.jpg"))
    greyMat.rows() shouldBe 399
    greyMat.cols() shouldBe 600
    greyMat.channels() shouldBe 1
  }

  behavior of "equalHis"

  it should "work for Golden-Retriever-with-family.jpg" in {
    val greyMat = ImageFaceDetector.greyscale(opencv_imgcodecs.imread("Golden-Retriever-with-family.jpg"))
    val equalMat = ImageFaceDetector.equalHis(greyMat)
    equalMat.rows() shouldBe 399
    equalMat.cols() shouldBe 600
    equalMat.channels() shouldBe 1
  }
  /*
    behavior of "markFace"

    it should "work for Golden-Retriever-with-family.jpg" in {
      //val faceNum = ImageFaceDetector.markFace("Golden-Retriever-with-family.jpg")
      //faceNum shouldBe 3
    }*/
}
