package br.com.gustavohenrique.simplifiedWallet.authorization;

public record Authorization(
        String message
) {
    public boolean isAuthorized() {
        return true;
    }
}
