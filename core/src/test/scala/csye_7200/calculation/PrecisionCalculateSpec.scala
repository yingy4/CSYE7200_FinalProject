package csye_7200.calculation

import org.apache.spark.sql.SparkSession
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

class PrecisionCalculateSpec extends FlatSpec with Matchers with BeforeAndAfter{
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

  behavior of "calculate"
  it should "work for folder_test" in {
    val accuracy = PrecisionCalculate.calculate("folder_test")
    accuracy shouldBe 1.0 +- 0.1
  }
}
