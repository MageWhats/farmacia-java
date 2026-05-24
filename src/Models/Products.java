
package Models;

public class Products {
    private int id;
    private int code_product;
    private String name; 
    private String description;
    private double unitPrice; 
    private int productQuantity; 
    private String created;
    private String update;
    private int categoryId; 
    private String categoryName;

 
    public Products() {
    }

  
    public Products(int id, int code_product, String name, String description, double unitPrice, int productQuantity, String created, String update, int categoryId, String categoryName) {
        this.id = id;
        this.code_product = code_product;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.productQuantity = productQuantity;
        this.created = created;
        this.update = update;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // Getters y Setters limpios
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code_product;
    }

    public void setCode(int code) {
        this.code_product = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
