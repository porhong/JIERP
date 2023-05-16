package models;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class MyFunction {

    public static int CheckTextboxEmpty(JTextField... txt) {
        for (JTextField t : txt) {
            String st = t.getText();
            st = st.trim();
            if (st.length() == 0) {
                t.grabFocus();
                Toolkit.getDefaultToolkit().beep();
                return 0;
            }
        }
        return 1;
    }

    public static void ClearTextbox(JTextField... txt) {
        for (JTextField t : txt) {
            t.setText("");
        }

    }

    public static int CheckComboBoxEmpty(JComboBox... txt) {
        for (JComboBox t : txt) {
            String st = (String) t.getSelectedItem();
            st = st.trim();
            if (st.length() == 0) {
                t.grabFocus();
                Toolkit.getDefaultToolkit().beep();
                return 0;
            }
        }
        return 1;
    }

    public static boolean TableItemEmpty(JTable table) {
        if (table.getSelectionModel().isSelectionEmpty()) {
            JOptionPane.showConfirmDialog(null, "Please select record !", "POS", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
            return true;
        }
        return false;
    }

    public static boolean RemoveRecordFromTable(JTable table) {

        int remove = JOptionPane.showConfirmDialog(null, "Are you want to delete record ?", "POS", JOptionPane.YES_NO_OPTION);
        if (remove == JOptionPane.YES_OPTION) {
            int row = table.getSelectedRow();
            table.remove(row);

        }
        return false;
    }

    public static void ReadImage(JTable table, JLabel lbl_image,int column) {
        int row = table.getSelectedRow();
        byte[] bytes = (byte[]) table.getValueAt(row, column);
        if (!table.getValueAt(row, column).equals("")) {
            ImageIcon icon = new ImageIcon(new ImageIcon(bytes).getImage().getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH));
            lbl_image.setIcon(icon);
        }
       
    }

    public static void SetImage(JLabel lbl_image, JTextField txt_image) {
        String pathimage = null;
        JFileChooser choose = new JFileChooser(FileSystemView.getFileSystemView().getFileSystemView().getHomeDirectory());
        choose.setDialogTitle("Select an image");
        choose.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG and GIF images", "png", "gif", "jpg","swf");
        choose.addChoosableFileFilter(filter);
        int click = choose.showOpenDialog(null);
        if (click == JFileChooser.APPROVE_OPTION) {
            File files = choose.getSelectedFile();
            pathimage = files.getPath();
            ImageIcon icon = new ImageIcon(new ImageIcon(pathimage).getImage().getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH));
            lbl_image.setIcon(icon);
            txt_image.setText(pathimage);

        }

    }

    public static void RemoveImage(JLabel label) {
        label.setIcon(null);

    }

    public static void RemoveImage(JLabel label, JTextField txt_image) {
        label.setIcon(null);
        txt_image.setText(null);
        

    }

    public static void InputString(JTextField txt, KeyEvent evt) {
        if (Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != 8) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }

    public static void inputNumber(JTextField txt, KeyEvent evt) {
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != 8) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }

    public static void inputNumber(JTextField txt, KeyEvent evt, int len) {
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != 8) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        } else {
            if (txt.getText().length() >= len) {
                Toolkit.getDefaultToolkit().beep();
                evt.consume();
            }
        }
    }

    public static void inputFloat(JTextField txt, KeyEvent evt) {
        if (evt.getKeyChar() == 46) {
            if (txt.getText().indexOf(46) != -1) {
                Toolkit.getDefaultToolkit().beep();
                evt.consume();
            } else {
                if (txt.getText().equals("")) {
                    txt.setText("0.");
                    evt.consume();
                }
            }

        } else if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != 8) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }

    }

    public static void inputFloat(JTextField txt, KeyEvent evt, int len) {
        if (evt.getKeyChar() == 46) {
            if (txt.getText().indexOf(46) != -1) {
                Toolkit.getDefaultToolkit().beep();
                evt.consume();
            } else {
                if (txt.getText().equals("")) {
                    txt.setText("0.");
                    evt.consume();
                }
            }

        } else if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != 8) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        } else {
            if (txt.getText().length() >= len) {
                Toolkit.getDefaultToolkit().beep();
                evt.consume();
            }
        }

    }
    
    
}

//https://www.codejava.net/java-se/swing/jtable-popup-menu-example
