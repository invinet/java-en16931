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

/**
 * Class That represents a Postal Address of an Entity
 * 
 */
public class PostalAddress {

    private String address;
    private String postalZone;
    private String city;
    private String country;

    /**
     *
     * @param address the address
     * @param postalZone the postal zone
     * @param city the City.
     * @param country the country code in ISO-3166-1 alpha2 format
     */
    public PostalAddress(String address, String postalZone, String city, String country) {
        this.address = address;
        this.postalZone = postalZone;
        this.city = city;
        this.country = country;
    }

    /**
     * Returns the address of the Entity.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the Entity
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the postal zone of the Entity
     *
     * @return the postal zone
     */
    public String getPostalZone() {
        return postalZone;
    }

    /**
     * Sets the postal zone of the Entity
     *
     * @param postalZone the postal zone
     */
    public void setPostalZone(String postalZone) {
        this.postalZone = postalZone;
    }

    /**
     * Return the City of the Entity
     *
     * @return the City
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the City of the Entity
     *
     * @param city the City
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the country code of the Entity in ISO-3166-1 alpha2 format.
     *
     * @return the country code in ISO-3166-1 alpha2 format.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country code of the Entity in ISO-3166-1 alpha2 format.
     *
     * @param country the country code in ISO-3166-1 alpha2 format.
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
