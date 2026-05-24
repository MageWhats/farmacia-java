
package Models;

public class Employees {
    private int id;
    private int idCard;          // ¡Agregado correctamente aquí, bro!
    private String fullName;     
    private String username;     
    private String address;      
    private String password;     
    private String telephone;    
    private String email;        
    private String rol;          
    private String created;      
    private String updated;      

    public Employees() {
    }

    public Employees(int id, int idCard, String fullName, String username, String address, String password, String telephone, String email, String rol, String created, String updated) {
        this.id = id;
        this.idCard = idCard;
        this.fullName = fullName;
        this.username = username;
        this.address = address;
        this.password = password;
        this.telephone = telephone;
        this.email = email;
        this.rol = rol;
        this.created = created;
        this.updated = updated;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCard() { return idCard; }
    public void setIdCard(int idCard) { this.idCard = idCard; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getCreated() { return created; }
    public void setCreated(String created) { this.created = created; }

    public String getUpdated() { return updated; }
    public void setUpdated(String updated) { this.updated = updated; }
}
