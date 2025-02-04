package br.com.gustavohenrique.simplifiedWallet.transaction;

import br.com.gustavohenrique.simplifiedWallet.wallet.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    public Transaction create(Transaction transaction){

        Transaction newTransaction = transactionRepository.save(transaction);

        var wallet = walletRepository.findById(transaction.payer()).orElseThrow();
        walletRepository.save(wallet.debit(transaction.value()));


        return newTransaction;
    }

}


