package example.micronaut

import io.micronaut.runtime.Micronaut.*
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License


@OpenAPIDefinition(
	info = Info(
		title = "Todo API",
		version = "0.1",
		description = "Todo API",
		license = License(name = "Apache 2.0", url = "https://foo.bar")
	)
)
object Application {

	@JvmStatic
	fun main(args: Array<String>) {
		build()
			.args(*args)
			.packages("example.micronaut")
			.start()
	}

}





