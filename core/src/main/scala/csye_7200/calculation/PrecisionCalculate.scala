package csye_7200.calculation

import csye_7200.dogIdentify.TensorLabel
import csye_7200.folder.ImportFolder
import org.apache.spark.sql.SparkSession

object PrecisionCalculate extends App{
  implicit val spark = SparkSession
    .builder()
    .appName("FolderImage")
    .master("local[*]")
    .getOrCreate()

  //val dirPath = "input_image"

  def calculate(dirPath: String)(implicit spark: SparkSession): Double = {
    val test_val = ImportFolder.testFolder(dirPath) // RDD[(filename:String, Seq[Label])]
    val act_val = spark.read.textFile("labels.csv").rdd.map(x=>(x.split(",").head+".jpg", x.split(",").last))
    val reduce_res = act_val.join(test_val)
      .map(x => (TensorLabel.checkCorrect(x._2):Int, 1))
      .fold((0,0))(sumTuple(_,_))
    reduce_res._1.toDouble /reduce_res._2.toDouble
  }

  def sumTuple(t1:(Int,Int), t2:(Int,Int)):(Int,Int) = {
    (t1._1+t2._1, t1._2+t2._2)
  }

  implicit def booleanToInt(ori: Boolean):Int = ori match {
    case true => 1
    case _ => 0
  }

  println("Precision: "+calculate("input_test"))

}
