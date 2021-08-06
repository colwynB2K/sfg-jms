package guru.springframework.sfgjms;

import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SfgJmsApplication {

	public static void main(String[] args) throws Exception {
		ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
		.setPersistenceEnabled(false)
		.setJournalDirectory("target/data/journal") 			// Set local build directory
		.setSecurityEnabled(false));							// Disable security, so anybody can connect to it
//		.addAcceptorConfiguration("invm", "vm://0"));	// Make the server accessible in the VM (from ActiveMQ server documentation). But this triggered an ugly error that 'Acceptor with id 0 already registered'

		server.start();											// Run ActiveMQ server within Spring Boot together with our client application (normally you would run this on a separate server, but is just for learning purposes)

		SpringApplication.run(SfgJmsApplication.class, args);
	}

}
