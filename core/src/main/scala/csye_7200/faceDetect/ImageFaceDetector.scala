package csye_7200.faceDetect

import java.awt.{ Color, Font }
import java.io.File
import java.nio.file.Path
import javax.imageio.ImageIO

import org.bytedeco.javacpp.opencv_core
import org.bytedeco.javacpp.opencv_core.{ Mat, RectVector }
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier
import org.bytedeco.javacpp.{ opencv_imgcodecs, opencv_imgproc }
import org.bytedeco.javacv.Java2DFrameConverter

import scala.xml.Source

object ImageFaceDetector {

  val conv = new Java2DFrameConverter
  val faceXml = this.getClass.getClassLoader.getResource("haarcascade_frontalface_alt.xml").getPath
  val faceCascade = new CascadeClassifier(faceXml)

  def readImg(inputImage: String): Mat = {
    opencv_imgcodecs.imread(inputImage)
  }

  def greyscale(mat: Mat): Mat = {
    // convert image to greyscale
    val greyMat = new Mat()
    opencv_imgproc.cvtColor(mat, greyMat, opencv_imgproc.CV_BGR2GRAY, 1)
    greyMat
  }

  // equalize histogram
  def equalHis(greyMat: Mat): Mat = {
    val equalizedMat = new Mat()
    opencv_imgproc.equalizeHist(greyMat, equalizedMat)
    equalizedMat
  }

  // mark face
  def markFace(inputImage: String): Long = {
    print(inputImage)
    val mat = readImg(inputImage)
    val equalizedMat = equalHis(greyscale(mat))
    val faceRects = new RectVector()
    faceCascade.detectMultiScale(equalizedMat, faceRects)
    //faceCascade.detectMultiScale(equalizedMat,faceRects,1.1,3,0,new opencv_core.Size(), new opencv_core.Size())
    val image = conv.getBufferedImage(ImageConversion.toFrame(mat))
    val graphics = image.getGraphics
    graphics.setColor(Color.RED)
    for (i <- 0L until faceRects.size()) {
      val faceRect = faceRects.get(i)
      graphics.drawRect(faceRect.x, faceRect.y, faceRect.width, faceRect.height)
      graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18))
      graphics.drawString(s"Face $i", faceRect.x, faceRect.y - 20)
    }
    // image: RenderedImage
    ImageIO.write(image, "jpg", new File(inputImage))
    faceRects.size()
  }

  //markFace("Golden-Retriever-with-family.jpg")

}
