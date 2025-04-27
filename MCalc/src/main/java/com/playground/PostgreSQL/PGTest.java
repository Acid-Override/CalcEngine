package com.playground.PostgreSQL;

import java.sql.*;

public class PGTest {


        public static void main(String[] args) throws SQLException {
            try (
                    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo", "jupiter", "");
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(
                            "SELECT " +
                                    "* " +
                                    "FROM " +
                                    "public.customers c " +
                                    "Join " +
                                    "public.orders o " +
                                    "on " +
                                    "c.customer_id = o.customer_id"
                    )
            ) {

                try {
                    while (resultSet.next()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(resultSet.getString("customer_id"));
                        sb.append(", ");
                        sb.append(resultSet.getString("name"));
                        sb.append(", ");
                        sb.append(resultSet.getString("order_id"));
                        System.out.println(sb.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
}
