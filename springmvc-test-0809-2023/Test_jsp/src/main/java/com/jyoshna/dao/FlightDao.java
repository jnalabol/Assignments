package com.jyoshna.dao;

import com.jyoshna.entity.Flight;
import com.jyoshna.entity.Passengers;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository
public class FlightDao {
    private static String url = "jdbc:mysql://localhost:3306/Gainsight";
    Connection con = null;
    PreparedStatement p = null;
    int count = 0;

    public ArrayList<Flight> searchFlight(String source, String destination) throws ClassNotFoundException {
        ArrayList<Flight> listOfFlights = new ArrayList<>();

        System.out.println("hiiiii");

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(url, "root", "Bramaramba@24");
             PreparedStatement preparedStatement = con.prepareStatement("select * from flight where source=? and destination = ?")) {
             preparedStatement.setString(1, source);
             preparedStatement.setString(2, destination);
             ResultSet rs = preparedStatement.executeQuery();
             while(rs.next()) {
                 Flight flight = new Flight(rs.getString(1),rs.getString(2),rs.getString(3),
                         rs.getDouble(4),rs.getInt(5));
                 listOfFlights.add(flight);
             }
            System.out.println(listOfFlights.get(0).getFlightId()+"   hello");
        } catch (Exception e) {

        }finally {
            try{
                if(p!=null)p.close();
                if(con!=null)con.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        return listOfFlights;

    }
    public Flight getFlightById(String flightId) {
        Flight flight = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,"root","");
            pst = conn.prepareStatement("select * from Flight where flight_id = ?");
            pst.setString(1, flightId);


            rs = pst.executeQuery();
            if(rs.next())
                flight = new Flight(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getInt(5));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs!=null) rs.close();
                if(pst!=null) pst.close();
                if(conn!=null) conn.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        return flight;
    }

    public boolean bookFlight(String flightId, Passengers passenger, String travelDate) {
        Connection conn = null;
        Connection conn2 = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        int count=0, count1 = 0;
        String bookingId = "BookingId" + Math.random();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,"root","Bramaramba@123");
            pst = conn.prepareStatement("insert into passengers values(?,?,?,?,?)");
            pst.setString(1, passenger.getPassengerId());
            pst.setString(2, passenger.getFirstName());
            pst.setString(3, passenger.getLastName());
            pst.setLong(4, passenger.getMobile());
            pst.setString(5, passenger.getEmail());

            count = pst.executeUpdate();

            Class.forName("com.mysql.jdbc.Driver");
            conn2 = DriverManager.getConnection(url,"root","Bramaramba@123");
            pst2 = conn2.prepareStatement("insert into bookings values(?,?,?,?)");
            pst2.setString(1, bookingId);
            pst2.setString(2, flightId);
            pst2.setString(3, passenger.getPassengerId());
            pst2.setString(4, travelDate);
            count1 = pst2.executeUpdate();



        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(pst!=null) pst.close();
                if(pst2!=null) pst2.close();
                if(conn!=null) conn.close();
                if(conn2!=null) conn2.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return count==1 && count1==1;
    }
}
