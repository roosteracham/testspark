package 重构.简化条件表达式.多态替换switch.refactor2;

import org.apache.http.util.Asserts;

public class EmployeeTest {
    public static void main(String[] args) {
        Employee tom = new Employee(new EngineerType());
        tom.setBonus(10);
        tom.setMonthSalary(100);
        System.out.println(tom.getMonthSalary());
        Asserts.check(100 == tom.getAmount(), "");
        Employee jim = new Employee(new SalesmanType());
        jim.setBonus(10);
        jim.setMonthSalary(100);
        System.out.println(jim.getAmount());
        Asserts.check(110 == jim.getAmount(), "");
    }
}
