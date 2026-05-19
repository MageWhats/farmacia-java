package Controllers;

import Models.EmployeesDao;
import static Models.EmployeesDao.address_user;
import static Models.EmployeesDao.email_user;
import static Models.EmployeesDao.full_name_user;
import static Models.EmployeesDao.id_user;
import static Models.EmployeesDao.telephone_user;
import Views.SystemView;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SettingsController implements MouseListener {

    private SystemView views;

    public SettingsController(SystemView views) {
        this.views = views;

        this.views.jLabelColaborator.addMouseListener(this);
        this.views.jLabelCategories.addMouseListener(this);
        this.views.jLabelCustomers.addMouseListener(this);
        this.views.jLabelProducts.addMouseListener(this);
        this.views.jLabelPurchases.addMouseListener(this);
        this.views.jLabelReports.addMouseListener(this);
        this.views.jLabelSettings.addMouseListener(this);
        this.views.jLabelSupplimers.addMouseListener(this);
        this.views.jLabelSales.addMouseListener(this);
        
        Profile();

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == views.jLabelProducts) {
            views.jPanelProducts.setBackground(new Color(152, 202, 63));
        } else if (e.getSource() == views.jLabelCategories) {
            views.jPanelCategories.setBackground(new Color(152, 202, 63));
        } else if (e.getSource() == views.jLabelColaborator) {
            views.jPanelCollaborator.setBackground(new Color(152, 202, 63));
        } else if (e.getSource() == views.jLabelCustomers) {
            views.jPanelCustomers.setBackground(new Color(152, 202, 63));
        } else if (e.getSource() == views.jLabelPurchases) {
            views.jPanelPurchases.setBackground(new Color(152, 202, 63));
        } else if (e.getSource() == views.jLabelReports) {
            views.jPanelReports.setBackground(new Color(152, 202, 63));
        } else if (e.getSource() == views.jLabelSettings) {
            views.jPanelSettings.setBackground(new Color(152, 202, 63));
        } else if (e.getSource() == views.jLabelSupplimers) {
            views.jPanelSupplimers.setBackground(new Color(152, 202, 63));
        } else if (e.getSource() == views.jLabelSales) {
            views.jPanelSales.setBackground(new Color(152, 202, 63));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == views.jLabelProducts) {
            views.jPanelProducts.setBackground(new Color(18, 45, 61));
        } else if (e.getSource() == views.jLabelCategories) {
            views.jPanelCategories.setBackground(new Color(18, 45, 61));
        } else if (e.getSource() == views.jLabelColaborator) {
            views.jPanelCollaborator.setBackground(new Color(18, 45, 61));
        } else if (e.getSource() == views.jLabelCustomers) {
            views.jPanelCustomers.setBackground(new Color(18, 45, 61));
        } else if (e.getSource() == views.jLabelPurchases) {
            views.jPanelPurchases.setBackground(new Color(18, 45, 61));
        } else if (e.getSource() == views.jLabelReports) {
            views.jPanelReports.setBackground(new Color(18, 45, 61));
        } else if (e.getSource() == views.jLabelSettings) {
            views.jPanelSettings.setBackground(new Color(18, 45, 61));
        } else if (e.getSource() == views.jLabelSupplimers) {
            views.jPanelSupplimers.setBackground(new Color(18, 45, 61));
        } else if (e.getSource() == views.jLabelSales) {
            views.jPanelSales.setBackground(new Color(18, 45, 61));
        }
    }

    private void Profile() {
        this.views.txt_profile_id.setText(""+id_user);
        this.views.txt_profile_name.setText(""+full_name_user);
        this.views.txt_profile_address.setText(""+address_user);
        this.views.txt_profile_telefono.setText(""+telephone_user);
        this.views.txt_profile_mail.setText(""+email_user);
    }
}
