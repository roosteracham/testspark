package 重构.简化条件表达式.多态替换switch.refactor2;

public class SalesmanType extends EmployeeType {

    @Override
    public int getAmount(Employee employee) {
        return employee.getMonthSalary() + employee.getBonus();
    }
}
