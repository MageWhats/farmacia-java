package Controllers;

import Models.Customers;
import Models.CustomersDao;
import Models.Customers;
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

public class CustomersController implements ActionListener, MouseListener, KeyListener {

    //Encapsular Variables
    private Customers customer;
    private CustomersDao customersDao;
    private SystemView views;
    DefaultTableModel model = new DefaultTableModel();

    //Contructor
    public CustomersController(Customers customer, CustomersDao customersDao, SystemView views) {
        this.customer = customer;
        this.customersDao = customersDao;
        this.views = views;
        //Boton registrar Cliente "Escucha"
        this.views.btn_customer_register.addActionListener(this);
        //TxT buscar Cliente "Escucha"
        this.views.txt_customer_search.addKeyListener(this);
        //Tabla
        this.views.tb_customer.addMouseListener(this);
        //Boton modificar clientes
        this.views.btn_customer_modificar.addActionListener(this);
        //Boton de eliminar
        this.views.btn_customer_remove.addActionListener(this);
        //Boton cancelar
        this.views.btn_customer_cancelar.addActionListener(this);
        //Label customer
        this.views.jLabelCustomers.addMouseListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btn_customer_register) {
            if (views.txt_customer_id.getText().equals("")
                    || views.txt_customer_name.getText().equals("")
                    || views.txt_customer_direccion.getText().equals("")
                    || views.txt_customer_telefono.getText().equals("")
                    || views.txt_customer_correo.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            } else {
                customer.setId(Integer.parseInt(views.txt_customer_id.getText().trim()));
                customer.setFull_name(views.txt_customer_name.getText().trim());
                customer.setAddress(views.txt_customer_direccion.getText().trim());
                customer.setTelephone(views.txt_customer_telefono.getText().trim());
                customer.setEmail(views.txt_customer_correo.getText().trim());
                if (customersDao.registerCutomersQuery(customer)) {
                    JOptionPane.showMessageDialog(null, "Cliente registrado con exito");
                    cleanFields();
                    cleanTable();
                    listAllCustomers();
                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar este cliente");
                }
            }

        } else if (e.getSource() == views.btn_customer_modificar) {
            if (views.txt_customer_id.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila de la tabla para continuar");
            } else {
                if (views.txt_customer_name.getText().equals("")
                        || views.txt_customer_direccion.getText().equals("")
                        || views.txt_customer_telefono.getText().equals("")
                        || views.txt_customer_correo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                } else {
                    customer.setId(Integer.parseInt(views.txt_customer_id.getText().trim()));
                    customer.setFull_name(views.txt_customer_name.getText().trim());
                    customer.setTelephone(views.txt_customer_telefono.getText().trim());
                    customer.setAddress(views.txt_customer_direccion.getText().trim());
                    customer.setEmail(views.txt_customer_correo.getText().trim());

                    if (customersDao.updateCustomersQuery(customer)) {
                        cleanTable(); // Primero limpia los datos viejos de la tabla
                        listAllCustomers(); // Luego carga los datos actualizados
                        cleanFields();//Limpiar cuadro de textos formularios
                        views.btn_customer_register.setEnabled(true);
                        JOptionPane.showMessageDialog(null, "Datos del cliente modificados con exito");

                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar el cliente ");
                    }

                }

            }
        } else if (e.getSource() == views.btn_customer_remove) {
            int row = views.tb_customer.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un cliente para eliminar");
            } else {
                int id = Integer.parseInt(views.tb_customer.getValueAt(row, 0).toString());
                int question = JOptionPane.showConfirmDialog(null, "En realidad quieres eliminar el cliente");
                if (question == 0 && customersDao.deleteCustomersQuery(id) != false) {
                    cleanTable();
                    cleanFields();
                    listAllCustomers();
                    JOptionPane.showMessageDialog(null, "El usuario fue eliminado con exito");
                }

            }
        } else if (e.getSource() == views.btn_customer_cancelar) {
            cleanFields();
            views.btn_customer_register.setEnabled(true);
            views.txt_customer_id.setEditable(true);
            views.txt_customer_id.setEnabled(true);
        }

    }

    //Listar Clientes
    public void listAllCustomers() {
        List<Customers> list = customersDao.listCustomersQuery(views.txt_customer_search.getText());
        model = (DefaultTableModel) views.tb_customer.getModel();
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getFull_name();
            row[2] = list.get(i).getAddress();
            row[3] = list.get(i).getTelephone();
            row[4] = list.get(i).getEmail();
            model.addRow(row);
        }
        views.tb_customer.setModel(model);
    }

    //Limpiar campos
    public void cleanFields() {
        views.txt_customer_id.setText("");
        views.txt_customer_id.setEditable(true);
        views.txt_customer_name.setText("");
        views.txt_customer_direccion.setText("");
        views.txt_customer_telefono.setText("");
        views.txt_customer_correo.setText("");

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.tb_customer) {
            int row = views.tb_customer.rowAtPoint(e.getPoint());
            views.txt_customer_id.setText(views.tb_customer.getValueAt(row, 0).toString());
            views.txt_customer_name.setText(views.tb_customer.getValueAt(row, 1).toString());
            views.txt_customer_direccion.setText(views.tb_customer.getValueAt(row, 2).toString());
            views.txt_customer_telefono.setText(views.tb_customer.getValueAt(row, 3).toString());
            views.txt_customer_correo.setText(views.tb_customer.getValueAt(row, 4).toString());

            views.btn_customer_register.setEnabled(false);
            views.txt_customer_id.setEditable(false);
        }else if (e.getSource()==views.jLabelCustomers){
            views.jTabbedPane1.setSelectedIndex(3);
            cleanTable();
            cleanFields();
            listAllCustomers();
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txt_customer_search) {
            cleanTable();
            listAllCustomers();
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
