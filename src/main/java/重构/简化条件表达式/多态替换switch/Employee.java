package 重构.简化条件表达式.多态替换switch;

public class Employee {
    public int getAmount() {
        int amount = 0;
        switch (getType()) {
            case 0:
                amount = getMonthSalary();
                break;
            case 1:
                amount = getMonthSalary() + getBonus();
                break;
            default:
                break;
        }
        return amount;
    }

    private int getBonus() {
        return 0;
    }

    private int getMonthSalary() {
        return 0;
    }

    private int getType() {
        return 0;
    }
}
