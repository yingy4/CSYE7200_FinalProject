package csye_7200.folder

import csye_7200.dogIdentify.{Label, TensorLabel}
import csye_7200.faceDetect.ImageFaceDetector
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}

/* ImportFolder class used for read whole folder of img, based on Spark 2.0+
* input: inputDirectory: String
* output: Seq[(imgName, seq[Label])]
* side effect: print out every img name with their top3 dog breed guess
* */

object ImportFolder {
/*  implicit val spark = SparkSession
    .builder()
    .appName("FolderImage")
    .master("local[*]")
    .getOrCreate()*/

  //val dirPath = "input_image"

  def readFromFolder(dirPath: String)(implicit spark:SparkSession):RDD[String]= {
    val test_images = spark.sparkContext.wholeTextFiles(dirPath, 5).map(x => x._1)
      .filter(x => x.endsWith(".jpg") || x.endsWith(".png") || x.endsWith("jpeg"))
      .map(_.substring(5))
    test_images
  }

  def testFolder(dirPath: String)(implicit spark:SparkSession): RDD[(String, Seq[Label])] = {
    readFromFolder(dirPath).map(x=>(x.split("/").last, TensorLabel.detectBreed(x)))
  }

  //spark.stop()
}

