package org.quintilis.common.storage

import java.nio.file.Path


interface FileProcessor {
    fun supports(type: FileType): Boolean
    fun process(event: FileProcessEvent, rootPath: Path)
}