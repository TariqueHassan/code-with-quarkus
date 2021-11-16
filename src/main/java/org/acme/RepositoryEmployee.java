package org.acme;

import io.agroal.api.AgroalDataSource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public ArrayList<Student> getAllStudent(){
        ArrayList<Student> studentsList = new ArrayList<>();
        String sql = "select * from student";
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                studentsList.add(new Student(resultSet.getString(1), resultSet.getString(2)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return studentsList;
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
