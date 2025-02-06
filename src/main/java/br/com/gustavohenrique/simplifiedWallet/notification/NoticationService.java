package br.com.gustavohenrique.simplifiedWallet.notification;

import br.com.gustavohenrique.simplifiedWallet.transaction.Transaction;
import org.springframework.stereotype.Service;

@Service
public class NoticationService {

    private final NotificationProducer notificationProducer;

    public NoticationService(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }


    public void notify(Transaction transaction){
        notificationProducer.sendNotification(transaction);
    }

}
