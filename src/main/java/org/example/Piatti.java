/*PENSABENE*/
package org.example;



public class Piatti {
    String descrizione = "";
    int id = 0;
    String name = "";
    double price = 0.0;

    @Override
    public String toString() {
        return "Piatti{" +
                "descrizione='" + descrizione + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public Piatti(String descrizione, int id, String name, double price) {
        this.descrizione = descrizione;
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
