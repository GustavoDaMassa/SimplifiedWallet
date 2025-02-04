package br.com.gustavohenrique.simplifiedWallet.transaction;

import br.com.gustavohenrique.simplifiedWallet.wallet.Wallet;
import br.com.gustavohenrique.simplifiedWallet.wallet.WalletRepository;
import br.com.gustavohenrique.simplifiedWallet.wallet.WalletType;
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

    private void validate(Transaction transaction){
        walletRepository.findById(transaction.payee()).map(payee -> walletRepository.findById(transaction.payer()).map(payer -> isTransactionValid(transaction, payer) ? transaction : null ).orElseThrow()).orElseThrow();
    }

    private boolean isTransactionValid(Transaction transaction, Wallet payer){
        return  payer.type() == WalletType.COMUM.getValue() && payer.balance().compareTo(transaction.value()) >= 0 && !payer.id().equals(transaction.payee());
    }
}




