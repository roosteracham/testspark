package 重构.firstchapter;

public class NewReleasePrice implements Price{
    @Override
    public double price(int days) {
        int thisAmount = 3;
        if (days > 2) {
            thisAmount += (days - 2) * 2;
        }
        return thisAmount;
    }

    @Override
    public int point(int days) {
        if(days > 1) {
            return 2;
        }
        return 1;
    }
}
