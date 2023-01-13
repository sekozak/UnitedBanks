package driver;

import controller.model.TransactionResponse;
import driver.model.UpdateTransactionTagsBody;
import javafx.beans.Observable;
import model.Transaction;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface BankService {

    @GET("/transactions")
    Call<List<TransactionResponse>> getTransactions();

    @GET("/transactions/tags")
    Call<List<TransactionResponse>>  getTaggedTransactions(@Query("tags") List<String> tags);

    @POST("/transactions/{transactionId}/tags")
    Call<Transaction> updateTransactionTags(@Path("transactionId") Integer id, @Body List<String> tags);

}
