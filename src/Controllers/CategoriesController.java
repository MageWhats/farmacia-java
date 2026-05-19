package Controllers;

import Models.Categories;
import Models.CategoriesDao;
import Models.DynamicCb;
import static Models.EmployeesDao.rol_user;
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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class CategoriesController implements ActionListener, KeyListener, MouseListener {

    private Categories category;
    private CategoriesDao categoryDao;
    private SystemView views;
    String rol = rol_user;
    DefaultTableModel model = new DefaultTableModel();

    public CategoriesController(Categories category, CategoriesDao categoryDao, SystemView views) {
        this.category = category;
        this.categoryDao = categoryDao;
        this.views = views;
        //Registrar
        this.views.btn_category_register.addActionListener(this);
        //Txt buscar categorias
        this.views.txt_category_search.addKeyListener(this);
        //tabla
        this.views.tb_category.addMouseListener(this);
        //Modificar
        this.views.btn_category_modificar.addActionListener(this);
        //Eliminar
        this.views.btn_category_eliminar.addActionListener(this);
        //Label Category
        this.views.jLabelCategories.addMouseListener(this);
        //Cancel
        this.views.btn_category_cancel.addActionListener(this);
        getCategoryName();
        AutoCompleteDecorator.decorate(views.cb_product_category);
    }

    //Listar categorias   
    public void listAllCategories() {
        if (rol.equals("Administrador")) {
            List<Categories> list = categoryDao.listCategoriesQuery(views.txt_category_search.getText());
            model = (DefaultTableModel) views.tb_category.getModel();
            Object[] row = new Object[2];
            for (int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getId();
                row[1] = list.get(i).getName();
                model.addRow(row);
            }
            views.tb_category.setModel(model);
        }

    }

//Limpiar tabla
    public void cleanTable() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }
    
//Limpiar campos
    public void cleanFields(){
        views.txt_category_id.setText("");
        views.txt_category_name.setText("");
    }
    
//Mostrar nombre de las categorias
    public void getCategoryName(){
        
            views.cb_product_category.removeAllItems();

        List<Categories> list= categoryDao.listCategoriesQuery((views.txt_category_search.getText()));
        for (int i = 0 ; i< list.size();i++){
            int id = list.get(i).getId();
            String name = list.get(i).getName();
            views.cb_product_category.addItem(new DynamicCb(id, name));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == views.btn_category_register) {
            if (views.txt_category_name.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "El campo nombre es obligatorio");
            } else {
                category.setName(views.txt_category_name.getText().trim());
                if (categoryDao.registerCategoryQuery(category)) {
                    cleanTable();
                    listAllCategories();
                    JOptionPane.showMessageDialog(null, "Categoria registrada con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un error al registrar categoria");
                }
            }
        }else if(e.getSource()==views.btn_category_modificar){
            if(views.txt_category_id.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Selecciona una fila para continuar");
            }else{
                if(views.txt_category_name.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                }else{
                    category.setName(views.txt_category_name.getText().trim());
                    category.setId(Integer.parseInt(views.txt_category_id.getText()));
                    if(categoryDao.updateCategoryQuery(category)){
                        cleanTable();
                        listAllCategories();
                        cleanFields();
                        views.btn_category_register.setEnabled(true);
                        JOptionPane.showMessageDialog(null, "Datos modificados exitosamente");
                    }else{
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar el proveedor");
                    }
                }
            }
        }else if (e.getSource()==views.btn_category_eliminar){
            int row = views.tb_category.getSelectedRow();
            if(row==-1){
                JOptionPane.showMessageDialog(null, "Debes seleccionar una categoria para eliminar");
            }else{
                int id =Integer.parseInt(views.tb_category.getValueAt(row, 0).toString());
                int question = JOptionPane.showConfirmDialog(null, "En realidad quieres eliminar la categoria");
                if (question==0 && categoryDao.deleteCategoryQuery(id) !=false){
                    cleanTable();
                    cleanFields();
                    views.btn_category_register.setEnabled(true);
                    listAllCategories();
                    JOptionPane.showMessageDialog(null, "Proveedor eliminado con exito");
                }
            }
            
        }else if(e.getSource()==views.btn_category_cancel){
            cleanFields();
            views.btn_category_register.setEnabled(true);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txt_category_search) {
            cleanTable();
            listAllCategories();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.tb_category) {
            int row = views.tb_category.rowAtPoint(e.getPoint());
            views.txt_category_id.setText(views.tb_category.getValueAt(row, 0).toString());
            views.txt_category_name.setText(views.tb_category.getValueAt(row, 1).toString());
            views.btn_category_register.setEnabled(false);
            
            
        }else if(e.getSource()==views.jLabelCategories){
            if(rol.equals("Administrador")){
                views.jTabbedPane1.setSelectedIndex(6);
                cleanTable();
                cleanFields();
                listAllCategories();
            }else{
                views.jTabbedPane1.setEnabledAt(6,false);
                views.jLabelCategories.setEnabled(false);
                JOptionPane.showMessageDialog(null, "No tienes permisos de Administrador");
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

}
