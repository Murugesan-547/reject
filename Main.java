package project;

import java.io.*;
import java.util.*;

class Project implements Serializable {
    String projectCode;
    String projectName;
    int projectStrength;

    public Project(String code, String name, int strength) {
        this.projectCode = code;
        this.projectName = name;
        this.projectStrength = strength;
    }

    @Override
    public String toString() {
        return "Project [projectCode=" + projectCode + ", projectName=" + projectName + ", projectStrength="
                + projectStrength + "]";
    }
}

class Employee implements Serializable {
    String employeeId;
    String employeeName;
    String employeePhone;
    String employeeAddress;
    int employeeSalary;

    public Employee(String id, String name, String phone, String address, int salary) {
        this.employeeId = id;
        this.employeeName = name;
        this.employeePhone = phone;
        this.employeeAddress = address;
        this.employeeSalary = salary;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeePhone="
                + employeePhone + ", employeeAddress=" + employeeAddress + ", employeeSalary=" + employeeSalary + "]";
    }
}

class ProjectSerializer {
    ArrayList<Employee> elist1 = new ArrayList<>();
    ArrayList<Employee> elist2 = new ArrayList<>();
    ArrayList<Employee> elist3 = new ArrayList<>();
    HashMap<Project, ArrayList<Employee>> projectMap1 = new HashMap<>();

    public void serializeProjectDetails(HashMap<Project, ArrayList<Employee>> projectMap) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("SerializeData.ser"))) {
            oos.writeObject(projectMap);
            System.out.println("Serialized Data saved to SerializeData.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserializeProjectDetails() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("SerializeData.ser"))) {
            HashMap<Project, ArrayList<Employee>> deserializedMap = (HashMap<Project, ArrayList<Employee>>) ois.readObject();
            System.out.println("DeSerialized Data :");
            for (Map.Entry<Project, ArrayList<Employee>> entry : deserializedMap.entrySet()) {
                Project project = entry.getKey();
                ArrayList<Employee> employees = entry.getValue();

                System.out.println("The Project " + project + " Has the following Employees");
                System.out.println("Employees .............");
                for (Employee employee : employees) {
                    System.out.println(employee);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ProjectSerializer projectSerializer = new ProjectSerializer();
        
        
        
        

        Employee e1 = new Employee("E001", "Harsha", "9383993933", "RTNagar", 1000);
        Employee e2 = new Employee("E002", "Harish", "9354693933", "Jayanagar", 2000);
        Employee e3 = new Employee("E003", "Meenal", "9383976833", "Malleswaram", 1500);
        
        projectSerializer.elist1.add(e1);
        projectSerializer.elist1.add(e2);
        projectSerializer.elist1.add(e3);
        
       
        
        Employee e4 = new Employee("E004","Sundar","9334593933","Vijayanagar",3000);
        Employee e5 = new Employee("E005","Suman","9383678933","Indiranagar",2000);
        Employee e6 = new Employee("E006","Suma","9385493933","KRPuram",1750);
        
        projectSerializer.elist2.add(e4);
        projectSerializer.elist2.add(e5);
        projectSerializer.elist2.add(e6);
        
        
        
        Employee e7 = new Employee("E007","Chitra","9383978933","Koramangala",4000);
        Employee e8 = new Employee("E008","Suraj","9383992133","Malleswaram",3000);
        Employee e9 = new Employee("E009","Manu","9345193933","RTNagar",2000);
        
        projectSerializer.elist3.add(e7);
        projectSerializer.elist3.add(e8);
        projectSerializer.elist3.add(e9);

        

        
        
       

        Project project1 = new Project("P1", "Music Synthesizer", 23);
        Project project2 = new Project("P2", "Vehicle Movement Tracker", 13);
        Project project3 = new Project("P3", "Liquid Viscosity Finder", 15);

        
        projectSerializer.projectMap1.put(project1, projectSerializer.elist1);
        projectSerializer.projectMap1.put(project2, projectSerializer.elist2);
        projectSerializer.projectMap1.put(project3, projectSerializer.elist3);

        projectSerializer.serializeProjectDetails(projectSerializer.projectMap1);
        System.out.println("Output : DeSerializeData");
        projectSerializer.deserializeProjectDetails();
    }
}
