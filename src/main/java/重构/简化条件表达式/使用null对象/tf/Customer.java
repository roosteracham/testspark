package 重构.简化条件表达式.使用null对象.tf;

public class Customer {

    public String getName() {
        return "n1";
    }

    public PaymentHistory getHistory() {
        return new PaymentHistory();
    }


    public Billingzplan getHPlan() {
        return new Billingzplan();
    }

}
