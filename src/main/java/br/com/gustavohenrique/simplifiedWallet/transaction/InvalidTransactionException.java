package br.com.gustavohenrique.simplifiedWallet.transaction;

public class InvalidTransctionException extends RuntimeException {
    public InvalidTransctionException(String message) {
        super(message);
    }
}
