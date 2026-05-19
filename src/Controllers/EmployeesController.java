package Controllers;

import Models.Employees;
import Models.EmployeesDao;
import static Models.EmployeesDao.id_user;
import static Models.EmployeesDao.rol_user;
import Views.SystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EmployeesController implements ActionListener, MouseListener, KeyListener {

    private Employees employee;
    private EmployeesDao employeesDao;
    private SystemView views;
    String rol = rol_user;
    DefaultTableModel model = new DefaultTableModel();

    public EmployeesController(Employees employee, EmployeesDao employeesDao, SystemView views) {
        this.employee = employee;
        this.employeesDao = employeesDao;
        this.views = views;

        //Boton de registrar empleados
        this.views.btn_colaborator_register.addActionListener(this);

        //Poner a la escucha la tabla
        this.views.tb_colaborator.addMouseListener(this);

        //txt buscar empleados a la escucha
        this.views.txt_colaborator_search.addKeyListener(this);

        //Boton de modificar empleados
        this.views.btn_colaborator_modificar.addActionListener(this);

        //Boton de eliminar
        this.views.btn_colaborator_remove.addActionListener(this);

        //Boton cancelar
        this.views.btn_colaborator_cancel.addActionListener(this);

        //Boton modificar contraseña
        this.views.btn_profile_modificar.addActionListener(this);

        //Colocar el label_colaborator
        this.views.jLabelColaborator.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btn_colaborator_register) {
            if (views.txt_colaborator_id.getText().equals("")
                    || views.txt_colaborator_name.getText().equals("")
                    || views.txt_colaborator_nameUser.getText().equals("")
                    || views.txt_colaborator_address.getText().equals("")
                    || views.txt_colaborator_phone.getText().equals("")
                    || views.txt_colaborator_mail.getText().equals("")
                    || views.cb_colaborator_rol.getSelectedItem().toString().equals("")
                    || String.valueOf(views.txt_colaborator_pass.getPassword()).equals("")) {
                JOptionPane.showMessageDialog(null, "Todos los campos son Obligatorios");

            } else {
                employee.setId(Integer.parseInt(views.txt_colaborator_id.getText().trim()));
                employee.setFull_name(views.txt_colaborator_name.getText().trim());
                employee.setUsername(views.txt_colaborator_nameUser.getText().trim());
                employee.setAddress(views.txt_colaborator_address.getText().trim());
                employee.setTelephone(views.txt_colaborator_phone.getText().trim());
                employee.setEmail(views.txt_colaborator_mail.getText().trim());
                employee.setPassword(String.valueOf(views.txt_colaborator_pass.getPassword()));
                employee.setRol(views.cb_colaborator_rol.getSelectedItem().toString());

                if (employeesDao.registerEmployeeQuery(employee)) {

                    JOptionPane.showMessageDialog(null, "Empleado registrado con exito");
                    cleanFields();
                    cleanTable();
                    listAllEmployees();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un error al registrar el empleado");
                }
            }
        } else if (e.getSource() == views.btn_colaborator_modificar) {
            if (views.txt_colaborator_id.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila de la tabla para continuar");
            } else {
                if (views.txt_colaborator_name.getText().equals("")
                        || views.cb_colaborator_rol.getSelectedItem() == null
                        || views.cb_colaborator_rol.getSelectedItem().toString().equals("")) {

                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                } else {
                    employee.setId(Integer.parseInt(views.txt_colaborator_id.getText()));
                    employee.setFull_name(views.txt_colaborator_name.getText().trim());
                    employee.setUsername(views.txt_colaborator_nameUser.getText().trim());
                    employee.setAddress(views.txt_colaborator_address.getText().trim());
                    employee.setTelephone(views.txt_colaborator_phone.getText().trim());
                    employee.setEmail(views.txt_colaborator_mail.getText().trim());
                    employee.setPassword(String.valueOf(views.txt_colaborator_pass.getPassword()));
                    employee.setRol(views.cb_colaborator_rol.getSelectedItem().toString());
                    if (employeesDao.updateEmployeeQuery(employee)) {
                        cleanTable(); // Primero limpia los datos viejos de la tabla
                        listAllEmployees(); // Luego carga los datos actualizados
                        cleanFields();//Limpiar cuadro de textos formularios
                        views.btn_category_register.setEnabled(true);
                        JOptionPane.showMessageDialog(null, "Datos modificados exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar el empleado");
                    }

                }
            }
        } else if (e.getSource() == views.btn_colaborator_remove) {
            int row = views.tb_colaborator.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un empleado para eliminar");
            } else if (views.tb_colaborator.getValueAt(row, 0).equals(id_user)) {
                JOptionPane.showMessageDialog(null, "No puedo eliminar al usuario autenticado");
            } else {
                int id = Integer.parseInt(views.tb_colaborator.getValueAt(row, 0).toString());
                int question = JOptionPane.showConfirmDialog(null, "En realidad quieres eliminar el empleado");
                if (question == 0 && employeesDao.deleteEmployeeQuery(id) != false) {
                    cleanTable();
                    cleanFields();
                    views.btn_colaborator_register.setEnabled(true);
                    views.txt_colaborator_pass.setEnabled(true);
                    listAllEmployees();
                    JOptionPane.showMessageDialog(null, "El usuario fue eliminado con exito");
                }
            }
        } else if (e.getSource() == views.btn_colaborator_cancel) {
            cleanFields();
            views.btn_colaborator_register.setEnabled(true);
            views.txt_colaborator_pass.setEnabled(true);
            views.txt_colaborator_id.setEditable(true);
            views.txt_colaborator_id.setEnabled(true);

        } else if (e.getSource() == views.btn_profile_modificar) {
            String password = String.valueOf(views.txt_profile_newPass.getPassword());
            String password_confirm = String.valueOf(views.txt_profile_confirmPass.getPassword());
            if (!password.equals("") && !password_confirm.equals("")) {
                if (password.equals(password_confirm)) {
                    employee.setPassword(String.valueOf(views.txt_profile_newPass.getPassword()));
                    if (employeesDao.updateEmployeePassword(employee) != false) {
                        JOptionPane.showMessageDialog(null, "Se modifico correctamente la contraseña");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar la contraseña");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            }
        }
    }

    //Listar todos los empleados
    public void listAllEmployees() {
        if (rol.equals("Administrador")) {
            List<Employees> list = employeesDao.listEmployeesQuery(views.txt_colaborator_search.getText());
            model = (DefaultTableModel) views.tb_colaborator.getModel();
            Object[] row = new Object[7];
            for (int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getId();
                row[1] = list.get(i).getFull_name();
                row[2] = list.get(i).getUsername();
                row[3] = list.get(i).getAddress();
                row[4] = list.get(i).getTelephone();
                row[5] = list.get(i).getEmail();
                row[6] = list.get(i).getRol();
                model.addRow(row);
            }

        }
    }

    //Limpiar campos
    public void cleanFields() {
        views.txt_colaborator_id.setText("");
        views.txt_colaborator_id.setEditable(true);
        views.txt_colaborator_name.setText("");
        views.txt_colaborator_nameUser.setText("");
        views.txt_colaborator_address.setText("");
        views.txt_colaborator_phone.setText("");
        views.txt_colaborator_mail.setText("");
        views.txt_colaborator_pass.setText("");
        views.txt_colaborator_pass.setEnabled(true);
        views.cb_colaborator_rol.setSelectedItem(0);
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        if (e.getSource() == views.tb_colaborator) {
            int row = views.tb_colaborator.rowAtPoint(e.getPoint());
            views.txt_colaborator_id.setText(views.tb_colaborator.getValueAt(row, 0).toString());
            views.txt_colaborator_name.setText(views.tb_colaborator.getValueAt(row, 1).toString());
            views.txt_colaborator_nameUser.setText(views.tb_colaborator.getValueAt(row, 2).toString());
            views.txt_colaborator_address.setText(views.tb_colaborator.getValueAt(row, 3).toString());
            views.txt_colaborator_phone.setText(views.tb_colaborator.getValueAt(row, 4).toString());
            views.txt_colaborator_mail.setText(views.tb_colaborator.getValueAt(row, 5).toString());
            views.cb_colaborator_rol.setSelectedItem(views.tb_colaborator.getValueAt(row, 6).toString());

            views.txt_colaborator_id.setEditable(false);
            views.txt_colaborator_pass.setEditable(false);
            views.btn_colaborator_register.setEnabled(false);
        } else if (e.getSource() == views.jLabelColaborator) {
            if (rol.equals("Administrador")) {
                views.jTabbedPane1.setSelectedIndex(4);
                cleanTable();
                cleanFields();
                listAllEmployees();
            } else {
                views.jTabbedPane1.setEnabledAt(4, false);
                views.jLabelColaborator.setEnabled(false);
                JOptionPane.showMessageDialog(null, "No tienes privilegios de Administrador");
            }
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txt_colaborator_search) {
            cleanTable();
            listAllEmployees();

        }
    }

    //Limpiar Tabla
    public void cleanTable() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }

    }

}
