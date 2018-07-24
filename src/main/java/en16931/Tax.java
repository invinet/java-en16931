/* 
 * Copyright 2018 Invinet Sistemes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package en16931;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

/**
 * Class representing a Tax associated to an InvoiceLine.
 *
 */
public class Tax {

    private static final Set<String> CATEGORIES = new HashSet<String>(Arrays.asList(
        new String[] {"AE", "L", "M", "E", "S", "Z", "G", "O", "K"}
    ));
    private double percent;
    private String category;
    private String name;
    private String comment;

    /**
     *
     * @param percent the percentage of the tax
     * @param category the category code of the tax
     * @param name the name of the tax
     * @param comment a comment on the tax
     */
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

    /**
     * Returns the percentage of the tax
     *
     * @return the percentage
     */
    public double getPercent() {
        return percent;
    }

    /**
     * Returns a suitable string representation of the percentage of the tax
     * 
     * @return string representation of the percentage
     */
    public String getStringPercent() {
        // We want the dot as a decimal separator
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        DecimalFormat formatter = (DecimalFormat)nf;
        formatter.applyPattern("#0.00");
        return formatter.format(percent * 100);
    }

    /**
     * Sets the percentage of the tax
     *
     * @param percent the percentage
     */
    public void setPercent(double percent) {
        if (percent > 1 || percent < -1) {
            this.percent = percent / 100;
        } else {
            this.percent = percent;
        } 
    }

    /**
     * Returns the category code of the tax
     *
     * @return the category code
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category code of the tax
     * 
     * <p>It must be one of:
     * <ul>
     * <li>AE</li>
     * <li>L</li>
     * <li>M</li>
     * <li>E</li>
     * <li>S</li>
     * <li>Z</li>
     * <li>G</li>
     * <li>O</li>
     * <li>K</li>
     * </ul>
     * 
     * @param category the category code
     * 
     * @throws IllegalArgumentException if the category code is invalid.
     */
    public void setCategory(String category) {
        if (CATEGORIES.contains(category)) {
            this.category = category;
        } else {
            throw new IllegalArgumentException("category must be one of: 'AE', 'L', 'M', 'E', 'S', 'Z', 'G', 'O', or 'K'");
        }
    }

    /**
     * Returns the name of the tax
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the tax
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the comment on the tax
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets a comment on the tax
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Returns the hash code of a tax instance
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.percent) ^ (Double.doubleToLongBits(this.percent) >>> 32));
        hash = 43 * hash + Objects.hashCode(this.category);
        hash = 43 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     * Compares tax instances for equality.
     *
     * @param obj
     * @return boolean
     */
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
