package DAO_Hibernate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.KhoDTO;
import DTO.NguoiDungDTO;
import DTO.NhanVienDTO;
import DTO.SanPhamDTO;

public class TestProcedure {

    String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    String username = "ConvinienceStore";
    String password = "conviniencestore";

    public List<SanPhamDTO> SEARCH_SANPHAM(String keyword) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            CallableStatement cstmt = conn.prepareCall("{call SEARCH_SANPHAM(?, ?) }");
            cstmt.setString(1, keyword);
            cstmt.registerOutParameter(2, Types.REF_CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(2);
            List<SanPhamDTO> sanPhamDTOs = new ArrayList<>();
            while (rs.next()) {
                SanPhamDTO sanPhamDTO = new SanPhamDTO();
                sanPhamDTO.setMaSPString(rs.getString(1));
                sanPhamDTO.setTenSPString(rs.getString(2));
                sanPhamDTO.setGiaInt(rs.getInt(3));
                sanPhamDTO.setSoLuongSPInt(rs.getInt(4));
                sanPhamDTO.setPhanLoaiString(rs.getString(5));
                sanPhamDTO.setNSXDate(rs.getDate(6));
                sanPhamDTO.setHSDDate(rs.getDate(7));
                sanPhamDTO.setMoTaString(rs.getString(8));
                sanPhamDTOs.add(sanPhamDTO);
            }
            rs.close();
            cstmt.close();

            return sanPhamDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void PL_SANPHAM() {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            CallableStatement cstmt = conn.prepareCall("{call PL_SANPHAM(?, ?) }");
            cstmt.setString(1, "Do an vat");
            cstmt.registerOutParameter(2, Types.REF_CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(2);
            while (rs.next()) {
                String maSP = rs.getString(1);
                String tenSP = rs.getString(2);
                System.out.println("Ma san pham: " + maSP + ", Ten SP: " + tenSP);
            }
            rs.close();
            cstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<KhachHangDTO> SEARCH_KHACHHANG(String keyword) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            CallableStatement cstmt = conn.prepareCall("{call SEARCH_KHACHHANG(?, ?) }");
            cstmt.setString(1, keyword);
            cstmt.registerOutParameter(2, Types.REF_CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(2);
            List<KhachHangDTO> khachHangDTOs = new ArrayList<>();
            while (rs.next()) {
                KhachHangDTO khachHangDTO = new KhachHangDTO();
                khachHangDTO.setMaKHString(rs.getString(1));
                khachHangDTO.settenKHString(rs.getString(2));
                khachHangDTO.setSDTKHString(rs.getString(3));
                khachHangDTO.setNgayDangKyDate(rs.getDate(4));
                khachHangDTOs.add(khachHangDTO);
            }
            rs.close();
            cstmt.close();

            return khachHangDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<HoaDonDTO> SEARCH_HOADON(String keyword) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            CallableStatement cstmt = conn.prepareCall("{call SEARCH_HOADON(?, ?) }");
            cstmt.setString(1, keyword);
            cstmt.registerOutParameter(2, Types.REF_CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(2);
            List<HoaDonDTO> hoaDonDTOs = new ArrayList<>();
            while (rs.next()) {
                HoaDonDTO hoaDonDTO = new HoaDonDTO();
                hoaDonDTO.setMaHDString(rs.getString(1));
                hoaDonDTO.setMaNVString(rs.getString(2));
                hoaDonDTO.setMaKHString(rs.getString(3));
                hoaDonDTO.setTriGiaHDInteger(rs.getInt(4));
                hoaDonDTO.setNgayMuaHangHDDate(rs.getDate(5));
                hoaDonDTO.setHinhThucThanhToanHDString(rs.getString(6));
                hoaDonDTOs.add(hoaDonDTO);
            }
            rs.close();
            cstmt.close();

            return hoaDonDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<KhoDTO> SEARCH_LOHANG(String keyword) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            CallableStatement cstmt = conn.prepareCall("{call SEARCH_LOHANG(?, ?) }");
            cstmt.setString(1, keyword);
            cstmt.registerOutParameter(2, Types.REF_CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(2);
            List<KhoDTO> khoDTOs = new ArrayList<>();
            while (rs.next()) {
                KhoDTO khoDTO = new KhoDTO();
                khoDTO.setMaLoHangString(rs.getString(1));
                khoDTO.setMaNhanVienString(rs.getString(2));
                khoDTO.setNgayNhapDate(rs.getDate(3));
                khoDTO.setTenNhaCungCapString(rs.getString(4));
                khoDTOs.add(khoDTO);
            }
            rs.close();
            cstmt.close();

            return khoDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<NhanVienDTO> SEARCH_NHANVIEN(String keyword) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            CallableStatement cstmt = conn.prepareCall("{call SEARCH_NHANVIEN(?, ?) }");
            cstmt.setString(1, keyword);
            cstmt.registerOutParameter(2, Types.REF_CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(2);
            List<NhanVienDTO> nhanVienDTOs = new ArrayList<>();
            while (rs.next()) {
                NhanVienDTO nhanVienDTO = new NhanVienDTO();
                nhanVienDTO.setMaNVString(rs.getString(1));
                nhanVienDTO.setHoTenNVString(rs.getString(2));
                nhanVienDTO.setSDTNVString(rs.getString(3));
                nhanVienDTO.setNgaySinhNVDate(rs.getDate(4));
                nhanVienDTO.setCCCDNVString(rs.getString(5));
                nhanVienDTO.setDiaChiNVString(rs.getString(6));
                nhanVienDTO.setChucVuNVString(rs.getString(7));
                nhanVienDTO.setLuongInteger(rs.getInt(8));
                nhanVienDTO.setNgayVaoLamDate(rs.getDate(9));
                nhanVienDTOs.add(nhanVienDTO);
            }
            rs.close();
            cstmt.close();

            return nhanVienDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public NguoiDungDTO SEARCH_TAIKHOAN(String maTK) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            CallableStatement cstmt = conn.prepareCall("{call SEARCH_TK(?, ?) }");
            cstmt.setString(1, maTK);
            cstmt.registerOutParameter(2, Types.REF_CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(2);
            NguoiDungDTO nguoiDungDTO = new NguoiDungDTO();
            while (rs.next()) {
                nguoiDungDTO.setUsernameString(rs.getString(1));
                nguoiDungDTO.setPasswordString(rs.getString(2));
                nguoiDungDTO.setMaNVString(rs.getString(3));
            }
            rs.close();
            cstmt.close();

            return nguoiDungDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        TestProcedure testProcedure = new TestProcedure();
        System.out.println(testProcedure.SEARCH_TAIKHOAN(""));
    }
}
