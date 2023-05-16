/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import views.users.MainForm;

/**
 *
 * @author vanreach
 */
public class User extends Test {

    public static int id;
    public int accountTypeId;
    public String userName;
    public String password;
    public String active;
    public String sql;
    public DefaultTableModel mode;

    public void logIn(JFrame jFrame) {
        this.sql = "SELECT * FROM tbluser WHERE UserName=? and password=?";
        try {
            Database.connectionDb("localhost", "pos_db", "root", "");
            this.sql = "SELECT * FROM tbluser WHERE UserName=? and password=?";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.s.setString(1, this.userName);
            Database.s.setString(2, this.password);
            Database.rs = Database.s.executeQuery();
            if (Database.rs.next()) {
                MainForm mainform = new MainForm();
                this.id=Database.rs.getInt("id");
                MainForm.lblVersion.setText("Version :1.0 Username :" + Database.rs.getString("UserName"));
                mainform.show();
                jFrame.dispose();
            } else {
                JOptionPane.showConfirmDialog(null, "Username and Password is invalid !");
            }

        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(null, "Can't connect database :" + ex.getMessage());
        }
    }

    public void setAccountName(JComboBox cboAcoountTypeName) {
        try {
            this.sql = "SELECT * FROM tblaccount";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.rs = Database.s.executeQuery();
            while (Database.rs.next()) {
                cboAcoountTypeName.addItem(Database.rs.getString("AccountName"));
            }

        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(null, "Error:" + ex.getMessage());
        }
    }

    public int getAccountId(JComboBox cboAccountTypeName1) throws Exception {
        int id = 0;
        this.sql = "SELECT * from tblaccount WHERE AccountName=?";
        Database.s = Database.con.prepareStatement(this.sql);
        Database.s.setString(1, cboAccountTypeName1.getSelectedItem().toString());
        Database.rs = Database.s.executeQuery();
        if (Database.rs.next()) {
            id = Integer.valueOf(Database.rs.getInt("id"));
        }
        return id;
    }

    @Override
    public void save() {
        try {
            this.sql = "INSERT INTO tbluser VALUES(null,?,?,?,?)";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.s.setInt(1, this.accountTypeId);
            Database.s.setString(2, this.userName);
            Database.s.setString(3, this.password);
            Database.s.setString(4, this.active);
            Database.s.execute();
            JOptionPane.showMessageDialog(null, "user created");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage());
        }
    }

    public void loadingData(JTable table) {
        try {
            this.sql = "SELECT * FROM tbluser INNER JOIN tblaccount ON tbluser.AccountTypeId=tblaccount.id ORDER BY tbluser.id desc LIMIT 10";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.rs = Database.s.executeQuery();
            mode = (DefaultTableModel) table.getModel();
            while (Database.rs.next()) {
                Object[] rows = {Database.rs.getInt("id"), Database.rs.getString("UserName"),
                    Database.rs.getString("Password"), Database.rs.getString("Active"),
                    Database.rs.getString("AccountName")};
                mode.addRow(rows);
            }

        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(null, "Error:" + ex.getMessage());
        }
    }

    @Override
    public void delete() {
        try {
            this.sql = "DELETE FROM tbluser WHERE id=?";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.s.setInt(1, id);
            Database.s.execute();
            JOptionPane.showMessageDialog(null, "User Deleted");

        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(null, "Error:" + ex.getMessage());
        }
    }

    public void transferData(JTable table, JTextField txtUserName, JTextField txtPassword, JRadioButton rTrue, JRadioButton rFalse, JComboBox cboAccountTypeName) {
        int selectedRow = table.getSelectedRow();
        txtUserName.setText(table.getValueAt(selectedRow, 1).toString());
        txtPassword.setText(table.getValueAt(selectedRow, 2).toString());
        String check = table.getValueAt(selectedRow, 3).toString();
        if (check.equals("True")) {
            rTrue.setSelected(true);
        } else {
            rFalse.setSelected(true);
        }
        cboAccountTypeName.setSelectedItem(table.getValueAt(selectedRow, 4));
    }

    @Override
    public void update() {
        try {
            this.sql = "UPDATE tbluser SET AccountTypeId=?,UserName=?,`Password`=?,Active=? WHERE id=?";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.s.setInt(1, accountTypeId);
            Database.s.setString(2, this.userName);
            Database.s.setString(3, this.password);
            Database.s.setString(4, active);
            Database.s.setInt(5, id);
            int i = Database.s.executeUpdate();
            if (i > 0) {
                JOptionPane.showConfirmDialog(null, "User Updated");
            }
        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(null, "Error:" + ex.getMessage());
        }
    }

    @Override
    public void search(JTable table) {
        try {
            this.sql = "SELECT * FROM tbluser INNER JOIN tblaccount ON tbluser.AccountTypeId=tblaccount.id\n"
                    + "WHERE tbluser.UserName LIKE CONCAT('%',?,'%')";
            Database.s = Database.con.prepareStatement(this.sql);
            Database.s.setString(1, userName);
            Database.rs = Database.s.executeQuery();
            mode = (DefaultTableModel) table.getModel();
            mode.setRowCount(0);
            while (Database.rs.next()) {
                Object[] rows = {Database.rs.getInt("id"), Database.rs.getString("UserName"), Database.rs.getString("Password"),
                    Database.rs.getString("active"), Database.rs.getString("AccountName")
                };
                mode.addRow(rows);
            }

        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(null, "Error:" + ex.getMessage());
        }
    }

}
