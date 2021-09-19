package 重构.简化条件表达式.使用null对象.tf;

import 重构.简化条件表达式.使用null对象.refract.NullPaymentHistory;

public class PaymentHistory {
    public int getDelinquent() {
        return 1;
    }

    public static PaymentHistory newNull() {
        return new NullPaymentHistory();
    }
}
