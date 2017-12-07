package csye_7200.folder

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

class ImportFolderSpec extends FlatSpec with Matchers with BeforeAndAfter{
  private var spark: SparkSession = _

  before {
    //sc = new SparkContext(new SparkConf().setAppName("FolderImage").setMaster("local[*]"))
    spark = SparkSession
      .builder()
      .appName("FolderImage")
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
    val imgs = ImportFolder.readFromFolder("test_image", spark)
    imgs.take(1).head should (include("Golden-Retriever-with-family.jpg"))
  }
}
