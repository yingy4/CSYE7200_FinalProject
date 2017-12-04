package faceDetect

import java.util.function.Supplier

import org.bytedeco.javacpp.opencv_core.Mat
import org.bytedeco.javacpp.opencv_imgproc
import org.bytedeco.javacpp.opencv_imgproc.CLAHE
import org.bytedeco.javacv.{Frame, OpenCVFrameConverter}

object ImageConversion {
  // Each thread gets its own greyMat for safety
  private val frameToMatConverter = ThreadLocal.withInitial(new Supplier[OpenCVFrameConverter.ToMat] {
    def get(): OpenCVFrameConverter.ToMat = new OpenCVFrameConverter.ToMat
  })

  private val clahe = ThreadLocal.withInitial(new Supplier[CLAHE] {
    def get(): CLAHE = opencv_imgproc.createCLAHE()
  })

  /**
    * Returns an OpenCV Mat for a given JavaCV frame
    */
  def toMat(frame: Frame): Mat = frameToMatConverter.get().convert(frame)

  /**
    * Returns a JavaCV Frame for a given OpenCV Mat
    */
  def toFrame(mat: Mat): Frame = frameToMatConverter.get().convert(mat)

}
