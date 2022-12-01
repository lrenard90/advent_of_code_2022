import java.io.File
class FileService {

    fun readText(fileName: String): String {
        return File(fileName).readText()
    }

}