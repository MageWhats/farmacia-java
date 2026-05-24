package Models;

public class PurchaseDetail {
    // 1. Atributos alineados a la tabla intermedia y a tu vista
    private int id;             
    private int purchaseId;     
    private int productCode;    
    private String productName; 
    private int quantity;       
    private double price;      
    private double subtotal;    

  
    public PurchaseDetail() {
    }

    // 3. Constructor completo para manejar transacciones rápidas
    public PurchaseDetail(int id, int purchaseId, int productCode, String productName, int quantity, double price, double subtotal) {
        this.id = id;
        this.purchaseId = purchaseId;
        this.productCode = productCode;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
    }

 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
