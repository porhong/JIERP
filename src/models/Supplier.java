/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author poeho
 */
public class Supplier extends Test {

    public int id = 1;
    public String SupplierName;
    public String contact;
    public int cod;
    public String email;
    public String sql;
    public DefaultTableModel mode;

    @Override
    public void save() {
        try {
            this.sql = "INSERT INTO tblsupplier VALUES(?,NULL,?,?,?,?,NULL,NULL)";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.s.setInt(1, id += 1);
            Database.s.setString(2, SupplierName);
            Database.s.setInt(3, cod);
            Database.s.setString(4, contact);
            Database.s.setString(5, email);
            int i = Database.s.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Supplier created");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error :" + ex.getMessage());
        }
    }

    public void loadingData(JTable table) {
        try {
            this.sql = "SELECT * FROM tblsupplier;";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.rs = Database.s.executeQuery();
            mode = (DefaultTableModel) table.getModel();
            while (Database.rs.next()) {
                Object[] rows = {Database.rs.getInt("id"), Database.rs.getString("supplierName"),
                    Database.rs.getInt("cod"), Database.rs.getString("contact"),
                    Database.rs.getString("email")};
                mode.addRow(rows);
            }

        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(null, "Error:" + ex.getMessage());
        }
    }

    @Override
    public void delete() {
        try {
            this.sql = "DELETE FROM tblsupplier WHERE id=?";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.s.setInt(1, id);
            Database.s.execute();
            JOptionPane.showMessageDialog(null, "User Deleted");

        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(null, "Error:" + ex.getMessage());
        }
    }

    @Override
    public void update() {
        try {
            this.sql = "UPDATE tblsupplier SET supplierName =?, cod =?, contact=?, email=? WHERE id =?;";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.s.setString(1, SupplierName);
            Database.s.setInt(2, cod);
            Database.s.setString(3, contact);
            Database.s.setString(4, email);
            Database.s.setInt(5, id);
            int i = Database.s.executeUpdate();
            if (i > 0) {
                JOptionPane.showConfirmDialog(null, "User Updated");
            }
        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(null, "Error:" + ex.getMessage());
        }
    }

    public void transferData(JTable table, JTextField txtName, JTextField txtCod, JTextField txtContact, JTextField txtEmail) {
        int selectedRow = table.getSelectedRow();
        txtName.setText(table.getValueAt(selectedRow, 1).toString());
        txtCod.setText(table.getValueAt(selectedRow, 2).toString());
        txtContact.setText(table.getValueAt(selectedRow, 3).toString());
        txtEmail.setText(table.getValueAt(selectedRow, 4).toString());
    }

}
