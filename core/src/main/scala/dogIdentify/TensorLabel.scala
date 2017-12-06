package dogIdentify

import java.io.{BufferedInputStream, ByteArrayOutputStream}
import java.net.URL
import java.nio.file.{Files, Paths}

object TensorLabel {
  def detectBreed(inputImage: String) {
    val jpgFile = inputImage
    val jpgAsBytes = jpgFile match {
      case urlString if urlString.startsWith("http") =>
        val url = new URL(urlString)
        val in = new BufferedInputStream(url.openStream())
        val out = new ByteArrayOutputStream()
        val buf = new Array[Byte](1024)
        var n = in.read(buf)
        while (n != -1) {
          out.write(buf, 0, n)
          n = in.read(buf)
        }
        val bytes = out.toByteArray
        out.close()
        in.close()
        bytes
      case file => Files.readAllBytes(Paths.get(file))
    }

    // define the model
    val model = new InceptionV3("model")

    // initialize TensorFlowProvider
    val provider = new TensorFlowProvider(model)

    // setting up input and output layers to classify, modify the output layer to "final_result", keep input layer same
    val inputLayer = "DecodeJpeg/contents"
    //val inputLayer = "Mul"
    //val outputLayer = "softmax"
    val outputLayer = "final_result"

    // get result of the outputLayer
    val result = provider.run(inputLayer -> jpgAsBytes, outputLayer)

    // get label of the top 5
    val label = model.getLabelOf(result.head, 5)

    // print out
    label foreach println

    // release resources
    provider.close()
  }
}
