package HealthCentreCoursework_5COSC019W_Package;

public class Nurse extends StaffMember{
    private int yearsOfExperience;
    private String department;

    //Constructor
    public Nurse(String name,String surname){
        super(name,surname);
    }

    //Setters and getters
    public int getYearsOfExperience(){
        return this.yearsOfExperience;
    }

    public String getDepartment(){
        return this.department;
    }

    public void setYearsOfExperience(int yearsOfExperience){
        this.yearsOfExperience=yearsOfExperience;
    }

    public void setDepartment(String department){
        this.department=department;
    }

    @Override
    public String toString(){
        return super.toString()+"\nYears of Experience : "+this.yearsOfExperience+"\nDepartment : "+this.department;
    }

}
