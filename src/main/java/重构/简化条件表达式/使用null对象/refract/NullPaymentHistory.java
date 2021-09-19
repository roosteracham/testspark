package 重构.简化条件表达式.使用null对象.refract;

import 重构.简化条件表达式.使用null对象.tf.PaymentHistory;

public class NullPaymentHistory extends PaymentHistory {
    @Override
    public int getDelinquent() {
        return 0;
    }
}
