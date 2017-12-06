package dogIdentify

case class Label(label: String, score: Float)

trait Labels {
  def getLabelOf(tensor: Array[Float], limit: Int = 5): Seq[Label]
}
