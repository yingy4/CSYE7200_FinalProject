package folder

import dogIdentify.TensorLabel
import faceDetect.ImageFaceDetector
import org.apache.spark.sql.SparkSession

object ImportFolder extends App{
  val spark = SparkSession
    .builder()
    .appName("FolderImage")
    .master("local[*]")
    .getOrCreate()

  val dirPath = "test_image"

  def readFromFolder() {
    val test_images = spark.sparkContext.wholeTextFiles(dirPath, 5).map(x => x._1)
      .filter(x => x.endsWith(".jpg") || x.endsWith(".png") || x.endsWith("jpeg"))
      .map(_.substring(5))
  }
  test_images.foreach(ImageFaceDetector.markFace(_))
  test_images.foreach(TensorLabel.detectBreed(_))

  spark.stop()
}

