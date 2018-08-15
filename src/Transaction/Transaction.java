package Transaction;

import general.CarAuctionRESTCall;

public class Transaction extends CarAuctionRESTCall {
private String transactionId;
private String transactionType;
public String getTransactionId() {
	return transactionId;
}
public void setTransactionId(String transactionId) {
	this.transactionId = transactionId;
}
public String getTransactionType() {
	return transactionType;
}
public void setTransactionType(String transactionType) {
	this.transactionType = transactionType;
}
}
