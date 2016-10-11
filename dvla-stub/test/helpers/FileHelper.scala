package helpers

import scala.io.Source

/**
  * Created by rob on 11/10/16.
  */
object FileHelper {
  def readTestFile(fileType: String , id: String) : String = {
    val resource = getClass.getResourceAsStream(s"/resources/${fileType}/${id}.json")
    try Source.fromInputStream(resource).mkString.toString
    finally resource.close()
  }
}
