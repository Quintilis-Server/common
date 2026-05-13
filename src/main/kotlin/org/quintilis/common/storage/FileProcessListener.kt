package org.quintilis.common.storage

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.file.Paths

@Service
class FileProcessListener(
    private val processors: List<FileProcessor>,
    @Value("\${quintilis.storage.nfs-path}") private val nfsPath: String
) {
    @RabbitListener(queues = [RabbitConfig.FILE_PROCESS_QUEUE])
    fun onFileUpload(event: FileProcessEvent) {
        val processor = processors.find { it.supports(event.fileType) }
        processor?.process(event, Paths.get(nfsPath))
    }

}