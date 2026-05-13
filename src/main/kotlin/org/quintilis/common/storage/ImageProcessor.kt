package org.quintilis.common.storage

import net.coobird.thumbnailator.Thumbnails
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class ImageProcessor: FileProcessor {
    override fun supports(type: FileType): Boolean = type == FileType.IMAGE

    override fun process(event: FileProcessEvent, rootPath: Path) {
        val source = Paths.get(event.tempPath)
        val target = rootPath.resolve(event.folder).resolve(event.fileName)

        Files.createDirectories(target.parent)

        Thumbnails.of(source.toFile())
            .size(1280, 720)
            .outputQuality(0.8)
            .toFile(target.toFile())

        Files.deleteIfExists(source) // Limpa o temporário

    }
}