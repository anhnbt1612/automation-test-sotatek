package test;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

public final class Product {
    private final String name;
    private final String price;
    private final String link;
    private final List<Character> redundantChars;

    public Product(String name, String price, String link, List<Character> redundantChars) {
        this.name = name;
        this.price = price;
        this.link = link;
        this.redundantChars = redundantChars;
    }

    public int getPriceNumber() {
        if (this.price == null) {
            return 0;
        }

        StringBuilder sb = new StringBuilder();
        for(char c : this.price.toCharArray()) {
            if (!this.redundantChars.contains(c)) {
                sb.append(c);
            }
        }
        return NumberUtils.toInt(sb.toString(),0);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", link='" + link + '\'' +
                ", redundantChars='" + redundantChars + '\'' +
                '}';
    }
}
