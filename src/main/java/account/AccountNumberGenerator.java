package account;

import java.util.Random;

public class AccountNumberGenerator {

	public static String generateAccountNumber() {
        
		Random random = new Random();
	    boolean accountNumberExists = true;
	    String accountNumber = "";

	    while (accountNumberExists) {
	        StringBuilder accountNumberBuilder = new StringBuilder("2042");
	        for (int i = 0; i < 6; i++) {
	            accountNumberBuilder.append(random.nextInt(10));
	        }

	        // 생성된 계좌 번호를 사용하여 계좌가 있는지 확인
	        accountNumber = accountNumberBuilder.toString();
	        long accountNumberLong = Long.parseLong(accountNumber);
	        AccountDAO dao = new AccountDAO();
	        AccountVO account = dao.getAccount(accountNumberLong);
	        
	        if (account == null) {
	            accountNumberExists = false;
	        }
	    }

        return accountNumber.toString();
    }
}
