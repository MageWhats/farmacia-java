
package Models;

public class Customers {
    private int id;              // Se usa solo para UPDATE, DELETE y consultas

    private String fullName;     
    private String address;      
    private String telephone;    
    private String email;        
    private String created;      
    private String updated;      

    public Customers() {}

    public Customers(int id, String fullName, String address, String telephone, String email, String created, String updated) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.created = created;
        this.updated = updated;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCreated() { return created; }
    public void setCreated(String created) { this.created = created; }
    public String getUpdated() { return updated; }
    public void setUpdated(String updated) { this.updated = updated; }
}
