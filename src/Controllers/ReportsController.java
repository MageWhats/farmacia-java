
package Controllers;

import Models.ProductDao;
import Models.Purchases;
import Models.PurchasesDao;
import Models.Sales;
import Models.SalesDao;
import Views.SystemView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ReportsController implements MouseListener {

    private final SalesDao saleDao;
    private final PurchasesDao purchaseDao;
    private final SystemView views;
    
    private DefaultTableModel salesModel = new DefaultTableModel();
    private DefaultTableModel purchasesModel = new DefaultTableModel();

    public ReportsController(SalesDao saleDao, PurchasesDao purchaseDao, SystemView views) {
        this.saleDao = saleDao;
        this.purchaseDao = purchaseDao;
        this.views = views;

        // Escuchador para la etiqueta del menú lateral que abre esta pantalla de reportes
        this.views.jLabelReports.addMouseListener(this);
        listAllSalesReports();
        listAllPurchasesReports();
    }

    // 1. Carga el historial de Ventas Realizadas en la tabla superior
    public void listAllSalesReports() {
        List<Sales> list = saleDao.listAllSalesQuery(); // Método blindado de tu SalesDao
        salesModel = (DefaultTableModel) views.tb_reports_sales.getModel(); // Verifica el nombre de tu JTable de ventas
        salesModel.setRowCount(0); // Limpieza rápida de filas

        for (Sales sale : list) {
            Object[] row = new Object[5];
            row[0] = sale.getId();          // Factura Venta
            row[1] = sale.getCustomerName(); // Cliente
            row[2] = sale.getEmployeeName(); // Empleado / Cajero
            row[3] = sale.getTotalToPay();   // Total
            row[4] = sale.getSaleDate();     // Fecha de Venta
            salesModel.addRow(row);
        }
        views.tb_reports_sales.setModel(salesModel);
    }

    // 2. Carga el historial de Compras Realizadas en la tabla inferior
    public void listAllPurchasesReports() {
        List<Purchases> list = purchaseDao.listAllPurchasesQuery(); // Método blindado de tu PurchasesDao
        purchasesModel = (DefaultTableModel) views.tb_reports_purchases.getModel(); // Verifica el nombre de tu JTable de compras
        purchasesModel.setRowCount(0);

        for (Purchases pur : list) {
            Object[] row = new Object[4];
            row[0] = pur.getId();           // Factura
            row[1] = pur.getSupplierName(); // Proveedor
            row[2] = pur.getTotal();        // Total de Compra
            row[3] = pur.getCreated();      // Fecha de Compra
            purchasesModel.addRow(row);
        }
        views.tb_reports_purchases.setModel(purchasesModel);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.jLabelReports) {
            // Cambia al índice numérico correspondiente a esta pestaña en tu SystemView
            views.jTabbedPane1.setSelectedIndex(7); 
            
            // ¡Magia en vivo!: Trae los datos frescos de MySQL de ambos módulos al mismo tiempo
            listAllSalesReports();
            listAllPurchasesReports();
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}

