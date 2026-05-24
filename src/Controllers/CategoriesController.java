package Controllers;

import Models.Categories;
import Models.CategoriesDao;
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

public class CategoriesController implements ActionListener, MouseListener, KeyListener {

    private final Categories category;
    private final CategoriesDao categoryDao;
    private final SystemView views;
    private DefaultTableModel model = new DefaultTableModel();

    public CategoriesController(Categories category, CategoriesDao categoryDao, SystemView views) {
        this.category = category;
        this.categoryDao = categoryDao;
        this.views = views;

        // Escuchadores de botones de tu formulario de Categorías
        this.views.btn_category_register.addActionListener(this); 
        this.views.btn_category_update.addActionListener(this);   
        this.views.btn_category_delete.addActionListener(this);   
        this.views.btn_category_cancel.addActionListener(this);   

        
        this.views.txt_category_search.addKeyListener(this);
        this.views.tb_category.addMouseListener(this);
        this.views.jLabelCategories.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btn_category_register) {
            executeRegister();
        } else if (e.getSource() == views.btn_category_update) {
            executeUpdate();
        } else if (e.getSource() == views.btn_category_delete) {
            executeDelete();
        } else if (e.getSource() == views.btn_category_cancel) {
            cleanFields();
            views.btn_category_register.setEnabled(true);
        }
    }

    private void executeRegister() {
        if (views.txt_category_name.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre de la categoría es un campo obligatorio.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        category.setName(views.txt_category_name.getText().trim());

        if (categoryDao.registerCategoryQuery(category)) {
            JOptionPane.showMessageDialog(null, "Categoría guardada exitosamente.");
            cleanFields();
            listAllCategories();
        }
    }

    private void executeUpdate() {
        if (views.txt_category_id.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione una categoría de la tabla para continuar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (views.txt_category_name.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre de la categoría no puede quedar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Extrae el ID guardado en tu casilla oculta
            category.setId(Integer.parseInt(views.txt_category_id.getText().trim()));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error al procesar la identidad de la categoría.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        category.setName(views.txt_category_name.getText().trim());

        if (categoryDao.updateCategoryQuery(category)) {
            cleanFields();
            listAllCategories();
            views.btn_category_register.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Categoría modificada de manera correcta.");
        }
    }

    private void executeDelete() {
        int row = views.tb_category.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla para poder eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(views.tb_category.getValueAt(row, 0).toString());
            int question = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar esta categoría?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            
            if (question == JOptionPane.YES_OPTION && categoryDao.deleteCategoryQuery(id)) {
                cleanFields();
                listAllCategories();
                views.btn_category_register.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Categoría eliminada con éxito.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error de conversión del ID.");
        }
    }

    public void cleanFields() {
        views.txt_category_id.setText("");
        views.txt_category_name.setText("");
    }

    public void listAllCategories() {
        List<Categories> list = categoryDao.listCategoriesQuery(views.txt_category_search.getText().trim());
        model = (DefaultTableModel) views.tb_category.getModel();
        model.setRowCount(0); // Limpieza rápida de filas

        for (Categories cat : list) {
            Object[] row = new Object[2];
            row[0] = cat.getId();
            row[1] = cat.getName();
            model.addRow(row);
        }
        views.tb_category.setModel(model);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.tb_category) {
            int row = views.tb_category.getSelectedRow();
            if (row != -1) {
                // Al dar clic, mapeamos el id al campo oculto de la vista para poder modificarlo/eliminarlo
                views.txt_category_id.setText(views.tb_category.getValueAt(row, 0).toString());
                views.txt_category_name.setText(views.tb_category.getValueAt(row, 1).toString());
                
                views.btn_category_register.setEnabled(false);
            }
        } else if (e.getSource() == views.jLabelCategories) {
            views.jTabbedPane1.setSelectedIndex(6); 
            listAllCategories();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txt_category_search) {
            listAllCategories();
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyPressed(KeyEvent e) {}
}
