package de.agiledojo.jgivenspec

import com.google.gson.Gson
import java.io.File
import java.nio.file.Path
import java.util.*

class JGivenImporter(private val exporter: SpecExporter) {

    fun read(importDir: Path) {
        val gson = Gson()
        val features = ArrayList<Feature>()
        importDir.toFile().walk().forEach {
            if (it.isFile)
                features.add(gson.fromJson(it.readText(),Feature::class.java))
        }
        exporter.toHtml(features)
    }
}
