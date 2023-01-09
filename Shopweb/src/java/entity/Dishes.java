package entity;

/**
 *
 * @author fpt shop
 */
public class Dishes {
    private int id;
    private String name;
    private String Description;
    private double price;
    private String image;    
    public Dishes() {
    }

    public Dishes(int id, String name, String Description, double price, String image) {
        this.id = id;
        this.name = name;
        this.Description = Description;
        this.price = price;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dishes{" + "id=" + id + ", name=" + name + ", Description=" + Description + ", price=" + price + ", image=" + image + '}';
    }

   
    
    
}
