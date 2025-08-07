package au.com.hub24.servicetemplateexample

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.servers.Server
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@OpenAPIDefinition(
	servers = [Server(
		url = "https://service-template-example-683109210138.australia-southeast1.run.app",
		description = "Default Server URL"
	),
		Server(
		url = "http://localhost:8080",
		description = "Localhost URL"
	)]
)
@SpringBootApplication
class GroundAllocatorApplication

private val logger: Logger = LoggerFactory.getLogger(GroundAllocatorApplication::class.java)

fun main(args: Array<String>) {
	logger.info("Starting Service Template Example Application. Logging is configured.")
	logger.info("Swagger at http://localhost:8080/swagger-ui/index.html.")
	if (!logger.isTraceEnabled) {
		logger.warn("Failed to enable trace logging! Misconfigured!")
	} else {
		logger.debug("Logging is configured correctly.")
	}

	runApplication<GroundAllocatorApplication>(*args)
}
