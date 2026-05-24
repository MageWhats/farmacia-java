package Models;

public class Sales {
    private int id;
    private int customerId;      // camelCase profesional
    private int employeeId;      // camelCase profesional
    private double totalToPay;   // double primitivo y camelCase para el monto total
    private String saleDate;     // Fecha de la transacción
    
    // Atributos extendidos útiles para los JOINs y visualización en tablas
    private String customerName; // camelCase profesional
    private String employeeName; // camelCase profesional

    // Constructor vacío obligatorio
    public Sales() {
    }

    // Constructor completo optimizado
    public Sales(int id, int customerId, int employeeId, double totalToPay, String saleDate, String customerName, String employeeName) {
        this.id = id;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.totalToPay = totalToPay;
        this.saleDate = saleDate;
        this.customerName = customerName;
        this.employeeName = employeeName;
    }

    // Getters y Setters limpios
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getTotalToPay() {
        return totalToPay;
    }

    public void setTotalToPay(double totalToPay) {
        this.totalToPay = totalToPay;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
