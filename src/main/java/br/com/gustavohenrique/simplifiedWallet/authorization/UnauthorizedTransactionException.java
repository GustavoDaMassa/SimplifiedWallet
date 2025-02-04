package br.com.gustavohenrique.simplifiedWallet.authorization;

public class UnauthorizedTransactionException extends  RuntimeException{
    public UnauthorizedTransactionException (String message){
        super(message);
    }
}
