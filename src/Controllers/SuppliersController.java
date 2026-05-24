package Controllers;

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

    private final Suppliers supplier;
    private final SuppliersDao supplierDao;
    private final SystemView views;
    private DefaultTableModel model = new DefaultTableModel();

    public SuppliersController(Suppliers supplier, SuppliersDao supplierDao, SystemView views) {
        this.supplier = supplier;
        this.supplierDao = supplierDao;
        this.views = views;

        // Escuchadores de los botones de tu imagen
        this.views.btn_supplier_register.addActionListener(this); 
        this.views.btn_supplier_update.addActionListener(this);   
        this.views.btn_supplier_delete.addActionListener(this);   
        this.views.btn_supplier_cancel.addActionListener(this);   

        this.views.txt_supplier_search.addKeyListener(this);
        this.views.tb_supplier.addMouseListener(this);
        this.views.jLabelSuppliers.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btn_supplier_register) {
            executeRegister();
        } else if (e.getSource() == views.btn_supplier_update) {
            executeUpdate();
        } else if (e.getSource() == views.btn_supplier_delete) {
            executeDelete();
        } else if (e.getSource() == views.btn_supplier_cancel) {
            cleanFields();
            views.btn_supplier_register.setEnabled(true);
        }
    }

    private void executeRegister() {
        if (views.txt_supplier_name.getText().trim().isEmpty() || views.txt_supplier_address.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre y la dirección son campos requeridos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        supplier.setName(views.txt_supplier_name.getText().trim());
        supplier.setDescription(views.txt_supplier_description.getText().trim());
        supplier.setAddress(views.txt_supplier_address.getText().trim());
        supplier.setTelephone(views.txt_supplier_telephone.getText().trim());
        supplier.setEmail(views.txt_supplier_email.getText().trim());
        supplier.setCity(views.cb_supplier_city.getSelectedItem().toString().trim()); // Extrae la ciudad del ComboBox

        if (supplierDao.registerSupplierQuery(supplier)) {
            JOptionPane.showMessageDialog(null, "Proveedor registrado exitosamente.");
            cleanFields();
            listAllSuppliers();
        }
    }

    private void executeUpdate() {
        if (views.txt_supplier_id.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecciona un proveedor de la tabla para modificarlo.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            supplier.setId(Integer.parseInt(views.txt_supplier_id.getText().trim()));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error al extraer el ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        supplier.setName(views.txt_supplier_name.getText().trim());
        supplier.setDescription(views.txt_supplier_description.getText().trim());
        supplier.setAddress(views.txt_supplier_address.getText().trim());
        supplier.setTelephone(views.txt_supplier_telephone.getText().trim());
        supplier.setEmail(views.txt_supplier_email.getText().trim());
        supplier.setCity(views.cb_supplier_city.getSelectedItem().toString().trim());

        if (supplierDao.updateSupplierQuery(supplier)) {
            cleanFields();
            listAllSuppliers();
            views.btn_supplier_register.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Proveedor actualizado de manera correcta.");
        }
    }

    private void executeDelete() {
        int row = views.tb_supplier.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int id = Integer.parseInt(views.tb_supplier.getValueAt(row, 0).toString());
            int question = JOptionPane.showConfirmDialog(null, "¿Desea eliminar este proveedor?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (question == JOptionPane.YES_OPTION && supplierDao.deleteSupplierQuery(id)) {
                cleanFields();
                listAllSuppliers();
                views.btn_supplier_register.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Proveedor eliminado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error de conversión.");
        }
    }

    public void cleanFields() {
        views.txt_supplier_id.setText("");
        views.txt_supplier_name.setText("");
        views.txt_supplier_description.setText("");
        views.txt_supplier_address.setText("");
        views.txt_supplier_telephone.setText("");
        views.txt_supplier_email.setText("");
        if (views.cb_supplier_city.getItemCount() > 0) {
            views.cb_supplier_city.setSelectedIndex(0);
        }
    }

    public void listAllSuppliers() {
        List<Suppliers> list = supplierDao.listSuppliersQuery(views.txt_supplier_search.getText().trim());
        model = (DefaultTableModel) views.tb_supplier.getModel();
        model.setRowCount(0);

        for (Suppliers sup : list) {
            Object[] row = new Object[7];
            row[0] = sup.getId();
            row[1] = sup.getName();
            row[2] = sup.getDescription();
            row[3] = sup.getAddress();
            row[4] = sup.getTelephone();
            row[5] = sup.getEmail();
            row[6] = sup.getCity();
            model.addRow(row);
        }
        views.tb_supplier.setModel(model);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.tb_supplier) {
            int row = views.tb_supplier.getSelectedRow();
            if (row != -1) {
                // Al dar clic, llenamos los campos incluyendo la casilla visible 'Id' que tienes en pantalla
                views.txt_supplier_id.setText(views.tb_supplier.getValueAt(row, 0).toString());
                views.txt_supplier_name.setText(views.tb_supplier.getValueAt(row, 1).toString());
                views.txt_supplier_description.setText(views.tb_supplier.getValueAt(row, 2).toString());
                views.txt_supplier_address.setText(views.tb_supplier.getValueAt(row, 3).toString());
                views.txt_supplier_telephone.setText(views.tb_supplier.getValueAt(row, 4).toString());
                views.txt_supplier_email.setText(views.tb_supplier.getValueAt(row, 5).toString());
                views.cb_supplier_city.setSelectedItem(views.tb_supplier.getValueAt(row, 6).toString());
                
                views.btn_supplier_register.setEnabled(false);
            }
        } else if (e.getSource() == views.jLabelSuppliers) {
            views.jTabbedPane1.setSelectedIndex(5); // Índice numérico de proveedores en tu vista
            listAllSuppliers();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txt_supplier_search) {
            listAllSuppliers();
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyPressed(KeyEvent e) {}
}
 