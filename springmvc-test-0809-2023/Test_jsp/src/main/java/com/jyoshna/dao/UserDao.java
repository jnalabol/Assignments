package com.jyoshna.dao;

import com.jyoshna.entity.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class UserDao {

    private static String url = "jdbc:mysql://localhost:3306/Gainsight";

    public boolean validateUser(User user) {

        int count = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception ex1) { System.out.println(ex1);}
        try (
                Connection con = DriverManager.getConnection(url, "root", "Bramaramba@24");
                PreparedStatement preparedStatement = con.prepareStatement("select count(*) from users where username=? and password = ?")) {


            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }


        } catch (Exception e) {

        }

        return count == 1;

    }
}
