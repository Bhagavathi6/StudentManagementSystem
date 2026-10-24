package StudentManagementSystem;

public class Student {
    private int id;
    private String name;
    private int age;
    private String course;
    private double marks;
    public Student(int id, String name, int age, String course, double marks){
        setId(id);
        setName(name);
        setAge(age);
        setCourse(course);
        setMarks(marks);
    }
    public void setId(int id){
        if(id>0){
            this.id=id;
        }else{
            throw new IllegalArgumentException("Student id must be positive");
        }
    }
    public void setName(String name){
        if((!name.isBlank())){
            this.name=name;
        }else{
            throw new IllegalArgumentException("name cannot be empty");
        }
    }
    public void setAge(int age){
        if(age>0){
            this.age=age;
        }else{
            throw new IllegalArgumentException("Student age must be positive");
        }
    }
    public void setCourse(String course){
        if((!course.isBlank())){
            this.course=course;
        }else{
            throw new IllegalArgumentException("Course cannot be empty");
        }
    }
    public void setMarks(double marks){
        if(marks>=0 && marks<=100){
            this.marks=marks;
        }else{
            throw new IllegalArgumentException("Marks must be between 0 to 100");
        }
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public String getCourse(){
        return course;
    }
    public double getMarks(){
        return marks;
    }
    @Override 
    public String toString(){
        String st="ID:"+id+",Name:"+name+",Age:"+age+",Course:"+course+",Marks:"+marks;
        return st;
    }
}
