package 重构.简化条件表达式.多态替换switch.refactor;

public class Employee {

    private EmployeeType type;

    public int getAmount() {
        return type.getAmount(this);
    }

    public int type() {
        return 0;
    }
    
    public int getBonus() {
        return 0;
    }

    public int getMonthSalary() {
        return 0;
    }

    public int getType() {
        return 0;
    }
}
