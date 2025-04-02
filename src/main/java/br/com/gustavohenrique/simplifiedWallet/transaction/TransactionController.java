package br.com.gustavohenrique.simplifiedWallet.transaction;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transactions")
public class    TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @Operation(summary = "Realizar transação", description = "Cria uma transação entre um pagante e um recebedor de acordo com a carteira.")
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction){
        return transactionService.create(transaction);
    }


    @Operation(summary = "Listar transaçôes")
    @GetMapping
    public List<Transaction> list(){
        return transactionService.showTransactions();
    }
}
