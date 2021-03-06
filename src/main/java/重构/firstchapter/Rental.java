package 重构.firstchapter;

public class Rental {

    private Movie movie;
    private int days;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Rental(Movie movie, int days) {
        this.movie = movie;
        this.days = days;
    }

    public double getAmount() {
        return getMovie().getPrice().price(getDays());
    }
}
