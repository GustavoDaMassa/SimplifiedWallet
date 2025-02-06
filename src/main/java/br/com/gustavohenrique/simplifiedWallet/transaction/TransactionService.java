package br.com.gustavohenrique.simplifiedWallet.transaction;

import br.com.gustavohenrique.simplifiedWallet.authorization.AuthorizationService;
import br.com.gustavohenrique.simplifiedWallet.notification.NoticationService;
import br.com.gustavohenrique.simplifiedWallet.wallet.Wallet;
import br.com.gustavohenrique.simplifiedWallet.wallet.WalletRepository;
import br.com.gustavohenrique.simplifiedWallet.wallet.WalletType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final AuthorizationService authorizationService;
    private  final NoticationService noticationService;

    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository,AuthorizationService authorizationService, NoticationService noticationService ) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizationService = authorizationService;
        this.noticationService = noticationService;
    }

    @Transactional
    public Transaction create(Transaction transaction){

        validate(transaction);

        Transaction newTransaction = transactionRepository.save(transaction);

        var walletPayer= walletRepository.findById(transaction.payer()).orElseThrow( () -> new InvalidTransctionException("Payer not found"));
        var walletPayee= walletRepository.findById(transaction.payee()).orElseThrow( () -> new InvalidTransctionException("Payee not found"));
        walletRepository.save(walletPayer.debit(transaction.amount()));
        walletRepository.save(walletPayee.credit(transaction.amount()));

        authorizationService.authorize(transaction);

        noticationService.notify(transaction);

        return newTransaction;
    }

    private void validate(Transaction transaction){
        walletRepository.findById(transaction.payee()).map(payee -> walletRepository.findById(transaction.payer()).map(payer -> isTransactionValid(transaction, payer) ? transaction : null ).orElseThrow( () -> new InvalidTransctionException(" Invalid Transaction - %s".formatted(transaction)))).orElseThrow(() -> new InvalidTransctionException(" Invalid Transaction - %s".formatted(transaction)));
    }

    private boolean isTransactionValid(Transaction transaction, Wallet payer){
        return  payer.type() == WalletType.COMUM.getValue() && payer.balance().compareTo(transaction.amount()) >= 0 && !payer.id().equals(transaction.payee());
    }

    public List<Transaction> showTransactions(){
        return transactionRepository.findAll();
    }
}




