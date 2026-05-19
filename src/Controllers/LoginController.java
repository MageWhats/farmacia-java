
package Controllers;

import Models.Employees;
import Models.EmployeesDao;
import Views.LoginView;
import Views.SystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;



public class LoginController implements ActionListener{
    //Encapsular variables
    private Employees employee;
    private EmployeesDao employee_dao;
    private LoginView login_view;

    public LoginController(Employees employee, EmployeesDao employee_dao, LoginView login_view) {
        this.employee = employee;
        this.employee_dao = employee_dao;
        this.login_view = login_view;
        this.login_view.btn_ingresarLogin.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Obteniendo los datos de la vista
        String user = login_view.txt_UsuarioLogin.getText().trim();
        String pass= String.valueOf(login_view.txt_Pass.getPassword());
        if(e.getSource()== login_view.btn_ingresarLogin){
            if(!user.equals("") || !pass.equals("")){
                employee  = employee_dao.loginQuery(user, pass);
                if(employee.getUsername() !=null){
                    if(employee.getRol().equals("Administrador")){
                        SystemView admin = new SystemView();
                        admin.setVisible(true);
                    }else{
                        SystemView aux = new SystemView();
                        aux.setVisible(true);
                    }
                    this.login_view.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Usuario o Password Incorrecto");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Los campos o uno de los campos esta vacio");
            }
        }
    }
    
    
    
}
