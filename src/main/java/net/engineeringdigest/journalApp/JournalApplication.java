
package net.engineeringdigest.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
//@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}
	
	
//	@Bean
//	public PlatformTransactionManager add(MongoDatabaseFactory dbfactory)
//	{
//		return new MongoTransactionManager(dbfactory);
//	}
}
