package org.acme;

import io.agroal.api.AgroalDataSource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;

@ApplicationScoped
public class RepositoryEmployee {

    @Inject
    AgroalDataSource agr;

    public Connection getConnection(){
        Connection connection = null;
        try {
//            connection = DriverManager.getConnection("jdbc:mysql://172.17.0.3:3306/testdb", "root","123");
            connection=agr.getConnection();
        }catch (Exception e){
            System.out.println("Error: "+e);
        }
        System.out.println("Newss Connection: "+connection);

        return connection;
    }

    public Student getStudentById(int id){
        System.out.println("id"+id);
        String sql = "select * from student where id=?";
        Student student = null;
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            // int id, String name, int age, String gender, String address
            System.out.println("result"+result);
            student = new Student();
            while (result.next()) {
                student.setId(result.getInt(1));
                student.setName(result.getString(2));
                student.setEmail(result.getString(3));
            }
        } catch (Exception ae) {
            ae.printStackTrace();
        }
        return student;
    }


    public ArrayList<Student> getAllStudent(){
        ArrayList<Student> studentsList = new ArrayList<>();
        String sql = "select * from student";
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                studentsList.add(new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return studentsList;
    }

    public void updateStudent(int id, String email){

        String sql = "Update student set email=? where id=?";
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, email);
            statement.setInt(2, id);
            statement.executeUpdate();
            System.out.println("id: "+id+"  email: "+email);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id){
        String sql = "Delete from student where id=?";
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void setStudent(Student student){
        String sql = "INSERT INTO student (name, email) values (?, ?)";
        try {
            System.out.println("Student name: "+student.getName());
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            int rows = statement.executeUpdate();
            if(rows>0) System.out.println("New Student inserted into student table");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
