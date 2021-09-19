package 重构.简化条件表达式.使用null对象.refract;

import 重构.简化条件表达式.使用null对象.tf.PaymentHistory;

public class NullCustomer extends Customer{
    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public String getName() {
        return "null name";
    }

    @Override
    public PaymentHistory getHistory() {
        return PaymentHistory.newNull();
    }
}
