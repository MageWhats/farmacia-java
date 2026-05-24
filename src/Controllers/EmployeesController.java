package Controllers;

import Models.Employees;
import Models.EmployeesDao;
import Views.SystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EmployeesController implements ActionListener, MouseListener, KeyListener {

    private final Employees employee;
    private final EmployeesDao employeeDao;
    private final SystemView views;
    private DefaultTableModel model = new DefaultTableModel();

    public EmployeesController(Employees employee, EmployeesDao employeeDao, SystemView views) {
        this.employee = employee;
        this.employeeDao = employeeDao;
        this.views = views;

        this.views.btn_colaborador_register.addActionListener(this); 
        this.views.btn_colaborador_update.addActionListener(this);   
        this.views.btn_colaborador_delete.addActionListener(this);   
        this.views.btn_colaborador_cancel.addActionListener(this);   

        this.views.txt_colaborador_search.addKeyListener(this);
        this.views.tb_colaborador.addMouseListener(this);
        this.views.jLabelColaboradores.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btn_colaborador_register) {
            executeRegister();
        } else if (e.getSource() == views.btn_colaborador_update) {
            executeUpdate();
        } else if (e.getSource() == views.btn_colaborador_delete) {
            executeDelete();
        } else if (e.getSource() == views.btn_colaborador_cancel) {
            cleanFields();
            views.btn_colaborador_register.setEnabled(true);
            views.txt_colaborador_password.setEnabled(true);
        }
    }

    private void executeRegister() {
        if (areFieldsEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Captura de la identificación desde la caja de texto del diseño
            employee.setIdCard(Integer.parseInt(views.txt_colaborador_id_card.getText().trim()));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "La identificación debe ser numérica.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        employee.setFullName(views.txt_colaborador_name.getText().trim());
        employee.setUsername(views.txt_colaborador_username.getText().trim());
        employee.setAddress(views.txt_colaborador_address.getText().trim()); 
        employee.setTelephone(views.txt_colaborador_telephone.getText().trim());
        employee.setEmail(views.txt_colaborador_email.getText().trim());
        employee.setPassword(String.valueOf(views.txt_colaborador_password.getPassword()).trim());
        employee.setRol(views.cb_colaborador_rol.getSelectedItem().toString().trim());

        if (employeeDao.registerEmployeeQuery(employee)) {
            JOptionPane.showMessageDialog(null, "Colaborador registrado exitosamente.");
            cleanFields();
            listAllEmployees();
        }
    }

    private void executeUpdate() {
        if (views.txt_colaborador_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Seleccione un registro para continuar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (areFieldsEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            employee.setId(Integer.parseInt(views.txt_colaborador_id.getText().trim()));
            employee.setIdCard(Integer.parseInt(views.txt_colaborador_id_card.getText().trim()));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Verifique los valores numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        employee.setFullName(views.txt_colaborador_name.getText().trim());
        employee.setUsername(views.txt_colaborador_username.getText().trim());
        employee.setAddress(views.txt_colaborador_address.getText().trim());
        employee.setTelephone(views.txt_colaborador_telephone.getText().trim());
        employee.setEmail(views.txt_colaborador_email.getText().trim());
        employee.setPassword(String.valueOf(views.txt_colaborador_password.getPassword()).trim());
        employee.setRol(views.cb_colaborador_rol.getSelectedItem().toString().trim());

        if (employeeDao.updateEmployeeQuery(employee)) {
            cleanFields();
            listAllEmployees();
            views.btn_colaborador_register.setEnabled(true);
            views.txt_colaborador_password.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Datos modificados de manera correcta.");
        }
    }

    private void executeDelete() {
        int row = views.tb_colaborador.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un colaborador.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int id = Integer.parseInt(views.tb_colaborador.getValueAt(row, 0).toString());
            if (id == EmployeesDao.id_user) {
                JOptionPane.showMessageDialog(null, "No puedes eliminar tu propio usuario en sesión.", "Restricción", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int question = JOptionPane.showConfirmDialog(null, "¿Eliminar este colaborador?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (question == JOptionPane.YES_OPTION && employeeDao.deleteEmployeeQuery(id)) {
                cleanFields();
                listAllEmployees();
                views.btn_colaborador_register.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Colaborador eliminado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error de ID.");
        }
    }

    private boolean areFieldsEmpty() {
        return views.txt_colaborador_id_card.getText().trim().isEmpty()
                || views.txt_colaborador_name.getText().trim().isEmpty()
                || views.txt_colaborador_username.getText().trim().isEmpty()
                || views.txt_colaborador_address.getText().trim().isEmpty()
                || views.txt_colaborador_telephone.getText().trim().isEmpty()
                || views.txt_colaborador_email.getText().trim().isEmpty();
                
    }

    public void cleanFields() {
        views.txt_colaborador_id.setText("");
        views.txt_colaborador_id_card.setText("");
        views.txt_colaborador_name.setText("");
        views.txt_colaborador_username.setText("");
        views.txt_colaborador_address.setText("");
        views.txt_colaborador_telephone.setText("");
        views.txt_colaborador_email.setText("");
        views.txt_colaborador_password.setText("");
    }

    public void listAllEmployees() {
        List<Employees> list = employeeDao.listEmployeesQuery(views.txt_colaborador_search.getText().trim());
        model = (DefaultTableModel) views.tb_colaborador.getModel();
        model.setRowCount(0);
        for (Employees emp : list) {
            Object[] row = new Object[8];
            row[0] = emp.getId();
            row[1] = emp.getIdCard(); 
            row[2] = emp.getFullName();
            row[3] = emp.getUsername();
            row[4] = emp.getAddress();
            row[5] = emp.getTelephone();
            row[6] = emp.getEmail();
            row[7] = emp.getRol();
            model.addRow(row);
        }
        views.tb_colaborador.setModel(model);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.tb_colaborador) {
            int row = views.tb_colaborador.getSelectedRow();
            if (row != -1) {
                views.txt_colaborador_id.setText(views.tb_colaborador.getValueAt( row, 0).toString());
                views.txt_colaborador_id_card.setText(views.tb_colaborador.getValueAt(row, 1).toString());
                views.txt_colaborador_name.setText(views.tb_colaborador.getValueAt(row, 2).toString());
                views.txt_colaborador_username.setText(views.tb_colaborador.getValueAt(row, 3).toString());
                views.txt_colaborador_address.setText(views.tb_colaborador.getValueAt(row, 4).toString());
                views.txt_colaborador_telephone.setText(views.tb_colaborador.getValueAt(row, 5).toString());
                views.txt_colaborador_email.setText(views.tb_colaborador.getValueAt(row, 6).toString());
                views.cb_colaborador_rol.setSelectedItem(views.tb_colaborador.getValueAt(row, 7).toString());
                
                views.btn_colaborador_register.setEnabled(false);
                views.txt_colaborador_password.setEnabled(false); 
            }
        } else if (e.getSource() == views.jLabelColaboradores) {
            views.jTabbedPane1.setSelectedIndex(4);
            listAllEmployees();
        }
    }

    @Override public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txt_colaborador_search) { listAllEmployees(); }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyPressed(KeyEvent e) {}
}
