/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.swing.JTable;

/**
 *
 * @author vanreach
 */
public interface Actions {
    void save();
    void update();
    void delete();
    void search(JTable table);
}
