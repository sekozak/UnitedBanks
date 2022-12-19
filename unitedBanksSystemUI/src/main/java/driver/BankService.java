package driver;

import controller.model.TransactionResponse;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface BankService {

    @GET("/transactions")
    Call<List<TransactionResponse>> getTransactions();

}
