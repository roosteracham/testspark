package 重构.firstchapter;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class CustomerV2 {
    private String name;
    private List<Rental> rantMovies;

    public CustomerV2(String name, List<Rental> movies) {
        this.name = name;
        this.rantMovies = movies;
    }

    public String statement() {
        if (CollectionUtils.isEmpty(rantMovies)) {
            return "dont rent any movie";
        }

        double totalAmount = 0;
        String res = this.name + " rent movie list:\n";

        for (Rental rantMovie : rantMovies) {
            double thisAmount = getAmount(rantMovie);
            totalAmount += thisAmount;
            Movie movie = rantMovie.getMovie();
            res += "movie: " + movie.getTitle() + " for " + rantMovie.getDays() + " days"
                    + "; price: " + thisAmount + "\n";
        }
        res += "total price to pay : " + totalAmount;
        return res;
    }

    private double getAmount(Rental rantMovie) {
        double thisAmount = 0;
        Movie movie = rantMovie.getMovie();
        switch (movie.getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (rantMovie.getDays() > 3) {
                    thisAmount += (rantMovie.getDays() - 3);
                }
                break;
            case Movie.CHILD_RENT:
                thisAmount += 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += 3;
                if (rantMovie.getDays() > 2) {
                    thisAmount += (rantMovie.getDays() - 2) * 2;
                }
                break;
            default:
                break;
        }
        return thisAmount;
    }
}
