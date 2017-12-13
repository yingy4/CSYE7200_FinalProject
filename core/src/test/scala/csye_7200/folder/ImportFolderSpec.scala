package csye_7200.folder

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

class ImportFolderSpec extends FlatSpec with Matchers with BeforeAndAfter{
  implicit var spark: SparkSession = _

  before {
    spark = SparkSession
      .builder()
      .appName("FolderTest")
      .master("local[*]")
      .getOrCreate()
  }

  after {
    if (spark != null) {
      spark.stop()
    }
  }

  behavior of "readFromFolder"
  it should "work for test_image csye_7200.folder" in {
    val imgs = ImportFolder.readFromFolder("test_image")
    imgs.take(1).head should (include("Golden-Retriever-with-family.jpg"))
  }

  behavior of "testFolder"
  it should "work for test_image csye_7200.folder" in {
    val filename = ImportFolder.testFolder("test_image").collect().head._1
    filename shouldBe "Golden-Retriever-with-family.jpg"
  }
}
