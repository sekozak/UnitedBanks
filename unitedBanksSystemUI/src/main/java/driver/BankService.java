package driver;

import driver.model.PagesResponse;
import model.Transaction;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface BankService {

    @GET("/transactions")
    Call<PagesResponse> getPages(@Query("page") Integer page,
                                 @Query("size") Integer size,
                                 @Query("tags") List<String> tags);

    @GET("/transactions/tags")
    Call<List<String>>  getTagList();

    @POST("/transactions/{transactionId}/tags")
    Call<Transaction> updateTransactionTags(@Path("transactionId") Integer id, @Body List<String> tags);

}
