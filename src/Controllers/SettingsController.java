package Controllers;

import Models.Employees;
import Models.EmployeesDao;
import Views.LoginView;
import Views.SystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class SettingsController implements ActionListener, MouseListener {

    private final Employees employee;
    private final EmployeesDao employeeDao;
    private final SystemView views;

    public SettingsController(Employees employee, EmployeesDao employeeDao, SystemView views) {
        this.employee = employee;
        this.employeeDao = employeeDao;
        this.views = views;

        // Escuchador para el botón "Modificar" de tu diseño
        this.views.btn_profile_update.addActionListener(this);

        // Escuchador para la opción del menú lateral que abre la pestaña de Configuración/Perfil
        this.views.jLabelSettings.addMouseListener(this);

        this.views.btn_loginOut.addActionListener(this);
        loadActiveUserProfile();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btn_profile_update) {
            updateProfileProcess();
        } else if (e.getSource() == views.btn_loginOut) {
            views.dispose(); // Cierra la farmacia

            LoginView loginWindow = new LoginView(); // Crea la ventana visual de acceso
            new Controllers.LoginController(new Employees(), new EmployeesDao(), loginWindow); // ¡Le da vida al Login!
            loginWindow.setVisible(true); // Lo muestra 
        }
    }

    // 1. Método para cargar los datos del usuario activo en las casillas al entrar
    public void loadActiveUserProfile() {
        // Obtenemos la lista completa de empleados filtrando por el nombre de usuario activo
        java.util.List<Employees> list = employeeDao.listEmployeesQuery(EmployeesDao.username_user);

        // Buscamos el objeto exacto que coincida con el ID de la sesión activa
        for (Employees emp : list) {
            if (emp.getId() == EmployeesDao.id_user) {
                // Inyectamos los datos en tus JTextFields de la izquierda
                views.txt_profile_id_card.setText(String.valueOf(emp.getIdCard()));
                views.txt_profile_id_card.setEditable(false); // ¡Seguridad!: No editable

                views.txt_profile_name.setText(emp.getFullName());
                views.txt_profile_address.setText(emp.getAddress());
                views.txt_profile_telephone.setText(emp.getTelephone());
                views.txt_profile_email.setText(emp.getEmail());
                views.txt_profile_id.setText(String.valueOf(emp.getId()));

                // Las casillas de contraseñas siempre arrancan vacías por privacidad
                views.txt_profile_new_password.setText("");
                views.txt_profile_confirm_password.setText("");
                break;
            }
        }
    }

    // 2. Procesa la modificación de datos y el cambio de contraseña
    private void updateProfileProcess() {
        if (areBasicFieldsEmpty()) {
            JOptionPane.showMessageDialog(null, "Los campos de información personal son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Extraemos las contraseñas de los JPasswordField de tu pantalla
        String newPass = String.valueOf(views.txt_profile_new_password.getPassword()).trim();
        String confirmPass = String.valueOf(views.txt_profile_confirm_password.getPassword()).trim();
        String finalPassword;

        // Validación crítica de contraseñas
        if (!newPass.isEmpty() || !confirmPass.isEmpty()) {
            // Si intentó escribir en alguna de las dos, deben coincidir estrictamente
            if (!newPass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(null, "Las contraseñas ingresadas no coinciden. Verifique por favor.", "Error de Seguridad", JOptionPane.ERROR_MESSAGE);
                return;
            }
            finalPassword = newPass; // Si coinciden, esta será la nueva contraseña
        } else {
            // Si dejó los dos campos vacíos, significa que no quiere cambiar su clave. 
            // Recuperamos la contraseña actual para no sobreescribirla en blanco.
            java.util.List<Employees> list = employeeDao.listEmployeesQuery(EmployeesDao.username_user);
            finalPassword = "";
            for (Employees emp : list) {
                if (emp.getId() == EmployeesDao.id_user) {
                    finalPassword = emp.getPassword();
                    break;
                }
            }
        }

        // Cargamos los datos modificados al modelo conservando el ID original de la sesión
        employee.setId(EmployeesDao.id_user);
        employee.setIdCard(Integer.parseInt(views.txt_profile_id_card.getText().trim()));
        employee.setFullName(views.txt_profile_name.getText().trim());
        employee.setAddress(views.txt_profile_address.getText().trim());
        employee.setTelephone(views.txt_profile_telephone.getText().trim());
        employee.setEmail(views.txt_profile_email.getText().trim());
        employee.setPassword(finalPassword); // Contraseña validada

        // El rol no se modifica en el perfil personal, conservamos el rol de la sesión activa
        employee.setRol(EmployeesDao.rol_user);

        // Impactamos los cambios en MySQL usando tu método robusto del DAO
        if (employeeDao.updateEmployeeQuery(employee)) {
            JOptionPane.showMessageDialog(null, "¡Tu perfil ha sido actualizado con éxito!");

            // Actualizamos el nombre global en memoria por si el usuario se cambió el nombre
            EmployeesDao.fullName_user = employee.getFullName();

            loadActiveUserProfile(); // Recarga los campos limpios
        }
    }

    private boolean areBasicFieldsEmpty() {
        return views.txt_profile_name.getText().trim().isEmpty()
                || views.txt_profile_address.getText().trim().isEmpty()
                || views.txt_profile_telephone.getText().trim().isEmpty()
                || views.txt_profile_email.getText().trim().isEmpty();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.jLabelSettings) {
            // Cambia al índice numérico de la pestaña Editar Perfil en tu SystemView
            views.jTabbedPane1.setSelectedIndex(8);

            // Precarga los datos en vivo del usuario logueado al abrir la pestaña
            loadActiveUserProfile();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
