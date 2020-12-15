package com.sb.jsr;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Jsr330ScopeMetadataResolver;

@SpringBootApplication
public class ExampleApplication  implements CommandLineRunner {
	
	private static Logger log = LoggerFactory.getLogger(ExampleApplication.class);
	
	private ConfigurableApplicationContext context;
	
	/* @Inject JSR#330 annotation equivalent to Spring @Autowired. */
	
	@Inject
	public ExampleApplication(ConfigurableApplicationContext context) {
		this.context = context;
	}
	
	public static void main(String ...args) {
		SpringApplication applicaiton = new SpringApplication(ExampleApplication.class);
		applicaiton.addInitializers(new JSR330Initializer());
		applicaiton.run(args);
	}
	
	
	private static class JSR330Initializer implements ApplicationContextInitializer<AnnotationConfigApplicationContext> {

		@Override
		public void initialize(AnnotationConfigApplicationContext applicationContext) {
			
			applicationContext.setScopeMetadataResolver(new Jsr330ScopeMetadataResolver());
			applicationContext.scan("com.sb.jsr.*");
		}
		
	}
	

	@Override
	public void run(String... args) throws Exception {
		String testBean1Scope = context.getBeanFactory().getBeanDefinition("testBean1").getScope();
		String testBean2Scope = context.getBeanFactory().getBeanDefinition("testBean2").getScope();
		String testBean3Scope = context.getBeanFactory().getBeanDefinition("testBean3").getScope();
        
		log.info("Test Bean1 Scope {}",testBean1Scope);
		log.info("Test Bean2 Scope {}",testBean2Scope);
		log.info("Test Bean3 Scope {}",testBean3Scope);
		
		
	}

}
