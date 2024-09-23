package tw.rc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class SpringDocConfig {
	//這是swagger產生api文件
	@Bean
	public OpenAPI baseOpenAPI() {
		OpenAPI openAPI = new OpenAPI();
		Components components = new Components();
		Info info = new Info();
		info.title("豬皮電子商務 API 文件").version("V2024092301").description("就是 API");
		
		openAPI.info(info)
				.components(components);
				
		
		return openAPI;
	}
	
}
