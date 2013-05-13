package org.github.mansur.scalastyle

import org.scalastyle.{Directory, FileSpec}
import java.io.File
import java.util.{List => jList}
import scala.collection.JavaConverters._

/**
 * @author Muhammad Ashraf
 * @since 5/12/13
 */
class ScalaStyleUtils {


  def getFilesToProcess(sourceFiles: jList[File], testFiles: jList[File], inputEncoding: String, includeTestSourceDirectory: Boolean): List[FileSpec] = {
    val sd = getFiles("sourceDirectory", asScalaBufferConverter(sourceFiles).asScala.toList, inputEncoding)
    val tsd = if (includeTestSourceDirectory) getFiles("testFiles", asScalaBufferConverter(testFiles).asScala.toList, inputEncoding) else Nil

    sd ::: tsd
  }

  def getFiles(name: String, file: List[File], encoding: String) = {
      Directory.getFiles(Option[String](encoding), file)
  }

  def isDirectory(file: File) = file != null && file.exists() && file.isDirectory
}
