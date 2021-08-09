package guru.springframework.sfgjms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SfgJmsApplication {

	public static void main(String[] args) throws Exception {
/* Comment out ActiveMQ server instantiation as we will be running it outside of the Spring Boot application now
		ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
		.setPersistenceEnabled(false)
		.setJournalDirectory("target/data/journal") 			// Set local build directory
		.setSecurityEnabled(false));							// Disable security, so anybody can connect to it
//		.addAcceptorConfiguration("invm", "vm://0"));	// Make the server accessible in the VM (from ActiveMQ server documentation). But this triggered an ugly error that 'Acceptor with id 0 already registered'

		server.start();											// Run ActiveMQ server within Spring Boot together with our client application (normally you would run this on a separate server, but is just for learning purposes)
*/

		SpringApplication.run(SfgJmsApplication.class, args);
	}

}
