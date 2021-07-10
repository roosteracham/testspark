package 重构.firstchapter;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class CustomerV3 {
    private String name;
    private List<Rental> rantMovies;

    public CustomerV3(String name, List<Rental> movies) {
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
            double thisAmount = rantMovie.getAmount();
            totalAmount += thisAmount;
            Movie movie = rantMovie.getMovie();
            res += "movie: " + movie.getTitle() + " for " + rantMovie.getDays() + " days"
                    + "; price: " + thisAmount + "\n";
        }
        res += "total price to pay : " + totalAmount;
        return res;
    }
}
