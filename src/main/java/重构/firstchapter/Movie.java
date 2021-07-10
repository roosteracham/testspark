package 重构.firstchapter;

public class Movie {
    public static final int CHILD_RENT = 0;
    public static final int REGULAR = 1;
    public static final int NEW_RELEASE = 2;

    private String title;

    private int priceCode;

    private Price price;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
        setPrice(priceCode);
    }

    private void setPrice(int priceCode) {
        switch (priceCode) {
            case Movie.CHILD_RENT:
                price = new ChildPrice();
                break;
            case Movie.REGULAR:
                price = new RegularPrice();
                break;
            case Movie.NEW_RELEASE:
                price = new NewReleasePrice();
                break;
            default:
                throw new UnsupportedOperationException("no price object");
        }
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

    public Price getPrice() {
        return price;
    }

    public void setPriceCode(int priceCode) {
        this.priceCode = priceCode;
    }

    public int totalPoint(int days) {
        return price.point(days);
    }
}
