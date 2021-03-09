package redis;

import java.util.Date;

public class MailContent {
    private String seller;
    String item;
    double price;
    String buyer;
    Date time = new Date();

    public MailContent(String seller, String item, double price, String buyer) {
        this.seller = seller;
        this.item = item;
        this.price = price;
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
