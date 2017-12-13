package csye_7200.example

import csye_7200.dogIdentify.TensorLabel
import csye_7200.faceDetect.ImageFaceDetector

object SingleImgProcess extends App{
  val imgPath = "test_image/Golden-Retriever-with-family.jpg"

  ImageFaceDetector.markFace(imgPath)
  TensorLabel.detectBreed(imgPath)
}
