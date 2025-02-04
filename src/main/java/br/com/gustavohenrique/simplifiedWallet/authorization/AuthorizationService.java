package br.com.gustavohenrique.simplifiedWallet.authorization;

import br.com.gustavohenrique.simplifiedWallet.transaction.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AuthorizationService {

    private final RestClient restClient;

    public AuthorizationService(RestClient.Builder restClient) {
        this.restClient = restClient.baseUrl("https://util.devi.tools/api/v2/authorize").build();
    }

    public  void authorize(Transaction transaction){

        var response = restClient.get().retrieve().toEntity(Authorization.class);

        if(response.getStatusCode().isError() || !response.getBody().isAuthorized()){
            throw new UnauthorizedTransactionException("Unauthorized transaction !!");
        }
    }
}
