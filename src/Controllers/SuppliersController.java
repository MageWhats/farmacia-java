package Controllers;

import Models.DynamicCb;
import static Models.EmployeesDao.rol_user;
import Models.Suppliers;
import Models.SuppliersDao;
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

public class SuppliersController implements ActionListener, MouseListener, KeyListener {

    //Variables
    private Suppliers supplier;
    private SuppliersDao supplierDao;
    private SystemView views;
    String rol = rol_user;
    DefaultTableModel model = new DefaultTableModel();

    public SuppliersController(Suppliers supplier, SuppliersDao supplierDao, SystemView views) {
        this.supplier = supplier;
        this.supplierDao = supplierDao;
        this.views = views;
        //Registrar
        this.views.btn_suplimers_register.addActionListener(this);
        //Txt buscar cliente
        this.views.txt_suplimers_search.addKeyListener(this);
        //Tabla
        this.views.tb_suplimers.addMouseListener(this);
        //Modificar
        this.views.btn_suplimers_modificar.addActionListener(this);
        //Eliminar
        this.views.btn_suplimers_remove.addActionListener(this);
        //Cancelar
        this.views.btn_suplimers_cancel.addActionListener(this);
        //Label supplier
        this.views.jLabelSupplimers.addMouseListener(this);
     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btn_suplimers_register) {
            if (views.txt_suplimers_name.getText().equals("")
                    || views.txt_suplimers_address.getText().equals("")
                    || views.txt_suplimers_telefono.getText().equals("")
                    || views.txt_suplimers_mail.getText().equals("")
                    || views.txt_suplimers_descripcion.getText().equals("")
                    || views.cb_suplimers_ciudad.getSelectedItem().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            } else {
                supplier.setName(views.txt_suplimers_name.getText().trim());
                supplier.setAddress(views.txt_suplimers_address.getText().trim());
                supplier.setTelephone(views.txt_suplimers_telefono.getText().trim());
                supplier.setEmail(views.txt_suplimers_mail.getText().trim());
                supplier.setDescription(views.txt_suplimers_descripcion.getText().trim());
                supplier.setCity(views.cb_suplimers_ciudad.getSelectedItem().toString());

                if (supplierDao.registerSuppliersQuery(supplier)) {
                    JOptionPane.showMessageDialog(null, "Proveedor registrado con exito");
                    cleanFields();
                    cleanTable();
                    listAllSuppliers();

                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un error al registrar proveedor");
                }
            }

        } else if (e.getSource() == views.btn_suplimers_modificar) {
            if (views.txt_suplimers_id.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para continuar");
            } else {
                if (views.txt_suplimers_name.getText().equals("")
                        || views.txt_suplimers_address.getText().equals("")
                        || views.txt_suplimers_telefono.getText().equals("")
                        || views.txt_suplimers_mail.getText().equals("")
                        || views.txt_suplimers_descripcion.getText().equals("")
                        || views.cb_suplimers_ciudad.getSelectedItem() == null
                        || views.cb_suplimers_ciudad.getSelectedItem().toString().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                } else {
                    supplier.setName(views.txt_suplimers_name.getText().trim());
                    supplier.setAddress(views.txt_suplimers_address.getText().trim());
                    supplier.setTelephone(views.txt_suplimers_telefono.getText().trim());
                    supplier.setEmail(views.txt_suplimers_mail.getText().trim());
                    supplier.setDescription(views.txt_suplimers_descripcion.getText().trim());
                    supplier.setCity(views.cb_suplimers_ciudad.getSelectedItem().toString());
                    supplier.setId(Integer.parseInt(views.txt_suplimers_id.getText().trim()));
                    if (supplierDao.updateSuppliersQuery(supplier)) {
                        cleanTable();
                        listAllSuppliers();
                        cleanFields();
                        views.btn_suplimers_register.setEnabled(true);
                        JOptionPane.showMessageDialog(null, "Datos modificados exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar el proveedor");
                    }
                }
            }

        } else if (e.getSource() == views.btn_suplimers_remove) {
            int row = views.tb_suplimers.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un proveedor para eliminar");
            } else {
                int id = Integer.parseInt(views.tb_suplimers.getValueAt(row, 0).toString());
                int question = JOptionPane.showConfirmDialog(null, "En realidad quieres eliminar el proveedor");
                if (question == 0 && supplierDao.deleteSuppliersQuery(id) != false) {
                    cleanTable();
                    cleanFields();
                    views.btn_suplimers_register.setEnabled(true);
                    listAllSuppliers();
                    JOptionPane.showMessageDialog(null, "Proveedor eliminado con exito");
                }
            }
        } else if (e.getSource() == views.btn_suplimers_cancel) {
            cleanFields();
            views.btn_suplimers_register.setEnabled(true);
            views.txt_suplimers_id.setEditable(true);
        }

    }

    //Listar todos los proveedores
    public void listAllSuppliers() {
        if (rol.equals("Administrador")) {
            List<Suppliers> list = supplierDao.listSuppliersQuery(views.txt_suplimers_search.getText());
            model = (DefaultTableModel) views.tb_suplimers.getModel();
            Object[] row = new Object[7];
            for (int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getId();
                row[1] = list.get(i).getName();
                row[2] = list.get(i).getDescription();
                row[3] = list.get(i).getAddress();
                row[4] = list.get(i).getTelephone();
                row[5] = list.get(i).getEmail();
                row[6] = list.get(i).getCity();
                model.addRow(row);
            }
            views.tb_suplimers.setModel(model);
        }

    }

    //Limpiar campos
    public void cleanFields() {
        views.txt_suplimers_id.setText("");
        views.txt_suplimers_name.setText("");
        views.txt_suplimers_address.setText("");
        views.txt_suplimers_telefono.setText("");
        views.txt_suplimers_mail.setText("");
        views.txt_suplimers_descripcion.setText("");
        views.cb_suplimers_ciudad.setSelectedItem(0);

    }

    //Limpiar tabla
    public void cleanTable() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }
    


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.tb_suplimers) {
            int row = views.tb_suplimers.rowAtPoint(e.getPoint());
            views.txt_suplimers_id.setText(views.tb_suplimers.getValueAt(row, 0).toString());
            views.txt_suplimers_name.setText(views.tb_suplimers.getValueAt(row, 1).toString());
            views.txt_suplimers_descripcion.setText(views.tb_suplimers.getValueAt(row, 2).toString());
            views.txt_suplimers_address.setText(views.tb_suplimers.getValueAt(row, 3).toString());
            views.txt_suplimers_telefono.setText(views.tb_suplimers.getValueAt(row, 4).toString());
            views.txt_suplimers_mail.setText(views.tb_suplimers.getValueAt(row, 5).toString());
            views.cb_suplimers_ciudad.setSelectedItem(views.tb_suplimers.getValueAt(row, 6).toString());

            views.btn_suplimers_register.setEnabled(false);
            views.txt_suplimers_id.setEditable(false);

        } else if (e.getSource() == views.jLabelSupplimers) {
            if (rol.equals("Administrador")) {
                views.jTabbedPane1.setSelectedIndex(5);
                cleanTable();
                cleanFields();
                listAllSuppliers();
            } else {
                views.jTabbedPane1.setEnabledAt(5, false);
                views.jLabelSupplimers.setEnabled(false);
                JOptionPane.showMessageDialog(null, "No tienes privilegios de Administrador");
            }
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
        if (e.getSource() == views.txt_suplimers_search) {
            cleanTable();
            listAllSuppliers();
        }
    }

}
