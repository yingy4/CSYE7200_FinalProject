package csye_7200.folder

import csye_7200.dogIdentify.TensorLabel
import csye_7200.faceDetect.ImageFaceDetector
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object ImportFolder extends App{
  val spark = SparkSession
    .builder()
    .appName("FolderImage")
    .master("local[*]")
    .getOrCreate()

  val dirPath = "test_image"

  def readFromFolder(dirPath:String, spark: SparkSession):RDD[String]= {
    val test_images = spark.sparkContext.wholeTextFiles(dirPath, 5).map(x => x._1)
      .filter(x => x.endsWith(".jpg") || x.endsWith(".png") || x.endsWith("jpeg"))
      .map(_.substring(5))
    test_images
  }

  val test_images = readFromFolder(dirPath, spark)
  test_images.foreach(ImageFaceDetector.markFace(_))
  test_images.foreach(TensorLabel.detectBreed(_))

  spark.stop()
}

