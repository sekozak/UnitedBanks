package driver.model;

import java.util.List;

public record UpdateTransactionTagsBody(Integer transactionId, List<String> tags) {
}
