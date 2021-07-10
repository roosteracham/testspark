package 重构.firstchapter;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class CustomerV4 {
    private String name;
    private List<Rental> rantMovies;

    public CustomerV4(String name, List<Rental> movies) {
        this.name = name;
        this.rantMovies = movies;
    }

    public String statement() {
        if (CollectionUtils.isEmpty(rantMovies)) {
            return "dont rent any movie";
        }
        String res = this.name + " rent movie list:\n";
        for (Rental rantMovie : rantMovies) {
            Movie movie = rantMovie.getMovie();
            res += "movie: " + movie.getTitle() + " for " + rantMovie.getDays() + " days, " +
                    "earn point: " + movie.totalPoint(rantMovie.getDays())
                    + "; price: " + rantMovie.getAmount() + "\n";
        }
        res += "total price to pay : " + totalAmount();
        return res;
    }

    public double totalAmount() {
        double sum = 0;
        for (Rental rantMovie : rantMovies) {
            sum += rantMovie.getAmount();
        }
        return sum;
    }

}
