package en16931;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author jtorrents
 */
public class Tax {

    private static final Set<String> CATEGORIES = new HashSet<String>(Arrays.asList(
        new String[] {"AE", "L", "M", "E", "S", "Z", "G", "O", "K"}
    ));
    private double percent;
    private String category;
    private String name;
    private String comment;

    public Tax(double percent, String category, String name, String comment) {
        if (percent > 1 || percent < -1) {
            this.percent = percent / 100;
        } else {
            this.percent = percent;
        }
        this.category = category;
        this.name = name;
        this.comment = comment;
    }

    public double getPercent() {
        return percent;
    }

    public String getStringPercent() {
        // We want the dot as a decimal separator
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        DecimalFormat formatter = (DecimalFormat)nf;
        formatter.applyPattern("#0.00");
        return formatter.format(percent * 100);
    }

    public void setPercent(double percent) {
        if (percent > 1 || percent < -1) {
            this.percent = percent / 100;
        } else {
            this.percent = percent;
        } 
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (CATEGORIES.contains(category)) {
            this.category = category;
        } else {
            throw new IllegalArgumentException("category must be one of: 'AE', 'L', 'M', 'E', 'S', 'Z', 'G', 'O', or 'K'");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.percent) ^ (Double.doubleToLongBits(this.percent) >>> 32));
        hash = 43 * hash + Objects.hashCode(this.category);
        hash = 43 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tax other = (Tax) obj;
        if (Double.doubleToLongBits(this.percent) != Double.doubleToLongBits(other.percent)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
}
