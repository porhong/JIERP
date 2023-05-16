/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static models.User.id;

/**
 *
 * @author poeho
 */
public class Category extends Test {

    public int id;
    public String name;
    public String sql;
    public DefaultTableModel mode;

    @Override
    public void save() {
        try {
            this.sql = "INSERT INTO tblcategory VALUES(NULL,NULL,?,NULL,NULL);";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.s.setString(1, name);
            int i = Database.s.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Category created");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error :" + ex.getMessage());
        }
    }
    
    public void loadingData(JTable table) {
        try {
            this.sql = "SELECT * FROM tblcategory;";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.rs = Database.s.executeQuery();
            mode = (DefaultTableModel) table.getModel();
            while (Database.rs.next()) {
                Object[] rows = {Database.rs.getInt("id"), Database.rs.getInt("userid"),
                    Database.rs.getString("categoryName"), Database.rs.getString("createAt"),
                    Database.rs.getString("updateAt")};
                    mode.addRow(rows);
            }

        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(null, "Error:" + ex.getMessage());
        }
    }

    @Override
    public void delete() {
        try {
            this.sql = "DELETE FROM tblcategory WHERE id=?";
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
            this.sql = "UPDATE tblcategory SET categoryName=? WHERE id =?";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.s.setString(1, name);
            Database.s.setInt(2, id);
            int i = Database.s.executeUpdate();
            if (i > 0) {
                JOptionPane.showConfirmDialog(null, "User Updated");
            }
        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(null, "Error:" + ex.getMessage());
        }
    }
    
    public void transferData(JTable table, JTextField txtCategory) {
        int selectedRow = table.getSelectedRow();
        txtCategory.setText(table.getValueAt(selectedRow, 3).toString());
    }
    
    
    
    

}
