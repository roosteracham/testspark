package 重构.简化条件表达式.多态替换switch.refactor2;

public class Employee {

    private EmployeeType type;

    private int monthSalary;

    private int bonus;

    public Employee() {
    }

    public Employee(EmployeeType type) {
        this.type = type;
    }

    public void setMonthSalary(int monthSalary) {
        this.monthSalary = monthSalary;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getAmount() {
        return type.getAmount(this);
    }

    public int type() {
        return 0;
    }
    
    public int getBonus() {
        return bonus;
    }

    public int getMonthSalary() {
        return monthSalary;
    }
}
