package Controllers;

import Models.Customers;
import Models.CustomersDao;
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

public class CustomersController implements ActionListener, MouseListener, KeyListener {

    private final Customers customer;
    private final CustomersDao customerDao;
    private final SystemView views;
    private DefaultTableModel model = new DefaultTableModel();

    public CustomersController(Customers customer, CustomersDao customerDao, SystemView views) {
        this.customer = customer;
        this.customerDao = customerDao;
        this.views = views;

        // Escuchadores de botones
        this.views.btn_customer_register.addActionListener(this);
        this.views.btn_customer_update.addActionListener(this);
        this.views.btn_customer_delete.addActionListener(this);
        this.views.btn_customer_cancel.addActionListener(this);
        
        // Tablas y barra de búsqueda
        this.views.txt_customer_search.addKeyListener(this);
        this.views.tb_customer.addMouseListener(this);
        this.views.jLabelCustomers.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btn_customer_register) {
            executeRegister();
        } else if (e.getSource() == views.btn_customer_update) {
            executeUpdate();
        } else if (e.getSource() == views.btn_customer_delete) {
            executeDelete();
        } else if (e.getSource() == views.btn_customer_cancel) {
            cleanFields();
            views.btn_customer_register.setEnabled(true);
        }
    }

    private void executeRegister() {
        if (areFieldsEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            // El usuario digita el id_card (cédula), NO el ID autoincrementable
            customer.setId(Integer.parseInt(views.txt_customer_id.getText().trim()));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "La cédula/DNI debe contener solo números.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        customer.setFullName(views.txt_customer_name.getText().trim());
        customer.setAddress(views.txt_customer_address.getText().trim());
        customer.setTelephone(views.txt_customer_telephone.getText().trim());
        customer.setEmail(views.txt_customer_email.getText().trim());

        if (customerDao.registerCustomerQuery(customer)) {
            JOptionPane.showMessageDialog(null, "Cliente guardado con éxito.");
            cleanFields();
            listAllCustomers();
        }
    }

    private void executeUpdate() {
        if (views.txt_customer_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila de la tabla para modificar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (areFieldsEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos obligatorios vacíos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            // Aquí SÍ extraemos el ID oculto de la base de datos para pasárselo al WHERE
            customer.setId(Integer.parseInt(views.txt_customer_id.getText().trim()));
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Verifique los formatos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        customer.setFullName(views.txt_customer_name.getText().trim());
        customer.setAddress(views.txt_customer_address.getText().trim());
        customer.setTelephone(views.txt_customer_telephone.getText().trim());
        customer.setEmail(views.txt_customer_email.getText().trim());

        if (customerDao.updateCustomerQuery(customer)) {
            cleanFields();
            listAllCustomers();
            views.btn_customer_register.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Cliente actualizado.");
        }
    }

    private void executeDelete() {
        int row = views.tb_customer.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona un cliente para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            // Extraemos el ID de la fila seleccionada (columna 0) para enviarlo al DELETE
            int id = Integer.parseInt(views.tb_customer.getValueAt(row, 0).toString());
            int question = JOptionPane.showConfirmDialog(null, "¿Eliminar este cliente?", "Confirmar", JOptionPane.YES_NO_OPTION);
            
            if (question == JOptionPane.YES_OPTION && customerDao.deleteCustomerQuery(id)) {
                cleanFields();
                listAllCustomers();
                views.btn_customer_register.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Cliente eliminado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error de ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean areFieldsEmpty() {
        return views.txt_customer_id.getText().trim().isEmpty()
                || views.txt_customer_name.getText().trim().isEmpty()
                || views.txt_customer_address.getText().trim().isEmpty()
                || views.txt_customer_telephone.getText().trim().isEmpty()
                || views.txt_customer_email.getText().trim().isEmpty();
    }

    public void cleanFields() {
        views.txt_customer_id.setText("");
        views.txt_customer_name.setText("");
        views.txt_customer_address.setText("");
        views.txt_customer_telephone.setText("");
        views.txt_customer_email.setText("");
    }

    public void listAllCustomers() {
        List<Customers> list = customerDao.listCustomersQuery(views.txt_customer_search.getText().trim());
        model = (DefaultTableModel) views.tb_customer.getModel();
        model.setRowCount(0);

        for (Customers cust : list) {
            Object[] row = new Object[6];
            row[0] = cust.getId();
            row[1] = cust.getFullName();
            row[2] = cust.getAddress();
            row[3] = cust.getTelephone();
            row[4] = cust.getEmail();
            model.addRow(row);
        }
        views.tb_customer.setModel(model);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.tb_customer) {
            int row = views.tb_customer.getSelectedRow();
            if (row != -1) {
                // Al hacer clic, cargamos el ID autoincrementado en el campo oculto
                views.txt_customer_id.setText(views.tb_customer.getValueAt(row, 0).toString());
                views.txt_customer_name.setText(views.tb_customer.getValueAt(row, 1).toString());
                views.txt_customer_address.setText(views.tb_customer.getValueAt(row, 2).toString());
                views.txt_customer_telephone.setText(views.tb_customer.getValueAt(row, 3).toString());
                views.txt_customer_email.setText(views.tb_customer.getValueAt(row, 4).toString());
                
                views.btn_customer_register.setEnabled(false);
            }
        } else if (e.getSource() == views.jLabelCustomers) {
            views.jTabbedPane1.setSelectedIndex(3);
            listAllCustomers();
            cleanFields();
        }
    }

    @Override public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txt_customer_search) {
            listAllCustomers();
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyPressed(KeyEvent e) {}
}
