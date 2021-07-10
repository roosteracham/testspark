package 重构.firstchapter;

public class Movie {
    public static final int CHILD_RENT = 0;
    public static final int REGULAR = 1;
    public static final int NEW_RELEASE = 2;

    private String title;

    private int priceCode;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int priceCode) {
        this.priceCode = priceCode;
    }

    public int totalPoint(int days) {
        if (getPriceCode() == Movie.NEW_RELEASE &&
        days > 1) {
            return 2;
        }
        return 1;
    }
}
