package Models;

public class PurchaseDetail {
    // 1. Atributos alineados a la tabla intermedia y a tu vista
    private int id;             // Llave primaria autoincremental de la tabla de detalle
    private int purchaseId;     // Llave foránea que amarra el detalle con la tabla 'purchases'
    private int productCode;    // Código único del producto (Columna 'Cod Producto' o 'Id' en la vista)
    private String productName; // Nombre del producto (Sirve para pintar datos en el JTable de forma temporal)
    private int quantity;       // Cantidad comprada
    private double price;       // Precio de compra unitario
    private double subtotal;    // Subtotal de la fila (precio * cantidad)

    // 2. Constructor vacío por defecto
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

    // 4. Métodos Getters y Setters limpios
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
