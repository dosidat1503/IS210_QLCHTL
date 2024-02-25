package DAO_Hibernate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;

public class CallProcedure {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String username = "ConvinienceStore";
        String password = "conviniencestore";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            CallableStatement cstmt = conn.prepareCall("{call TOP10SP_BANCHAY(?, ?, ?) }");
            cstmt.setString(1, "01-JAN-23");
            cstmt.setString(2, "31-DEC-23");
            cstmt.registerOutParameter(3, Types.REF_CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(3);
            while (rs.next()) {
                String maSP = rs.getString(1);
                String tenSP = rs.getString(2);
                int soLuong = rs.getInt(3);
                System.out.println("Mã sản phẩm: " + maSP + ", Tên sản phẩm: " + tenSP + ", Số lượng: " + soLuong);
            }
            rs.close();
            cstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
