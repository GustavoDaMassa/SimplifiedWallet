package br.com.gustavohenrique.simplifiedWallet.exception;

public class InvalidTransctionException extends RuntimeException {
    public InvalidTransctionException(String message) {
        super(message);
    }
}
