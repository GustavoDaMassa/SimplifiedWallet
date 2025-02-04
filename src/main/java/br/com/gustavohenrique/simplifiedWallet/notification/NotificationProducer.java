package br.com.gustavohenrique.simplifiedWallet.notification;

import br.com.gustavohenrique.simplifiedWallet.transaction.Transaction;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {
    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(Transaction transaction){
        kafkaTemplate.send("transaction-notification", transaction);
    }
}
