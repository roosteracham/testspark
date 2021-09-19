package 重构.简化条件表达式.使用null对象.refract;

public class Site2 {

    private Customer customer;

    public Customer getCustomer() {
        return customer == null ? Customer.newNull() : customer;
    }

    public void test() {
        String name = customer.getName();
        int delinquent = customer.getHistory().getDelinquent();
    }
}
