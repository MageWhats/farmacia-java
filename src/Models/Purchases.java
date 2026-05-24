
package Models;

public class Purchases{
    // Atributos definidos por la base de datos de tu esquema
    private int id;             // `id` (INT, Autoincremental)
    private double total;       // `total` (DOUBLE)
    private String created;     // `created` (DATETIME maneja string en este diseño)
    private int supplierId;   
    private int employeeId;
    private String employeeName;    // `employee_id` (INT) - Convención Java
    private String supplierName;


    // Constructor vacío por defecto
    public Purchases() {
    }

    // Constructor para transferir datos rápido
    public Purchases(int id, double total, String created, int supplierId, String employeeName) {
        this.id = id;
        this.total = total;
        this.created = created;
        this.supplierId = supplierId;
        this.employeeName = employeeName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    // Getters y Setters limpios
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public String getSupplierName() {
    return supplierName;
}

public void setSupplierName(String supplierName) {
    this.supplierName = supplierName;
}
}
