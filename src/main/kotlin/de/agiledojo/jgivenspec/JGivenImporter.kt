package de.agiledojo.jgivenspec

import com.google.gson.Gson
import java.io.File
import java.util.*

class JGivenImporter(private val exporter: SpecExporter) {

    fun read(importDir: File) {
        val gson = Gson()
        val features = ArrayList<Feature>()
        importDir.walk().forEach {
            if (it.isFile)
                features.add(gson.fromJson(it.readText(),Feature::class.java))
        }
        exporter.toHtml(features)
    }

}
