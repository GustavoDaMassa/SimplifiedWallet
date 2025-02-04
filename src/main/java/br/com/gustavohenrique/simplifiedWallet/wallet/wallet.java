package br.com.gustavohenrique.simplifiedWallet.wallet;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public record wallet(
        @Id Long id,
        String fullname,
        Long cpf,
        String email,
        String password,
        int type,
        BigDecimal balance
) {
}
