/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vanreach
 */
public class Product extends Test {
    
    public int id;
    public String name;
    public long barcode;
    public double unitPrice;
    public int qtyInStock;
    public String photo;
    public String sql;
    public int categoryId;
   public DefaultTableModel mode;
    
    public void setCategoryName(JComboBox cboCategoryName) {
        try {
            this.sql = "SELECT categoryName FROM tblcategory";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.rs = Database.s.executeQuery();
            while (Database.rs.next()) {
                cboCategoryName.addItem(Database.rs.getString("categoryName"));
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage());
        }
    }    
    
    public int getCategoryId(JComboBox cboCategoryName) {
        int id = 0;
        try {
            this.sql = "SELECT id,categoryName FROM tblcategory WHERE categoryName=?";
            Database.s = Database.con.prepareStatement(this.sql);
            String categoryName = cboCategoryName.getSelectedItem().toString();
            Database.s.setString(1, categoryName);
            Database.rs = Database.s.executeQuery();
            if (Database.rs.next()) {
                id = Database.rs.getInt("id");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage());
        }
        
        return id;
    }
    
    @Override
    public void save() {
        try {
            int check = checkDumplicated("tblProduct", "productName", this.name, "Product name");
            if (check== 0) {
                return;
            };
            int check2 = checkDumplicated("tblProduct", "code",String.valueOf(barcode), "Barcode");
            if (check2== 0) {
                return;
            };
            this.sql = "INSERT INTO tblproduct VALUES(null,?,?,?,?,?,0,?,CURDATE(),null)";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.s.setInt(1, User.id);
            Database.s.setInt(2, categoryId);
            Database.s.setString(3, name);
            Database.s.setLong(4, barcode);
            Database.s.setDouble(5, unitPrice);
            Database.s.setString(6, photo);
            int i = Database.s.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "product created");
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error :" + ex.getMessage());
        }
    }
    
    
        public void loadingData(JTable table) {
        try {
            this.sql = "SELECT tblproduct.id,productName,`code`,unitPrice,tblcategory.categoryName FROM tblproduct JOIN tblcategory ON tblproduct.categoryId = tblcategory.id;";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.rs = Database.s.executeQuery();
            mode = (DefaultTableModel) table.getModel();
            while (Database.rs.next()) {
                Object[] rows = {Database.rs.getInt("id"), 
                    Database.rs.getString("productName"),
                    Database.rs.getInt("code"),
                    Database.rs.getInt("unitPrice"),
                    Database.rs.getString("categoryName")};
                mode.addRow(rows);
            }

        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(null, "Error:" + ex.getMessage());
        }
    }

    @Override
    public void delete() {
        try {
            this.sql = "DELETE FROM tblproduct WHERE id=?";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.s.setInt(1, id);
            Database.s.execute();
            JOptionPane.showMessageDialog(null, "User Deleted");
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "Error:" + ex.getMessage());
        }
            
    }
    int  checkDumplicated(String tablename, String columnName, String id, String msg) throws SQLException{
        try {
            this.sql = "SELECT * FROM "+ tablename + " WHERE " + columnName + "=?";
            Database.s =Database.con.prepareStatement(this.sql);
            Database.s.setString(1, id);
            Database.rs = Database.s.executeQuery();
            if (Database.rs.next()) {
                JOptionPane.showMessageDialog(null, msg +"has been already!");
                return 0;
            }
            
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
        return 1;
    }
    public static void SetImage (JLabel lbl_image, JTextField txtImage){
        String pathimage = null;
        JFileChooser chhose= new JFileChooser (FileSystemView.getFileSystemView().getFileSystemView().getHomeDirectory());
        
    }
    
}
