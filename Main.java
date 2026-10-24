package StudentManagementSystem;
import java.util.*;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        StudentDAO sd=new StudentDAO();
        boolean running=true;
      while(running){
        System.out.print("===== Student Management System =====\r\n" + //
                        "1. Add Student\r\n" + //
                        "2. View All Students\r\n" + //
                        "3. Search Student\r\n" + //
                        "4. update Student\r\n"+//
                        "5. delete Student\r\n"+//
                        "6. Exit\r\n" + //
                        "Enter your choice:");
        String input=sc.nextLine(); 
        try{              
          int exp=Integer.parseInt(input);
          switch(exp){
            case 1:
                System.out.println("Enter StudentId:");
                String studid=sc.nextLine();
                int id=Integer.parseInt(studid);
                System.out.println("Enter StudentsName:");
                String name=sc.nextLine();
                System.out.println("Enter StudentAge:");
                String studage=sc.nextLine();
                int age=Integer.parseInt(studage);
                System.out.println("Enter StudentCourse");
                String course=sc.nextLine();
                System.out.println("Enter StudentMarks:");
                String studmark=sc.nextLine();
                double marks=Double.parseDouble(studmark);
              try{
                Student s=new Student(id, name, age, course, marks);
                int res=sd.addStudent(s);
                if (res > 0) {
                System.out.println("Student added successfully!");
                } else {
                System.out.println("Student not added.");
                }
              }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
              }catch(SQLIntegrityConstraintViolationException e){
                System.out.println("Student ID already exists.Please enter a different ID.");
              }catch(SQLException e){
                 System.out.println("Database error: Unable to complete the operation.");
              }
              break;
            case 2:
                sd.viewAllStudents();
                break;
            case 3: 
                System.out.println("enter search id:");
                String searchid=sc.nextLine();
                int ide=Integer.parseInt(searchid);   
                sd.searchStudent(ide);
                break;
            case 4:
                System.out.println("enter student id:");
                String stid=sc.nextLine();
                int sid=Integer.parseInt(stid);
                System.out.println("enter name:");
                String nam=sc.nextLine();
                System.out.println("enter age:");
                String stage=sc.nextLine();
                int a=Integer.parseInt(stage);
                System.out.println("enter course:");
                String cou=sc.nextLine();
                System.out.println("enter marks:");
                String stm=sc.nextLine();
                double mr=Double.parseDouble(stm);
                try{
                    new Student(sid, nam, a, cou, mr);
                    sd.updateStudent(sid,nam,a, cou, mr);
                    sd.viewAllStudents();
                }catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 5:
                System.out.println("Enter student ID to delete");
                String deleteid=sc.nextLine();
                int iden=Integer.parseInt(deleteid);
                sd.deleteStudent(iden);
                break;
            case 6:    
                System.out.println("Exiting Student Management System...");
                running=false; 
                break;       
            default:    
                System.out.println("Invalid choice! Please enter a number between 1 and 6.");
          }
        }catch(NumberFormatException e){
            System.out.println("Please enter a valid number!");
        }catch(SQLException e){
            System.out.println("Database error: Unable to complete the operation.");
        }
    }
    sc.close();
}
}
