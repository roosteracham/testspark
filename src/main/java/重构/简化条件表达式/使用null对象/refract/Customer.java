package 重构.简化条件表达式.使用null对象.refract;

import 重构.简化条件表达式.使用null对象.tf.Billingzplan;
import 重构.简化条件表达式.使用null对象.tf.PaymentHistory;

public class Customer implements Nullable{

    public String getName() {
        return "n1";
    }

    public PaymentHistory getHistory() {
        return new PaymentHistory();
    }


    public Billingzplan getHPlan() {
        return new Billingzplan();
    }

    @Override
    public boolean isNull() {
        return false;
    }

    public static Customer newNull() {
        return new NullCustomer();
    }
}
