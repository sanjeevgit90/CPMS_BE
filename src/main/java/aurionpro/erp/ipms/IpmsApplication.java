package aurionpro.erp.ipms;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import io.swagger.models.parameters.Parameter;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class IpmsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(IpmsApplication.class, args);
	}

	/**
	 * this bean is for multiple authentication token testing purpose
	 * @return
	 */
	//@Bean
/*	public Docket swaggerConfiguration2(){
		
		springfox.documentation.service.Parameter authHeader = new ParameterBuilder()
				  .parameterType("header")
				  .name("Authorization")
				  .modelRef(new ModelRef("string"))
				  .build();
		
		return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.paths(PathSelectors.ant("/**")) //restrict based on path
					.apis(RequestHandlerSelectors.basePackage("aurionpro.erp.ipms")) //restrict base on package
					.build()
					.apiInfo(apiDetails())
					.groupName("Multi")
					//.securitySchemes(Arrays.asList(apiKey()))
	                //.securityContexts(Collections.singletonList(securityContext()));
					.globalOperationParameters(Collections.singletonList(authHeader));
	          }

	*/
	/**
	 * Single user testing, Single authentication token 
	 * @return
	 */
	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.paths(PathSelectors.ant("/**")) //restrict based on path
					.apis(RequestHandlerSelectors.basePackage("aurionpro.erp.ipms")) //restrict base on package
					.build()
					.apiInfo(apiDetails())
					.securitySchemes(Arrays.asList(apiKey()))
	                .securityContexts(Collections.singletonList(securityContext()));
					//;//.globalOperationParameters(Collections.singletonList(authHeader));
	          }
	
	          private SecurityContext securityContext() {
	            return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*")).build();
	          }

	          private List<SecurityReference> defaultAuth() {
	            final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
	            final AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
	            return Collections.singletonList(new SecurityReference("Bearer", authorizationScopes));
	          }

	          private ApiKey apiKey() {
	            return new ApiKey("Bearer", "Authorization", "header");
	          }

				
	

	private ApiInfo apiDetails(){
		return new ApiInfo("Aurionpro ERP IPMS Framework", "This framework will be used for API Development", "1.0.0", "For framework Use only", 
					new springfox.documentation.service.Contact("Jitendra Vijay Dubey","http://aurionpro.com", "jitendra.dubey@aurionpro.com"),
					 "Licensed to Aurionpro Solutions Ltd", "Copyright @2020", Collections.emptyList());
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	    return builder.sources(IpmsApplication.class);
	}

	//End Swagger Configuration
}
