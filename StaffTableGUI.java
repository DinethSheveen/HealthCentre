/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HealthCentreCoursework_5COSC019W_Package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class StaffTableGUI extends JFrame {
    
    JTable myTable;
    StaffTableModel tableModel;
    ArrayList<StaffMember> list;
    
    // contructor
    public StaffTableGUI(ArrayList<StaffMember> list){
        
        //set the title
        this.setTitle("Staff in Westminster Health Centre");
        
        // initialise and instantiate the instance variable 
        this.list = list;
        tableModel = new StaffTableModel(list);
        myTable =  new JTable(tableModel);
        
        // set the size of the frame 
        setBounds(20,20,800,600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
        // sorting
        myTable.setAutoCreateRowSorter(true);
      
        // add the table to the panle
        JScrollPane scrollPane = new JScrollPane(myTable); 
        scrollPane.setPreferredSize(new Dimension(380,280)); 

        // add a button on the bottom
        JButton button = new JButton("Statistics");
        
        // add the panel to the frame
        add(scrollPane,BorderLayout.CENTER); 
        add(button, BorderLayout.SOUTH);

        EventHandler handler = new EventHandler();
        button.addActionListener(handler);
    }

    public class EventHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){

            int average;
            int consultations=0;
            int doctors=0;

            for(int i=0;i<list.size();i++) {
                if (list.get(i) instanceof Doctor) {
                    consultations += ((Doctor) list.get(i)).getNumberConsultationsperWeek();
                    doctors++;
                }
            }

            average = consultations/doctors;
            JOptionPane.showMessageDialog(null,"Average number of consultations : "+average);
        }
    }
    
}
