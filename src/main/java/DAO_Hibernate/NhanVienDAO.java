package DAO_Hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.Query;

import DTO.NhanVienDTO;

public class NhanVienDAO {

    Session session;

    /**
     * Lấy thông tin từ Database
     *
     * @param condition
     * @param orderBy
     * @return Danh sách Nhân viên
     */
    public List<NhanVienDTO> readDB(String condition, String orderBy) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<NhanVienDTO> nhanVienDTOs = null;

        try {
            String queryString = "from NhanVienDTO";

            if (condition != null && !condition.isEmpty()) {
                queryString += " where " + condition;
            }

            if (orderBy != null && !orderBy.isEmpty()) {
                queryString += " order by " + orderBy;
            }

            Query<NhanVienDTO> query = session.createQuery(queryString, NhanVienDTO.class);
            nhanVienDTOs = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return nhanVienDTOs;
    }

    /**
     * Override lại phương thức readDB() cho TH truyền vào một tham số Condition
     *
     * @param condition
     * @return readDB(condition, null)
     */
    public List<NhanVienDTO> readDB(String condition) {
        return readDB(condition, null);
    }

    /**
     * Override lại phương thức readDB() cho TH không truyền vào tham số
     *
     * @return readDB(null, null)
     */
    public List<NhanVienDTO> readDB() {
        return readDB(null, null);
    }

    /**
     * Thêm một Nhân viên mới đã có thông tin vào CSDL
     *
     * @param nhanVienDTO
     * @return True nếu thành công
     */
    public boolean them(NhanVienDTO nhanVienDTO) {
        boolean result = false;
        session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            NhanVienDTO newNhanVien = new NhanVienDTO();

            newNhanVien.setMaNVString(nhanVienDTO.getMaNVString());
            newNhanVien.setHoTenNVString(nhanVienDTO.getHoTenNVString());
            newNhanVien.setHoTenNVString(nhanVienDTO.getHoTenNVString());
            newNhanVien.setSDTNVString(nhanVienDTO.getSDTNVString());
            newNhanVien.setCCCDNVString(nhanVienDTO.getCCCDNVString());
            newNhanVien.setDiaChiNVString(nhanVienDTO.getDiaChiNVString());
            newNhanVien.setChucVuNVString(nhanVienDTO.getChucVuNVString());
            newNhanVien.setNgaySinhNVDate(nhanVienDTO.getNgaySinhNVDate());

            session.save(newNhanVien);
            session.getTransaction().commit();
            result = true;

        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return result;
    }

    /**
     * Thêm một Nhân viên mới đã có thông tin vào CSDL
     *
     * @param nhanvien
     * @return True nếu thành công
     * @throws Exception và rollback Transaction
     */
    public boolean them_optimized(NhanVienDTO nhanvien) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(nhanvien);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Xóa một Nhân viên khỏi CSDL
     *
     * @param nhanVienDTO
     * @return True nếu thành công
     * @throws Exception và rollback Transaction
     */
    public boolean xoa(NhanVienDTO nhanVienDTO) {
        boolean result = false;
        session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.delete(nhanVienDTO); // xóa đối tượng NhanVienDTO khỏi database

            transaction.commit(); // commit transaction
            result = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // rollback transaction nếu có lỗi xảy ra
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return result;
    }

    /**
     * Sửa một Nhân viên có trong CSDL
     *
     * @param nhanVienDTO
     * @return
     * @throws Exception
     */
    public boolean sua(NhanVienDTO nhanVienDTO) {
        boolean result = false;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.update(nhanVienDTO); // sửa thông tin đối tượng NhanVienDTO trong database

            transaction.commit(); // commit transaction
            result = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // rollback transaction nếu có lỗi xảy ra
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return result;
    }

    public NhanVienDTO tim(String keyword) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query<NhanVienDTO> query = session
                .createQuery("FROM NhanVienDTO WHERE MaNV = :keyword OR TenNV LIKE :searchKeyword", NhanVienDTO.class);
        query.setParameter("keyword", keyword);
        query.setParameter("searchKeyword", "%" + keyword + "%");

        NhanVienDTO sanPham = query.uniqueResult();

        session.getTransaction().commit();
        return sanPham;
    }

    public void insertNhanVien(NhanVienDTO nhanVienDTO) throws Exception {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        ProcedureCall procedureCall = session.createStoredProcedureCall("INSERT_NV");

        // Đăng ký các tham số và thiết lập giá trị
        procedureCall.registerParameter("MNV", String.class, ParameterMode.IN).bindValue(nhanVienDTO.getMaNVString());
        procedureCall.registerParameter("TEN", String.class, ParameterMode.IN).bindValue(nhanVienDTO.getHoTenNVString());
        procedureCall.registerParameter("SDT", String.class, ParameterMode.IN).bindValue(nhanVienDTO.getSDTNVString());
        procedureCall.registerParameter("NS", Date.class, ParameterMode.IN).bindValue(nhanVienDTO.getNgaySinhNVDate());
        procedureCall.registerParameter("CCCD", String.class, ParameterMode.IN).bindValue(nhanVienDTO.getCCCDNVString());
        procedureCall.registerParameter("DC", String.class, ParameterMode.IN).bindValue(nhanVienDTO.getDiaChiNVString());
        procedureCall.registerParameter("CV", String.class, ParameterMode.IN).bindValue(nhanVienDTO.getChucVuNVString());
        procedureCall.registerParameter("L", Integer.class, ParameterMode.IN).bindValue(nhanVienDTO.getLuongInteger());
        procedureCall.registerParameter("NVL", Date.class, ParameterMode.IN).bindValue(nhanVienDTO.getNgayVaoLamDate());

        // Thực hiện stored procedure
        // ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
        // ResultSetOutput resultSetOutput = (ResultSetOutput)
        // procedureOutputs.getCurrent();
        procedureCall.execute();

        session.getTransaction().commit();
    }

    public void updateNhanVien(NhanVienDTO nhanVienDTO) throws Exception {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        ProcedureCall procedureCall = session.createStoredProcedureCall("UPDATE_NV");

        // Đăng ký các tham số và thiết lập giá trị
        procedureCall.registerParameter("MNV", String.class, ParameterMode.IN).bindValue(nhanVienDTO.getMaNVString());
        procedureCall.registerParameter("TEN", String.class, ParameterMode.IN).bindValue(nhanVienDTO.getHoTenNVString());
        procedureCall.registerParameter("SDT", String.class, ParameterMode.IN).bindValue(nhanVienDTO.getSDTNVString());
        procedureCall.registerParameter("NS", Date.class, ParameterMode.IN).bindValue(nhanVienDTO.getNgaySinhNVDate());
        procedureCall.registerParameter("CCCDI", String.class, ParameterMode.IN).bindValue(nhanVienDTO.getCCCDNVString());
        procedureCall.registerParameter("DC", String.class, ParameterMode.IN).bindValue(nhanVienDTO.getDiaChiNVString());
        procedureCall.registerParameter("CV", String.class, ParameterMode.IN).bindValue(nhanVienDTO.getChucVuNVString());
        procedureCall.registerParameter("L", Integer.class, ParameterMode.IN).bindValue(nhanVienDTO.getLuongInteger());
        procedureCall.registerParameter("NVL", Date.class, ParameterMode.IN).bindValue(nhanVienDTO.getNgayVaoLamDate());

        // Thực hiện stored procedure
        // ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
        // ResultSetOutput resultSetOutput = (ResultSetOutput)
        // procedureOutputs.getCurrent();
        procedureCall.execute();

        session.getTransaction().commit();
    }

    public void deleteNhanVien(String ma) throws SQLException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            ProcedureCall procedureCall = session.createStoredProcedureCall("DELETE_NV");

            // Đăng ký các tham số và thiết lập giá trị
            procedureCall.registerParameter("MNV", String.class, ParameterMode.IN).bindValue(ma);

            // Thực hiện stored procedure
            procedureCall.execute();

            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw new SQLException("Error executing deleteNhanVien: " + e.getMessage(), e);
        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw new SQLException("Error executing deleteNhanVien: " + e.getMessage(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<String> getMaNVList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query<String> query = session.createQuery("SELECT s.maNVString FROM NhanVienDTO s", String.class);
        List<String> maNVList = query.getResultList();

        session.getTransaction().commit();

        return maNVList;
    }

    public String AutoGenerateMaNV() {
        // Lấy danh sách MaNV trong CSDL
        List<String> maNVList = new ArrayList<>();
        maNVList = getMaNVList();
        List<Integer> numberList = new ArrayList<>();
        // Tách riêng phần số trong MaNV ra
        for (String maNV : maNVList) {
            // Xóa tất cả các ký tự không phải số từ chuỗi
            String numberString = maNV.replaceAll("[^\\d]", "");
            numberList.add(Integer.parseInt(numberString));
        }
        // Tạo MaNV mới theo định dạng "NVxxxxxx"
        String MaNV = "NV" + String.format("%06d", Collections.max(numberList) + 1);
        return MaNV;
    }
}
