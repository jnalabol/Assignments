package org.example;

import java.sql.*;

public class OrderedProductsDao {
    Product pr = null;
    Orders o =null;

    private static String url = "jdbc:mysql://localhost:3306/Gainsight";
    public void displayOrderedProductById(int productId){
        try (Connection con = DriverManager.getConnection(url, "root", "Bramaramba@24");
            PreparedStatement p = con.prepareStatement("select P.prod_id, prod_name,prod_desc,prod_price,prod_quantity from Product P, ordered_products Op, orders O where P.prod_id = Op.prod_id and Op.order_id = O.order_id and P.prod_id = ?")){
                p.setInt(1,pr.getProd_id());
                ResultSet rs = p.executeQuery();

                while(rs.next()){
                    System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getDouble(4)+ " " +rs.getInt(5));
                }

            }
        catch (Exception e){
                e.printStackTrace();
            }
        }
        public void displayOrderedProductsByOrderDate(String orderDate){
            try(Connection con = DriverManager.getConnection(url, "root", "Bh@rgav2811");
                PreparedStatement p = con.prepareStatement("select P.prod_id, prod_name,prod_desc,prod_price,prod_quantity,O.order_date from Product P, ordered_products Op, orders O where P.prod_id = Op.prod_id and Op.order_id = O.order_id and O.order_date = ?")){
                p.setString(1,o.getOrder_date());
                ResultSet rs = p.executeQuery();
                while(rs.next()){
                    System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getDouble(4)+ " " +rs.getInt(5)+" " +rs.getString(6));
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
