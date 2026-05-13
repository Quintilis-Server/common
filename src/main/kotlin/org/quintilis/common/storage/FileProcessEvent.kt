package org.quintilis.common.storage

import java.io.Serializable

data class FileProcessEvent(
    val tempPath: String,
    val fileName: String,
    val folder: String,
    val fileType: FileType,
) : Serializable
enum class FileType { IMAGE, VIDEO, GIF, DOCUMENT }