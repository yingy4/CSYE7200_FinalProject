package csye_7200.folder

import csye_7200.dogIdentify.TensorLabel
import csye_7200.faceDetect.ImageFaceDetector
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object ImportFolder extends App {
  val spark = SparkSession
    .builder()
    .appName("FolderImage")
    .master("local[*]")
    .getOrCreate()

  val dirPath = "core/test_image"

  def readFromFolder(dirPath: String, spark: SparkSession): RDD[String] = {
    val test_images = spark.sparkContext.wholeTextFiles(dirPath, 5).map(x => x._1)
      .filter(x => x.endsWith(".jpg") || x.endsWith(".png") || x.endsWith("jpeg"))
      .map(_.substring(5))
    test_images
  }

//  case class TestLabel(dogLabel: String, filename: String, humanNum: Int)
//  def readLabel(filename: String, spark: SparkSession): RDD[TestLabel] = {
//    val label_list = spark.read.csv(filename).rdd.map(x => TestLabel(x(0).toString,x(1).toString,x(2).toString.toInt))
//    label_list
//  }

  val test_images = readFromFolder(dirPath, spark)
//  val faceList = test_images.map(ImageFaceDetector.markFace(_).toInt)
//  test_images.fold("0")((sum, temp) => (sum.toInt+ImageFaceDetector.markFace(temp).toInt).toString)
  test_images.foreach(ImageFaceDetector.markFace(_))
  test_images.foreach(TensorLabel.detectBreed(_))

  spark.stop()
}

