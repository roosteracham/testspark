package 重构.简化条件表达式.使用null对象.tf;

public class Site {
    public Customer customer;

    public void test() {
        String name = null;
        if (customer != null) {
            name = customer.getName();
        } else {
            name = "o1";
        }

        int delinquent;
        if (customer == null) {
            delinquent = 0;
        } else {
            delinquent = customer.getHistory().getDelinquent();
        }

    }
}
