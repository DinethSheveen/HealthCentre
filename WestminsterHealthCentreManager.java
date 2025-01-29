/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HealthCentreCoursework_5COSC019W_Package;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;


public class WestminsterHealthCentreManager implements HealthCentreManager {
    
    // ArrayLists
    private ArrayList<StaffMember> staffMemberList;
    private int staff_limit;
    
    public WestminsterHealthCentreManager(int maxMemebersNumber){
        staffMemberList = new ArrayList<StaffMember>();
        staff_limit = maxMemebersNumber;
    }

    @Override
    public boolean runMenu() {
         boolean exit = false; // Exit flag
        
        // Run console menu
        System.out.println("\n-- WESTMINSTER HEALTH CENTRER CONSOLE MENU--");
        
        System.out.println("To save and exit, press 0");
        
        System.out.println("To Add a new staff member, press 1");
        
        System.out.println("To Print the list of staff members press 2");
        
        System.out.println("To Open GUI, press 3");

        System.out.println("To Delete a staff member,press 4");

        
        // Switch based on selected option
        Scanner s = new Scanner(System.in);
        int choice;
        choice = s.nextInt();

        
        switch(choice){
            
             // Exit
            case 0:
                exit = true;
                break;
            
            // Add staff member
            case 1:
                this.addStaffMember();
                break;
            // Print staff members
            case 2:
                this.printStaffMemberList();
                break;
            
            // GUI
            case 3:
                this.runGUI();
                break;

            //Delete staff
            case 4:
                this.deleteStaffMember();
                break;
        }
        
        return exit;
    }

    @Override
    public void addStaffMember() {
        Scanner s = new Scanner (System.in);
        
        if(staffMemberList.size() < staff_limit){
            System.out.println("Press 1 if you want to add a Doctor");
            System.out.println("Press 2 if you want to add a Receptionist");
            System.out.println("Press 3 if you want to add a Nurse");
            
            int choiceStaff = s.nextInt();
            s.nextLine();
            
            //common questions
            System.out.println("Enter the first name");
            String name = s.nextLine();
            
            System.out.println("Enter the last name");
            String surname = s.nextLine();
            
            System.out.println("Enter the staff ID");
            String staffID = s.nextLine();
            
            
            System.out.println("Enter the date of birth  (dd/MM/yyyy format only!)");
            LocalDate date = null;
            String dob = null;
            boolean parsingSucceds = false;
            while(!parsingSucceds){
                dob = s.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                
                try{
                    date = LocalDate.parse(dob, formatter);
                    parsingSucceds = true; // If parsing succeeds, the format is correct    
                }catch(DateTimeParseException e){
                    System.out.println("Enter the correct format. It should be dd/MM/yyyy!");
                    parsingSucceds = false;
                }
            }
            
            
            System.out.println("Enter the phone number ");
            String phone = null;
            boolean correctPhoneFormat = false;
            while (!correctPhoneFormat){
                phone = s.nextLine();
                if(phone.matches("^[0-9]+$")){
                    correctPhoneFormat = true;
                }
                else{
                    System.out.println("Enter the correct format. It should contain only numbers!");
                    correctPhoneFormat = false;
                }
            }
            
            // check if the staff is a doctor or a receptionist
            switch(choiceStaff){
                case 1:
                    // it is a doctor
                    System.out.println("Enter the licence number");
                    String licenceNum = s.nextLine();
                    
                    System.out.println("Enter the medical specialisation ");
                    String specialisation = s.nextLine();

                    System.out.println("Enter the number of consultation per week:");
                    int consultationNum = s.nextInt();
                    s.nextLine();

                    // create a new Doctor and add to the list
                    Doctor doctor = new Doctor(name, surname);
                    doctor.setLicenceNumber(licenceNum);
                    doctor.setSpecialisation(specialisation);
                    doctor.setDob(date);
                    doctor.setPhoneNo(phone);
                    doctor.setStaffID(staffID);
                    doctor.setNumberConsultationsperWeek(consultationNum);
                    this.addStaffToList(doctor);
                    
                    break;
                    
                case 2:
                    //it is a receptionist
                    System.out.println("Enter the desk number");
                    int deskNum = s.nextInt();
                    s.nextLine();
                    
                    System.out.println("Enter the working hour per week");
                    int hoursPerWeek = s.nextInt();
                    s.nextLine();
                    
                    // create a new receptionist and add to the list
                    Receptionist receptionist = new Receptionist(name, surname);
                    receptionist.setDeskNumber(deskNum);
                    receptionist.setHoursPerWeek(hoursPerWeek);
                    receptionist.setDob(date);
                    receptionist.setPhoneNo(phone);
                    receptionist.setStaffID(staffID);
                    this.addStaffToList(receptionist);
                    break;

                case 3:
                    //it is a nnurse
                    System.out.println("Enter years of Experience : ");
                    int experience = s.nextInt();
                    s.nextLine();

                    System.out.println("Enter the department : ");
                    String department = s.next();

                    Nurse nurse = new Nurse(name,surname);
                    nurse.setYearsOfExperience(experience);
                    nurse.setDepartment(department);
                    this.addStaffToList(nurse);
                    break;
            }
            
        }
        else {
            System.out.println("No more space in the system");
        }
    }
    
    public void addStaffToList(StaffMember staff){
        
        //check if there are space avilable
        if(this.staffMemberList.size() < staff_limit) {
            staffMemberList.add(staff);
        }
        else{
            System.out.println("No more space in teh list");
        }
    }

    @Override
    public void printStaffMemberList() {
        
        
        if (!staffMemberList.isEmpty()){
            for(StaffMember member : staffMemberList) {
                Collections.sort(staffMemberList);
                System.out.println(member.toString());
            }
        }
        else{
            System.out.println("There are no staff memebers in the system.");
        }
    }

    @Override
    public void runGUI() {
        StaffTableGUI table = new StaffTableGUI(staffMemberList);
        table.setVisible(true);
    }

    public void deleteStaffMember(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the staff id : ");
        String staffId = input.next();

        for(int i =0;i<staffMemberList.size();i++){
            if(staffMemberList.get(i).getStaffID().equals(staffId)){
                System.out.println("Name : "+ staffMemberList.get(i).getName());
                System.out.println("Surname : "+staffMemberList.get(i).getSurname());

                if(staffMemberList.get(i) instanceof Doctor){
                    System.out.println("Role : Doctor");
                }
                else if(staffMemberList.get(i) instanceof Receptionist){
                    System.out.println("Role : Receptionist");
                }
                else if(staffMemberList.get(i) instanceof Nurse){
                    System.out.println("Role : Nurse");
                }

                staffMemberList.remove(staffMemberList.get(i));

            }
        }
    }
}
