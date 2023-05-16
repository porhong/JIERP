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
public class Account extends Test {

    public int id;
    public String name;
    public String sql;
    public DefaultTableModel mode;
    
     @Override
    public void save() {
        try {
            this.sql = "INSERT INTO tblaccount VALUES(NULL,?);";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.s.setString(1, name);
            int i = Database.s.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Account created");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error :" + ex.getMessage());
        }
    }
    
    public void loadingData(JTable table) {
        try {
            this.sql = "SELECT * FROM tblaccount;";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.rs = Database.s.executeQuery();
            mode = (DefaultTableModel) table.getModel();
            while (Database.rs.next()) {
                Object[] rows = {Database.rs.getInt("id"), Database.rs.getString("AccountName")
                };
                    mode.addRow(rows);
            }

        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(null, "Error:" + ex.getMessage());
        }
    }

    @Override
    public void delete() {
        try {
            this.sql = "DELETE FROM tblaccount WHERE id=?";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.s.setInt(1, id);
            Database.s.execute();
            JOptionPane.showMessageDialog(null, "Account Deleted");

        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(null, "Error:" + ex.getMessage());
        }
    }

    @Override
    public void update() {
        try {
            this.sql = "UPDATE tblaccount SET AccountName=? WHERE id =?";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.s.setString(1, name);
            Database.s.setInt(2, id);
            int i = Database.s.executeUpdate();
            if (i > 0) {
                JOptionPane.showConfirmDialog(null, "Account Updated");
            }
        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(null, "Error:" + ex.getMessage());
        }
    }
    
    public void transferData(JTable table, JTextField txtName) {
        int selectedRow = table.getSelectedRow();
        txtName.setText(table.getValueAt(selectedRow, 1).toString());
    }
    
}
