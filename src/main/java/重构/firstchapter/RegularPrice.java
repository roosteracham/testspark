package 重构.firstchapter;

public class RegularPrice implements Price {
    @Override
    public double price(int days) {
        int thisAmount = 2;
        if (days > 3) {
            thisAmount += (days - 3);
        }
        return thisAmount;
    }

    @Override
    public int point(int days) {
        return 1;
    }
}
