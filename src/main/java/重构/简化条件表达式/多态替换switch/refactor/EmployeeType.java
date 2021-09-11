package 重构.简化条件表达式.多态替换switch.refactor;

public abstract class EmployeeType {

    public int getAmount(Employee employee) {
        switch (getType()) {
            case 0:
                return employee.getMonthSalary();
            case 1:
                return employee.getMonthSalary() + employee.getBonus();
            default:
                break;
        }
        return 0;
    }

    protected abstract int getType();
}
