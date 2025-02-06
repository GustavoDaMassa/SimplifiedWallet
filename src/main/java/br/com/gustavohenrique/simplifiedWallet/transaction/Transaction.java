package br.com.gustavohenrique.simplifiedWallet.transaction;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table("TRANSACTIONS")
public record Transaction(
        @Id Long id,
        Long payer,
        Long payee,
        BigDecimal amount,
        @CreatedDate LocalDateTime createdAt

) {
    public Transaction{
        amount = amount.setScale(2);
    }
}