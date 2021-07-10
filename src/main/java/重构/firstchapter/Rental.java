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
        double thisAmount = 0;
        Movie movie = getMovie();
        switch (movie.getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (getDays() > 3) {
                    thisAmount += (getDays() - 3);
                }
                break;
            case Movie.CHILD_RENT:
                thisAmount += 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += 3;
                if (getDays() > 2) {
                    thisAmount += (getDays() - 2) * 2;
                }
                break;
            default:
                break;
        }
        return thisAmount;
    }
}
