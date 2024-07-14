import java.io.File
import java.nio.charset.StandardCharsets
import java.util.*

fun main() {
    val file = File("C:\\Users\\rusla\\Android\\OTUS\\yusupova-server\\verses_ru.json")
    val lines: List<String> = file.readLines(StandardCharsets.UTF_8)
    val unique = HashSet<String>()

    for (line in lines) {
        val index = line.indexOf("\"id\":")
        if (index != -1) {
            val id = line.substringAfter("\"id\":").substringBefore(",")
            val bytes = ("Yusupova" + id).toByteArray()
            var newId = UUID.nameUUIDFromBytes(bytes).toString()
            if (unique.contains(newId)) {
                newId = UUID.nameUUIDFromBytes(newId.toByteArray()).toString()
            }
            unique.add(newId)

            println(line.substringBefore("\"id\":") + "\"id\": \"" + newId + "\",")
        } else {
            println(line)
        }
    }
}
