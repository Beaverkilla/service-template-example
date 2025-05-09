package au.com.hub24.servicetemplateexample

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GroundAllocatorApplication

private val logger: Logger = LoggerFactory.getLogger(GroundAllocatorApplication::class.java)

fun main(args: Array<String>) {
	logger.info("Starting Service Template Example Application. Logging is configured.")
	logger.info("Swagger at http://localhost:8080/swagger-ui/index.html.")

	runApplication<GroundAllocatorApplication>(*args)
}
