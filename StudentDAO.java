package StudentManagementSystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class StudentDAO{
        Connection connectToDatabase() throws SQLException{
            Properties properties = new Properties();
            try (FileInputStream fis = new FileInputStream("StudentManagementSystem/db.properties")) {
                properties.load(fis);
            } catch (IOException e) {
                throw new SQLException("Unable to load db.properties", e);
            }
            String databaseUrl = properties.getProperty("db.url");
            String userName = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            return DriverManager.getConnection(databaseUrl, userName, password);
        }
        int addStudent(Student s) throws SQLException{
            String sqlQuery="INSERT INTO Student VALUES (?,?,?,?,?)";
            int r=0;
            try(Connection c=connectToDatabase();
            PreparedStatement ps=c.prepareStatement(sqlQuery)){
                ps.setInt(1,s.getId());
                ps.setString(2,s.getName());
                ps.setInt(3,s.getAge());
                ps.setString(4,s.getCourse());
                ps.setDouble(5,s.getMarks());
                r=ps.executeUpdate();
            }
            return r;
        }
        void viewAllStudents()throws SQLException{
            String sqlQuery="SELECT * FROM STUDENT";
            try(Connection c=connectToDatabase();
                PreparedStatement ps=c.prepareStatement(sqlQuery);
                ResultSet rs=ps.executeQuery()){
                if(!rs.next()){
                   System.out.println("No student found");
                }else{
                    do{
                      int id=rs.getInt("id");
                      System.out.println("Student id:"+id);
                      String n=rs.getString("name");
                      System.out.println("Student Name:"+n);
                      int ag=rs.getInt("age");
                      System.out.println("Student age:"+ag);
                      String cor=rs.getString("course");
                      System.out.println("Student course:"+cor);
                      double m=rs.getDouble("marks");
                      System.out.println("Student Marks:"+m);
                      System.out.println();
                    }while(rs.next());
                }
            }
        }
        void searchStudent(int ide)throws SQLException{
            String sqlQuery="SELECT * FROM Student WHERE id=?";
            try(Connection c=connectToDatabase();
                PreparedStatement ps=c.prepareStatement(sqlQuery)){
                ps.setInt(1,ide);
                try(ResultSet rs=ps.executeQuery()){
                    if(rs.next()){
                      System.out.println("Student id:"+ide);
                      String n=rs.getString("name");
                      System.out.println("Student Name:"+n);
                      int ag=rs.getInt("age");
                      System.out.println("Student age:"+ag);
                      String cor=rs.getString("course");
                      System.out.println("Student course:"+cor);
                      double m=rs.getDouble("marks");
                      System.out.println("Student Marks:"+m);
                      System.out.println();
                    }else{
                       System.out.println("No student has that ID:"+ide);
                    }
                }
            }
        }
        void updateStudent(int sid,String name,int a,String cou,double mr)throws SQLException{
            String sqlQuery="UPDATE Student SET name=?,age=?,course=?,marks=? WHERE id=?";
            try(Connection c=connectToDatabase();
                PreparedStatement ps=c.prepareStatement(sqlQuery)){
                    ps.setString(1,name);
                    ps.setInt(2,a);
                    ps.setString(3,cou);
                    ps.setDouble(4, mr);
                    ps.setInt(5,sid);
                    int r=ps.executeUpdate();
                    if(r>0){
                        System.out.println("Student updated successfully!");
                    }else{
                        System.out.println("student not updated");
                    }
                }
        }
        void deleteStudent(int iden)throws SQLException{
            String sqlQuery="DELETE from Student where id=?";
            try(Connection c=connectToDatabase();
                PreparedStatement ps=c.prepareStatement(sqlQuery)){
                    ps.setInt(1,iden);
                    int r=ps.executeUpdate();
                    if(r>0){
                        System.out.println("Student deleted successfully!");
                    }else{
                        System.out.println("No student deleted");
                    }
                }
        }
}