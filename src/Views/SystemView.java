package Views;

import Models.EmployeesDao;





public final class SystemView extends javax.swing.JFrame {

  

    public SystemView() {
        initComponents(); 
        jTabbedPane1.setUI(null); 
        setSize(1208, 680);
        setResizable(false);
        titleInterface();
        setLocationRelativeTo(null);

      

        this.repaint();

    }

 public void titleInterface() {
    // Jalamos los datos estáticos llamando directamente a la clase dueña de la información
    String rol = EmployeesDao.rol_user;
    String nombre =EmployeesDao.fullName_user;
    
    // Configuramos el título y las etiquetas de tu SystemView
    setTitle("Panel - " + rol);
    lb_name_colaborador.setText(nombre);
    lb_rol_colaborador.setText(rol);
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JPanel();
        jPanelProducts = new javax.swing.JPanel();
        jLabelProducts = new javax.swing.JLabel();
        jPanelPurchases = new javax.swing.JPanel();
        jLabelPurchases = new javax.swing.JLabel();
        jPanelCustomers = new javax.swing.JPanel();
        jLabelCustomers = new javax.swing.JLabel();
        jPanelCollaborator = new javax.swing.JPanel();
        jLabelColaboradores = new javax.swing.JLabel();
        jPanelSupplimers = new javax.swing.JPanel();
        jLabelSuppliers = new javax.swing.JLabel();
        jPanelCategories = new javax.swing.JPanel();
        jLabelCategories = new javax.swing.JLabel();
        jPanelReports = new javax.swing.JPanel();
        jLabelReports = new javax.swing.JLabel();
        jPanelSettings = new javax.swing.JPanel();
        jLabelSettings = new javax.swing.JLabel();
        jPanelSales = new javax.swing.JPanel();
        jLabelSales = new javax.swing.JLabel();
        logo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cabecera = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_photo = new javax.swing.JButton();
        btn_loginOut = new javax.swing.JButton();
        lb_name_colaborador = new javax.swing.JLabel();
        lb_rol_colaborador = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_product_code = new javax.swing.JTextField();
        txt_product_name = new javax.swing.JTextField();
        txt_product_price_sale = new javax.swing.JTextField();
        txt_product_description = new javax.swing.JTextField();
        txt_product_id = new javax.swing.JTextField();
        btn_product_register = new javax.swing.JButton();
        btn_product_update = new javax.swing.JButton();
        btn_product_delete = new javax.swing.JButton();
        btn_product_cancel = new javax.swing.JButton();
        cb_product_category = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_product = new javax.swing.JTable();
        txt_product_search = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_purchase_productCode = new javax.swing.JTextField();
        txt_purchase_productName = new javax.swing.JTextField();
        txt_purchase_quantity = new javax.swing.JTextField();
        cb_purchase_supplier = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_purchase_price = new javax.swing.JTextField();
        txt_purchase_subtotal = new javax.swing.JTextField();
        txt_purchase_id_product = new javax.swing.JTextField();
        txt_purchase_total_to_pay = new javax.swing.JTextField();
        btn_purchase_add = new javax.swing.JButton();
        btn_purchase_buy = new javax.swing.JButton();
        btn_purchase_delete = new javax.swing.JButton();
        btn_purchase_new = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_purchase = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        txt_sale_productCode = new javax.swing.JTextField();
        txt_sale_nameProduct = new javax.swing.JTextField();
        txt_sale_quantity = new javax.swing.JTextField();
        txt_sale_idCustomer = new javax.swing.JTextField();
        txt_sale_total_to_pay = new javax.swing.JTextField();
        txt_sale_product_id = new javax.swing.JTextField();
        txt_sale_price_product = new javax.swing.JTextField();
        txt_sale_subtotal = new javax.swing.JTextField();
        txt_sale_stock = new javax.swing.JTextField();
        txt_sale_nameCustomer = new javax.swing.JTextField();
        btn_sale_add = new javax.swing.JButton();
        btn_sale_generate = new javax.swing.JButton();
        btn_sale_delete = new javax.swing.JButton();
        btn_sale_new = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tb_sale = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_customer_id = new javax.swing.JTextField();
        txt_customer_name = new javax.swing.JTextField();
        txt_customer_address = new javax.swing.JTextField();
        txt_customer_telephone = new javax.swing.JTextField();
        txt_customer_email = new javax.swing.JTextField();
        btn_customer_register = new javax.swing.JButton();
        btn_customer_update = new javax.swing.JButton();
        btn_customer_delete = new javax.swing.JButton();
        btn_customer_cancel = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        txt_customer_search = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_customer = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txt_colaborador_id_card = new javax.swing.JTextField();
        txt_colaborador_name = new javax.swing.JTextField();
        txt_colaborador_username = new javax.swing.JTextField();
        cb_colaborador_rol = new javax.swing.JComboBox<>();
        txt_colaborador_address = new javax.swing.JTextField();
        txt_colaborador_telephone = new javax.swing.JTextField();
        txt_colaborador_email = new javax.swing.JTextField();
        txt_colaborador_password = new javax.swing.JPasswordField();
        btn_colaborador_register = new javax.swing.JButton();
        btn_colaborador_update = new javax.swing.JButton();
        btn_colaborador_delete = new javax.swing.JButton();
        btn_colaborador_cancel = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        txt_colaborador_search = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_colaborador = new javax.swing.JTable();
        jLabel64 = new javax.swing.JLabel();
        txt_colaborador_id = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txt_supplier_name = new javax.swing.JTextField();
        txt_supplier_address = new javax.swing.JTextField();
        txt_supplier_telephone = new javax.swing.JTextField();
        txt_supplier_email = new javax.swing.JTextField();
        txt_supplier_description = new javax.swing.JTextField();
        cb_supplier_city = new javax.swing.JComboBox<>();
        txt_supplier_id = new javax.swing.JTextField();
        btn_supplier_register = new javax.swing.JButton();
        btn_supplier_update = new javax.swing.JButton();
        btn_supplier_delete = new javax.swing.JButton();
        btn_supplier_cancel = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        txt_supplier_search = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_supplier = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txt_category_id = new javax.swing.JTextField();
        txt_category_name = new javax.swing.JTextField();
        btn_category_register = new javax.swing.JButton();
        btn_category_update = new javax.swing.JButton();
        btn_category_delete = new javax.swing.JButton();
        btn_category_cancel = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        txt_category_search = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tb_category = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tb_reports_purchases = new javax.swing.JTable();
        jLabel62 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tb_reports_sales = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        txt_profile_id_card = new javax.swing.JTextField();
        txt_profile_name = new javax.swing.JTextField();
        txt_profile_address = new javax.swing.JTextField();
        txt_profile_telephone = new javax.swing.JTextField();
        txt_profile_email = new javax.swing.JTextField();
        txt_profile_new_password = new javax.swing.JPasswordField();
        txt_profile_confirm_password = new javax.swing.JPasswordField();
        btn_profile_update = new javax.swing.JButton();
        jLabel65 = new javax.swing.JLabel();
        txt_profile_id = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu.setBackground(new java.awt.Color(18, 45, 61));
        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelProducts.setBackground(new java.awt.Color(18, 45, 61));

        jLabelProducts.setBackground(new java.awt.Color(255, 255, 255));
        jLabelProducts.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelProducts.setForeground(new java.awt.Color(255, 255, 255));
        jLabelProducts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/producto.png"))); // NOI18N
        jLabelProducts.setText("   Productos");

        javax.swing.GroupLayout jPanelProductsLayout = new javax.swing.GroupLayout(jPanelProducts);
        jPanelProducts.setLayout(jPanelProductsLayout);
        jPanelProductsLayout.setHorizontalGroup(
            jPanelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductsLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabelProducts, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelProductsLayout.setVerticalGroup(
            jPanelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductsLayout.createSequentialGroup()
                .addComponent(jLabelProducts)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        menu.add(jPanelProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 35));

        jPanelPurchases.setBackground(new java.awt.Color(18, 45, 61));

        jLabelPurchases.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPurchases.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelPurchases.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPurchases.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/compra.png"))); // NOI18N
        jLabelPurchases.setText("   Compras");

        javax.swing.GroupLayout jPanelPurchasesLayout = new javax.swing.GroupLayout(jPanelPurchases);
        jPanelPurchases.setLayout(jPanelPurchasesLayout);
        jPanelPurchasesLayout.setHorizontalGroup(
            jPanelPurchasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPurchasesLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabelPurchases, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelPurchasesLayout.setVerticalGroup(
            jPanelPurchasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPurchasesLayout.createSequentialGroup()
                .addComponent(jLabelPurchases)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        menu.add(jPanelPurchases, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 200, 35));

        jPanelCustomers.setBackground(new java.awt.Color(18, 45, 61));

        jLabelCustomers.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCustomers.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelCustomers.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCustomers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cliente.png"))); // NOI18N
        jLabelCustomers.setText("   Clientes");

        javax.swing.GroupLayout jPanelCustomersLayout = new javax.swing.GroupLayout(jPanelCustomers);
        jPanelCustomers.setLayout(jPanelCustomersLayout);
        jPanelCustomersLayout.setHorizontalGroup(
            jPanelCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCustomersLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabelCustomers, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelCustomersLayout.setVerticalGroup(
            jPanelCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCustomersLayout.createSequentialGroup()
                .addComponent(jLabelCustomers)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        menu.add(jPanelCustomers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 200, 35));

        jPanelCollaborator.setBackground(new java.awt.Color(18, 45, 61));

        jLabelColaboradores.setBackground(new java.awt.Color(255, 255, 255));
        jLabelColaboradores.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelColaboradores.setForeground(new java.awt.Color(255, 255, 255));
        jLabelColaboradores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/colaborador.png"))); // NOI18N
        jLabelColaboradores.setText("   Colaboradores");

        javax.swing.GroupLayout jPanelCollaboratorLayout = new javax.swing.GroupLayout(jPanelCollaborator);
        jPanelCollaborator.setLayout(jPanelCollaboratorLayout);
        jPanelCollaboratorLayout.setHorizontalGroup(
            jPanelCollaboratorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCollaboratorLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabelColaboradores, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelCollaboratorLayout.setVerticalGroup(
            jPanelCollaboratorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCollaboratorLayout.createSequentialGroup()
                .addComponent(jLabelColaboradores)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        menu.add(jPanelCollaborator, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 200, 35));

        jPanelSupplimers.setBackground(new java.awt.Color(18, 45, 61));

        jLabelSuppliers.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSuppliers.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelSuppliers.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSuppliers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/proveedor.png"))); // NOI18N
        jLabelSuppliers.setText("   Proveedores");

        javax.swing.GroupLayout jPanelSupplimersLayout = new javax.swing.GroupLayout(jPanelSupplimers);
        jPanelSupplimers.setLayout(jPanelSupplimersLayout);
        jPanelSupplimersLayout.setHorizontalGroup(
            jPanelSupplimersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSupplimersLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabelSuppliers, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelSupplimersLayout.setVerticalGroup(
            jPanelSupplimersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSupplimersLayout.createSequentialGroup()
                .addComponent(jLabelSuppliers)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        menu.add(jPanelSupplimers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 200, 35));

        jPanelCategories.setBackground(new java.awt.Color(18, 45, 61));

        jLabelCategories.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCategories.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelCategories.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCategories.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/category.png"))); // NOI18N
        jLabelCategories.setText("   Categorias");

        javax.swing.GroupLayout jPanelCategoriesLayout = new javax.swing.GroupLayout(jPanelCategories);
        jPanelCategories.setLayout(jPanelCategoriesLayout);
        jPanelCategoriesLayout.setHorizontalGroup(
            jPanelCategoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategoriesLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabelCategories, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelCategoriesLayout.setVerticalGroup(
            jPanelCategoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategoriesLayout.createSequentialGroup()
                .addComponent(jLabelCategories)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        menu.add(jPanelCategories, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 200, 35));

        jPanelReports.setBackground(new java.awt.Color(18, 45, 61));

        jLabelReports.setBackground(new java.awt.Color(255, 255, 255));
        jLabelReports.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelReports.setForeground(new java.awt.Color(255, 255, 255));
        jLabelReports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reporte.png"))); // NOI18N
        jLabelReports.setText("   Reportes");

        javax.swing.GroupLayout jPanelReportsLayout = new javax.swing.GroupLayout(jPanelReports);
        jPanelReports.setLayout(jPanelReportsLayout);
        jPanelReportsLayout.setHorizontalGroup(
            jPanelReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReportsLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabelReports, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelReportsLayout.setVerticalGroup(
            jPanelReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReportsLayout.createSequentialGroup()
                .addComponent(jLabelReports)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        menu.add(jPanelReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 200, 35));

        jPanelSettings.setBackground(new java.awt.Color(18, 45, 61));

        jLabelSettings.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSettings.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelSettings.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/configuracion.png"))); // NOI18N
        jLabelSettings.setText("   Configuración");

        javax.swing.GroupLayout jPanelSettingsLayout = new javax.swing.GroupLayout(jPanelSettings);
        jPanelSettings.setLayout(jPanelSettingsLayout);
        jPanelSettingsLayout.setHorizontalGroup(
            jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSettingsLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabelSettings, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelSettingsLayout.setVerticalGroup(
            jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSettingsLayout.createSequentialGroup()
                .addComponent(jLabelSettings)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        menu.add(jPanelSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 200, 35));

        jPanelSales.setBackground(new java.awt.Color(18, 45, 61));

        jLabelSales.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelSales.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ventas.png"))); // NOI18N
        jLabelSales.setText("    Ventas");

        javax.swing.GroupLayout jPanelSalesLayout = new javax.swing.GroupLayout(jPanelSales);
        jPanelSales.setLayout(jPanelSalesLayout);
        jPanelSalesLayout.setHorizontalGroup(
            jPanelSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSalesLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabelSales, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelSalesLayout.setVerticalGroup(
            jPanelSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelSales, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        menu.add(jPanelSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 200, 35));

        getContentPane().add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 200, 580));

        logo.setBackground(new java.awt.Color(255, 255, 255));
        logo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo_1 (1).png"))); // NOI18N
        logo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 100));

        getContentPane().add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 100));

        cabecera.setBackground(new java.awt.Color(18, 45, 61));
        cabecera.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Farmacia de la Muerte");
        cabecera.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 340, 100));

        btn_photo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/user.png"))); // NOI18N
        cabecera.add(btn_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, 80, 65));

        btn_loginOut.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_loginOut.setText("Salir");
        cabecera.add(btn_loginOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 35, -1, 30));

        lb_name_colaborador.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_name_colaborador.setForeground(new java.awt.Color(255, 255, 255));
        cabecera.add(lb_name_colaborador, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 160, 30));

        lb_rol_colaborador.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_rol_colaborador.setForeground(new java.awt.Color(255, 255, 255));
        cabecera.add(lb_rol_colaborador, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, 160, 30));

        getContentPane().add(cabecera, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1010, 100));

        jPanel1.setBackground(new java.awt.Color(152, 202, 63));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Codigo:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Nombre:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Precio de Venta:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Descripción:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Categoria:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Id:");

        txt_product_id.setEditable(false);
        txt_product_id.setToolTipText("");
        txt_product_id.setEnabled(false);

        btn_product_register.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_product_register.setText("Registrar");

        btn_product_update.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_product_update.setText("Modificar");

        btn_product_delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_product_delete.setText("Eliminar");

        btn_product_cancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_product_cancel.setText("Cancelar");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_product_price_sale, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_product_name, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_product_code, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_product_description, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_product_id, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_product_category, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_product_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_product_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_product_update, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_product_register, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(txt_product_code, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_product_description, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_product_register, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(txt_product_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_product_update, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_product_category, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(txt_product_price_sale, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_product_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_product_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btn_product_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 20, 920, 270));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Buscar:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 70, 30));

        tb_product.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Codigo", "Nombre", "Descripción", "Precio de venta", "Cantidad", "Categoria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_product);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 930, 150));

        txt_product_search.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel1.add(txt_product_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 160, 30));

        jTabbedPane1.addTab("Productos", jPanel1);

        jPanel2.setBackground(new java.awt.Color(152, 202, 63));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nueva Compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Cod Producto:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Nombre Producto:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Cantidad:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Proveedor:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Precio de Compra:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Sub Total:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Id:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Total a Pagar:");

        txt_purchase_subtotal.setEditable(false);

        txt_purchase_id_product.setEditable(false);
        txt_purchase_id_product.setEnabled(false);

        txt_purchase_total_to_pay.setEditable(false);

        btn_purchase_add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_purchase_add.setText("Agregar");

        btn_purchase_buy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_purchase_buy.setText("Comprar");

        btn_purchase_delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_purchase_delete.setText("Eliminar");

        btn_purchase_new.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_purchase_new.setText("Nuevo");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(cb_purchase_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_purchase_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_purchase_productName, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_purchase_productCode, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(55, 55, 55)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_purchase_total_to_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_purchase_id_product, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_purchase_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(txt_purchase_price, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(55, 55, 55)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_purchase_add, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_purchase_buy, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_purchase_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_purchase_new, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(135, 135, 135))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_purchase_productCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txt_purchase_price, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_purchase_add, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_purchase_productName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txt_purchase_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_purchase_buy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_purchase_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txt_purchase_id_product, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_purchase_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(cb_purchase_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)
                        .addComponent(txt_purchase_total_to_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_purchase_new, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 20, 920, 310));

        tb_purchase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre del Producto", "Cantidad", "Precio", "SubTotal", "Proveedor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tb_purchase);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 930, 140));

        jTabbedPane1.addTab("Compras", jPanel2);

        jPanel16.setBackground(new java.awt.Color(152, 202, 63));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nueva Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setText("Cod Producto:");

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setText("Nombre Producto:");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setText("Cantidad:");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel55.setText("Identificación Cliente:");

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setText("Total a Pagar:");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setText("Id:");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setText("Precio:");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setText("Sub Total:");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setText("Stock:");

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setText("Nombre del cliente:");

        txt_sale_productCode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_sale_nameProduct.setEditable(false);
        txt_sale_nameProduct.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_sale_quantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_sale_idCustomer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_sale_total_to_pay.setEditable(false);
        txt_sale_total_to_pay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_sale_product_id.setEditable(false);
        txt_sale_product_id.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_sale_price_product.setEditable(false);
        txt_sale_price_product.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_sale_subtotal.setEditable(false);
        txt_sale_subtotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_sale_stock.setEditable(false);
        txt_sale_stock.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_sale_nameCustomer.setEditable(false);
        txt_sale_nameCustomer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_sale_add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_sale_add.setText("Agregar");

        btn_sale_generate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_sale_generate.setText("Vender");

        btn_sale_delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_sale_delete.setText("Eliminar");

        btn_sale_new.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_sale_new.setText("Nuevo");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(txt_sale_total_to_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(txt_sale_idCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(txt_sale_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(txt_sale_nameProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(txt_sale_productCode, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(55, 55, 55)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_sale_nameCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sale_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sale_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sale_price_product, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sale_product_id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_sale_add, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sale_generate, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sale_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sale_new, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_sale_product_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_sale_add, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sale_productCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sale_nameProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sale_price_product, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_sale_generate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sale_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sale_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_sale_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sale_idCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sale_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_sale_new, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sale_total_to_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sale_nameCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 20, 920, 315));

        tb_sale.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id producto", "Nombre", "Cantidad", "Precio de Venta", "Sub Total", "Nombre del Cliente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tb_sale);

        jPanel16.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 930, 140));

        jTabbedPane1.addTab("Ventas", jPanel16);

        jPanel3.setBackground(new java.awt.Color(152, 202, 63));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Identificación");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Nombre Completo");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Dirección");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Telefono");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Correo");

        txt_customer_id.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txt_customer_name.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txt_customer_address.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txt_customer_telephone.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txt_customer_email.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        btn_customer_register.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_customer_register.setText("Registrar");

        btn_customer_update.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_customer_update.setText("Modificar");

        btn_customer_delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_customer_delete.setText("Eliminar");

        btn_customer_cancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_customer_cancel.setText("Cancelar");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_customer_name, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_customer_id, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_customer_address, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(txt_customer_telephone, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(txt_customer_email, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(80, 80, 80)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_customer_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_customer_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_customer_update, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_customer_register, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_customer_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_customer_telephone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_customer_register, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_customer_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_customer_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_customer_update, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_customer_address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_customer_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btn_customer_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 20, 920, 282));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Buscar:");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, -1, 30));

        txt_customer_search.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel3.add(txt_customer_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 160, 30));

        tb_customer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identificacion", "Nombre", "Direccion", "Telefeno", "Correo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tb_customer);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 930, 150));

        jTabbedPane1.addTab("Clientes", jPanel3);

        jPanel4.setBackground(new java.awt.Color(152, 202, 63));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Colaboradores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Identificación:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Nombre Completo:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Nombre de Usuario:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Rol:");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Direccion:");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Telefono:");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("Correo:");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setText("Contraseña:");

        txt_colaborador_id_card.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txt_colaborador_name.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txt_colaborador_username.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        cb_colaborador_rol.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cb_colaborador_rol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Auxiliar" }));

        txt_colaborador_address.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txt_colaborador_telephone.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txt_colaborador_email.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txt_colaborador_password.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        btn_colaborador_register.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_colaborador_register.setText("Registrar");

        btn_colaborador_update.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_colaborador_update.setText("Modificar");

        btn_colaborador_delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_colaborador_delete.setText("Eliminar");

        btn_colaborador_cancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_colaborador_cancel.setText("Cancelar");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(txt_colaborador_name, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(txt_colaborador_id_card, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_colaborador_username, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_colaborador_rol, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_colaborador_email, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_colaborador_address, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_colaborador_telephone, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_colaborador_password, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_colaborador_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_colaborador_register, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btn_colaborador_update, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_colaborador_delete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel28)
                    .addComponent(txt_colaborador_id_card, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_colaborador_address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_colaborador_register, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(txt_colaborador_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_colaborador_telephone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_colaborador_update, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel30)
                    .addComponent(txt_colaborador_username, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_colaborador_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_colaborador_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel31)
                    .addComponent(cb_colaborador_rol, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_colaborador_password, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_colaborador_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        jPanel4.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 20, 920, 280));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("Buscar:");
        jPanel4.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 80, 30));

        txt_colaborador_search.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel4.add(txt_colaborador_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 160, 30));

        tb_colaborador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Identificación", "Nombre", "Usuario", "Dirección", "Telefono", "Correo", "Rol"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tb_colaborador);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 930, 150));

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel64.setText("Id : ");
        jPanel4.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 310, 30, 30));

        txt_colaborador_id.setEditable(false);
        txt_colaborador_id.setEnabled(false);
        jPanel4.add(txt_colaborador_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 310, 50, 30));

        jTabbedPane1.addTab("Colaboradores", jPanel4);

        jPanel5.setBackground(new java.awt.Color(152, 202, 63));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Proveedores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText("Nombre:");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText("Direccion:");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setText("Telefono:");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setText("Correo:");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setText("Descripción:");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setText("Ciudad:");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setText("Id:");

        txt_supplier_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_supplier_address.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_supplier_telephone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_supplier_email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_supplier_description.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cb_supplier_city.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_supplier_city.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Santa Marta", "Medellin", "Cartagena", "Barranquilla" }));

        txt_supplier_id.setEditable(false);
        txt_supplier_id.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_supplier_id.setEnabled(false);

        btn_supplier_register.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_supplier_register.setText("Registrar");

        btn_supplier_update.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_supplier_update.setText("Modificar");

        btn_supplier_delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_supplier_delete.setText("Eliminar");

        btn_supplier_cancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_supplier_cancel.setText("Cancelar");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(txt_supplier_email, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15)
                        .addComponent(txt_supplier_telephone, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15)
                        .addComponent(txt_supplier_address, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15)
                        .addComponent(txt_supplier_name, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(15, 15, 15)
                        .addComponent(txt_supplier_description, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_supplier_id, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_supplier_city, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(70, 70, 70)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_supplier_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_supplier_register, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_supplier_update, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_supplier_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel37)
                    .addComponent(txt_supplier_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_supplier_description, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_supplier_register, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel38)
                    .addComponent(txt_supplier_address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_supplier_city, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_supplier_update, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel39)
                    .addComponent(txt_supplier_telephone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_supplier_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_supplier_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txt_supplier_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_supplier_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        jPanel5.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 20, 920, 270));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("Buscar:");
        jPanel5.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 60, 30));
        jPanel5.add(txt_supplier_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 160, 30));

        tb_supplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Descripción", "Dirección", "Telefono", "Correo", "Ciudad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tb_supplier);

        jPanel5.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 930, 150));

        jTabbedPane1.addTab("Proveedores", jPanel5);

        jPanel6.setBackground(new java.awt.Color(152, 202, 63));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Categorias", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("ID:");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setText("Nombre:");

        txt_category_id.setEditable(false);
        txt_category_id.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_category_id.setEnabled(false);

        txt_category_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_category_register.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_category_register.setText("Registrar");

        btn_category_update.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_category_update.setText("Modificar");

        btn_category_delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_category_delete.setText("Eliminar");

        btn_category_cancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_category_cancel.setText("Cancelar");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_category_name, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_category_id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_category_register, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_category_update, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_category_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_category_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(txt_category_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_category_register, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(txt_category_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_category_update, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btn_category_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_category_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPanel6.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 20, 520, 270));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setText("Buscar:");
        jPanel6.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 70, 30));

        txt_category_search.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel6.add(txt_category_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 290, 30));

        tb_category.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tb_category);

        jPanel6.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 370, 230));

        jTabbedPane1.addTab("Categorias", jPanel6);

        jPanel7.setBackground(new java.awt.Color(152, 202, 63));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Compras Realizadas");
        jPanel7.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, -1, -1));

        tb_reports_purchases.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Factura", "Proveedor", "Total de Compra", "Fecha de Compra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tb_reports_purchases);

        jPanel7.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 930, 170));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("Ventas Realizadas");
        jPanel7.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));

        tb_reports_sales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Factura Venta", "Cliente", "Empleado", "Total", "Fecha de Venta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tb_reports_sales);

        jPanel7.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 930, 170));

        jTabbedPane1.addTab("Reportes", jPanel7);

        jPanel8.setBackground(new java.awt.Color(152, 202, 63));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Editar Perfil", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setText("Identificación");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setText("Nombre Completo");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setText("Dirección");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText("Telefono");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setText("Correo");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel50.setText("Nueva Contraseña");

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setText("Confirmar Contraseña");

        txt_profile_id_card.setEditable(false);

        txt_profile_name.setEditable(false);

        txt_profile_address.setEditable(false);

        txt_profile_telephone.setEditable(false);

        txt_profile_email.setEditable(false);

        txt_profile_new_password.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txt_profile_confirm_password.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btn_profile_update.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_profile_update.setText("Modificar");

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel65.setText("Id ");

        txt_profile_id.setEditable(false);
        txt_profile_id.setEnabled(false);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_profile_email, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_profile_telephone, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_profile_name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_profile_address, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_profile_id_card, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(60, 60, 60)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50)
                            .addComponent(jLabel51))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_profile_confirm_password, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(txt_profile_new_password, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(btn_profile_update, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_profile_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jLabel50)
                    .addComponent(txt_profile_id_card, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_profile_new_password, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_profile_update, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel51)
                    .addComponent(txt_profile_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_profile_confirm_password, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_profile_address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_profile_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(txt_profile_telephone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(txt_profile_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        jPanel8.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 20, 920, 390));

        jTabbedPane1.addTab("Perfil", jPanel8);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 1010, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_category_cancel;
    public javax.swing.JButton btn_category_delete;
    public javax.swing.JButton btn_category_register;
    public javax.swing.JButton btn_category_update;
    public javax.swing.JButton btn_colaborador_cancel;
    public javax.swing.JButton btn_colaborador_delete;
    public javax.swing.JButton btn_colaborador_register;
    public javax.swing.JButton btn_colaborador_update;
    public javax.swing.JButton btn_customer_cancel;
    public javax.swing.JButton btn_customer_delete;
    public javax.swing.JButton btn_customer_register;
    public javax.swing.JButton btn_customer_update;
    public javax.swing.JButton btn_loginOut;
    private javax.swing.JButton btn_photo;
    public javax.swing.JButton btn_product_cancel;
    public javax.swing.JButton btn_product_delete;
    public javax.swing.JButton btn_product_register;
    public javax.swing.JButton btn_product_update;
    public javax.swing.JButton btn_profile_update;
    public javax.swing.JButton btn_purchase_add;
    public javax.swing.JButton btn_purchase_buy;
    public javax.swing.JButton btn_purchase_delete;
    public javax.swing.JButton btn_purchase_new;
    public javax.swing.JButton btn_sale_add;
    public javax.swing.JButton btn_sale_delete;
    public javax.swing.JButton btn_sale_generate;
    public javax.swing.JButton btn_sale_new;
    public javax.swing.JButton btn_supplier_cancel;
    public javax.swing.JButton btn_supplier_delete;
    public javax.swing.JButton btn_supplier_register;
    public javax.swing.JButton btn_supplier_update;
    private javax.swing.JPanel cabecera;
    public javax.swing.JComboBox<String> cb_colaborador_rol;
    public javax.swing.JComboBox<Object> cb_product_category;
    public javax.swing.JComboBox<Object> cb_purchase_supplier;
    public javax.swing.JComboBox<String> cb_supplier_city;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JLabel jLabelCategories;
    public javax.swing.JLabel jLabelColaboradores;
    public javax.swing.JLabel jLabelCustomers;
    public javax.swing.JLabel jLabelProducts;
    public javax.swing.JLabel jLabelPurchases;
    public javax.swing.JLabel jLabelReports;
    public javax.swing.JLabel jLabelSales;
    public javax.swing.JLabel jLabelSettings;
    public javax.swing.JLabel jLabelSuppliers;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    public javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    public javax.swing.JPanel jPanelCategories;
    public javax.swing.JPanel jPanelCollaborator;
    public javax.swing.JPanel jPanelCustomers;
    public javax.swing.JPanel jPanelProducts;
    public javax.swing.JPanel jPanelPurchases;
    public javax.swing.JPanel jPanelReports;
    public javax.swing.JPanel jPanelSales;
    public javax.swing.JPanel jPanelSettings;
    public javax.swing.JPanel jPanelSupplimers;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JLabel lb_name_colaborador;
    public javax.swing.JLabel lb_rol_colaborador;
    private javax.swing.JPanel logo;
    public javax.swing.JPanel menu;
    public javax.swing.JTable tb_category;
    public javax.swing.JTable tb_colaborador;
    public javax.swing.JTable tb_customer;
    public javax.swing.JTable tb_product;
    public javax.swing.JTable tb_purchase;
    public javax.swing.JTable tb_reports_purchases;
    public javax.swing.JTable tb_reports_sales;
    public javax.swing.JTable tb_sale;
    public javax.swing.JTable tb_supplier;
    public javax.swing.JTextField txt_category_id;
    public javax.swing.JTextField txt_category_name;
    public javax.swing.JTextField txt_category_search;
    public javax.swing.JTextField txt_colaborador_address;
    public javax.swing.JTextField txt_colaborador_email;
    public javax.swing.JTextField txt_colaborador_id;
    public javax.swing.JTextField txt_colaborador_id_card;
    public javax.swing.JTextField txt_colaborador_name;
    public javax.swing.JPasswordField txt_colaborador_password;
    public javax.swing.JTextField txt_colaborador_search;
    public javax.swing.JTextField txt_colaborador_telephone;
    public javax.swing.JTextField txt_colaborador_username;
    public javax.swing.JTextField txt_customer_address;
    public javax.swing.JTextField txt_customer_email;
    public javax.swing.JTextField txt_customer_id;
    public javax.swing.JTextField txt_customer_name;
    public javax.swing.JTextField txt_customer_search;
    public javax.swing.JTextField txt_customer_telephone;
    public javax.swing.JTextField txt_product_code;
    public javax.swing.JTextField txt_product_description;
    public javax.swing.JTextField txt_product_id;
    public javax.swing.JTextField txt_product_name;
    public javax.swing.JTextField txt_product_price_sale;
    public javax.swing.JTextField txt_product_search;
    public javax.swing.JTextField txt_profile_address;
    public javax.swing.JPasswordField txt_profile_confirm_password;
    public javax.swing.JTextField txt_profile_email;
    public javax.swing.JTextField txt_profile_id;
    public javax.swing.JTextField txt_profile_id_card;
    public javax.swing.JTextField txt_profile_name;
    public javax.swing.JPasswordField txt_profile_new_password;
    public javax.swing.JTextField txt_profile_telephone;
    public javax.swing.JTextField txt_purchase_id_product;
    public javax.swing.JTextField txt_purchase_price;
    public javax.swing.JTextField txt_purchase_productCode;
    public javax.swing.JTextField txt_purchase_productName;
    public javax.swing.JTextField txt_purchase_quantity;
    public javax.swing.JTextField txt_purchase_subtotal;
    public javax.swing.JTextField txt_purchase_total_to_pay;
    public javax.swing.JTextField txt_sale_idCustomer;
    public javax.swing.JTextField txt_sale_nameCustomer;
    public javax.swing.JTextField txt_sale_nameProduct;
    public javax.swing.JTextField txt_sale_price_product;
    public javax.swing.JTextField txt_sale_productCode;
    public javax.swing.JTextField txt_sale_product_id;
    public javax.swing.JTextField txt_sale_quantity;
    public javax.swing.JTextField txt_sale_stock;
    public javax.swing.JTextField txt_sale_subtotal;
    public javax.swing.JTextField txt_sale_total_to_pay;
    public javax.swing.JTextField txt_supplier_address;
    public javax.swing.JTextField txt_supplier_description;
    public javax.swing.JTextField txt_supplier_email;
    public javax.swing.JTextField txt_supplier_id;
    public javax.swing.JTextField txt_supplier_name;
    public javax.swing.JTextField txt_supplier_search;
    public javax.swing.JTextField txt_supplier_telephone;
    // End of variables declaration//GEN-END:variables
}
