package 重构.firstchapter;

public class ChildPrice implements Price{
    @Override
    public double price(int days) {
        return 1.5d;
    }

    @Override
    public int point(int days) {
        return 1;
    }
}
