package br.com.gustavohenrique.simplifiedWallet;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.TopicForRetryable;

@EnableJdbcAuditing
@SpringBootApplication
public class SimplifiedWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplifiedWalletApplication.class, args);
	}

	@Bean
	NewTopic notificationTopic(){
		return TopicBuilder.name("transaction-notification").build();
	}
}
