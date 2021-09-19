package 重构.简化条件表达式.使用null对象.refract;

public class Site {
    private Customer customer;

    public Customer getCustomer() {
        return customer == null ? Customer.newNull() : customer;
    }

    public void test() {
        String name = null;
        if (customer.isNull()) {
            name = customer.getName();
        } else {
            name = "o1";
        }

        int delinquent;
        if (customer.isNull()) {
            delinquent = 0;
        } else {
            delinquent = customer.getHistory().getDelinquent();
        }
    }
}
