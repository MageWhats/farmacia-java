package Controllers;

import Models.Employees;
import Models.EmployeesDao;
import Views.LoginView;
import Views.SystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class LoginController implements ActionListener {

    private final Employees employee;
    private final EmployeesDao employeeDao;
    private final LoginView loginView;

    public LoginController(Employees employee, EmployeesDao employeeDao, LoginView loginView) {
        this.employee = employee;
        this.employeeDao = employeeDao;
        this.loginView = loginView;

        // Registrar los escuchadores para la pantalla de Login
        this.loginView.btn_login.addActionListener(this);
        
        // Permite dar ENTER en la caja de contraseña para iniciar sesión rápido
        this.loginView.txt_password.addActionListener(this);
        
        // Centrar la pantalla al arrancar
        this.loginView.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginView.btn_login || e.getSource() == loginView.txt_password) {
            executeLoginProcess();
        }
    }

    private void executeLoginProcess() {
        String user = loginView.txt_username.getText().trim();
        String pass = String.valueOf(loginView.txt_password.getPassword()).trim();

        // Validar campos vacíos con .isEmpty() eficiente
        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese su usuario y contraseña.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Consultar a la base de datos con el método blindado contra Inyección SQL que ya hicimos
        Employees empActive = employeeDao.loginQuery(user, pass);

        if (empActive.getUsername() != null) {
            JOptionPane.showMessageDialog(null, "¡Bienvenido al sistema, " + empActive.getFullName() + "!", "Acceso Concedido", JOptionPane.INFORMATION_MESSAGE);
            
            // 1. Instanciamos la vista principal de la farmacia
            SystemView system = new SystemView();
            
            // 2. Inicializamos todos los controladores pasándoles la misma vista principal
            initAllSystemControllers(system);
            
            // 3. Mostramos la pantalla principal y cerramos el Login
            system.setVisible(true);
            loginView.dispose();
            
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos. Intente de nuevo.", "Acceso Denegado", JOptionPane.ERROR_MESSAGE);
            loginView.txt_password.setText("");
            loginView.txt_password.requestFocus();
        }
    }

    /**
     * Método Senior para arrancar todos los controladores del sistema de un solo golpe
     * al momento de iniciar sesión de forma exitosa.
     */
    private void initAllSystemControllers(SystemView system) {
        // Inicializar el controlador de productos
        new Controllers.ProductsController(new Models.Products(), new Models.ProductDao(), system);
        
        // Inicializar el controlador de compras
       new Controllers.PurchasesController(new Models.Purchases(), new Models.PurchasesDao(), new Models.ProductDao(), system);
        
        // Inicializar el controlador de ventas
        new Controllers.SalesController(new Models.Sales(), new Models.SalesDao(), new Models.ProductDao(), system, new Models.CustomersDao());
        
        // Inicializar el controlador de clientes
        new Controllers.CustomersController(new Models.Customers(), new Models.CustomersDao(), system);
        
        // Inicializar el controlador de colaboradores
        new Controllers.EmployeesController(new Models.Employees(), new Models.EmployeesDao(), system);
        
        // Inicializar el controlador de proveedores
        new Controllers.SuppliersController(new Models.Suppliers(), new Models.SuppliersDao(), system);
        
        // Inicializar el controlador de categorías
        new Controllers.CategoriesController(new Models.Categories(), new Models.CategoriesDao(), system);
        
        // Inicializar el controlador de reportes históricos
        new Controllers.ReportsController(new Models.SalesDao(), new Models.PurchasesDao(), system);
        
        // Inicializar el controlador de editar perfil / configuración
        new Controllers.SettingsController(new Models.Employees(), new Models.EmployeesDao(), system);
        
        // Forzamos a que la pantalla principal cargue el nombre y rol del usuario en sus etiquetas de bienvenida
       system.titleInterface();
    }
}
