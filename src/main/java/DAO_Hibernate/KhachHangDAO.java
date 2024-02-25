package DAO_Hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.Query;

import DTO.KhachHangDTO;

public class KhachHangDAO {
    Session session;

    /**
     * Lấy thông tin từ Database
     * 
     * @param condition
     * @param orderBy
     * @return Danh sách Khách hàng
     */
    public List<KhachHangDTO> readDB(String condition, String orderBy) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<KhachHangDTO> khachHangDTOs = null;

        try {
            String queryString = "from KhachHangDTO";

            if (condition != null && !condition.isEmpty()) {
                queryString += " where " + condition;
            }

            if (orderBy != null && !orderBy.isEmpty()) {
                queryString += " order by " + orderBy;
            }

            Query<KhachHangDTO> query = session.createQuery(queryString, KhachHangDTO.class);
            khachHangDTOs = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return khachHangDTOs;
    }

    /**
     * Override lại phương thức readDB() cho TH truyền vào một tham số Condition
     * 
     * @param condition
     * @return readDB(condition, null)
     */
    public List<KhachHangDTO> readDB(String condition) {
        return readDB(condition, null);
    }

    /**
     * Override lại phương thức readDB() cho TH không truyền vào tham số
     * 
     * @return readDB(null, null)
     */
    public List<KhachHangDTO> readDB() {
        return readDB(null, null);
    }

    /**
     * Thêm một Khách hàng mới đã có thông tin vào CSDL
     * 
     * @param KhachHangDTO
     * @return True nếu thành công
     * @throws Exception và rollback Transaction
     */
    public boolean them(KhachHangDTO khachHangDTO) throws Exception {
        boolean result = false;
        session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            KhachHangDTO newKhachHang = new KhachHangDTO();

            newKhachHang.setMaKHString(khachHangDTO.getMaKHString());
            newKhachHang.setSDTKHString(khachHangDTO.getSDTKHString());
            newKhachHang.settenKHString(khachHangDTO.gettenKHString());

            session.save(newKhachHang);
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
     * Thêm một Khách hàng mới đã có thông tin vào CSDL
     * 
     * @param khachhang
     * @return True nếu thành công
     * @throws Exception và rollback Transaction
     */
    public boolean them_optimized(KhachHangDTO khachhang) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(khachhang);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Xóa một Khách hàng khỏi CSDL
     * 
     * @param khachHangDTO
     * @return True nếu thành công
     * @throws Exception và rollback Transaction
     */
    public boolean xoa(KhachHangDTO khachHangDTO) throws Exception {
        boolean result = false;
        session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.delete(khachHangDTO); // xóa đối tượng KhachHangDTO khỏi database

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
     * Sửa một Khách hàng có trong CSDL
     * 
     * @param khachHangDTO
     * @return
     * @throws Exception
     */
    public boolean sua(KhachHangDTO khachHangDTO) throws Exception {
        boolean result = false;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.update(khachHangDTO); // sửa thông tin đối tượng KhachHangDTO trong database

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

    public KhachHangDTO tim(String keyword) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query<KhachHangDTO> query = session.createQuery(
                "FROM KhachHangDTO WHERE MaKH = :keyword OR SDTKHString LIKE :searchKeyword", KhachHangDTO.class);
        query.setParameter("keyword", keyword);
        query.setParameter("searchKeyword", "%" + keyword + "%");

        KhachHangDTO sanPham = query.uniqueResult();

        session.getTransaction().commit();
        return sanPham;
    }

    public boolean insertKhachHang(KhachHangDTO khachHangDTO) throws SQLException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            ProcedureCall procedureCall = session.createStoredProcedureCall("INSERT_KHACHHANG");

            // Đăng ký các tham số và thiết lập giá trị
            procedureCall.registerParameter("MA", String.class, ParameterMode.IN)
                    .bindValue(khachHangDTO.getMaKHString());
            procedureCall.registerParameter("TEN", String.class, ParameterMode.IN)
                    .bindValue(khachHangDTO.gettenKHString());
            procedureCall.registerParameter("SDT", String.class, ParameterMode.IN)
                    .bindValue(khachHangDTO.getSDTKHString());

            // Thực hiện stored procedure
            // ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
            // ResultSetOutput resultSetOutput = (ResultSetOutput)
            // procedureOutputs.getCurrent();
            procedureCall.execute();

            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateKhachHang(KhachHangDTO khachHangDTO) throws SQLException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            ProcedureCall procedureCall = session.createStoredProcedureCall("UPDATE_KHACHHANG");

            // Đăng ký các tham số và thiết lập giá trị
            procedureCall.registerParameter("MA", String.class, ParameterMode.IN)
                    .bindValue(khachHangDTO.getMaKHString());
            procedureCall.registerParameter("TEN", String.class, ParameterMode.IN)
                    .bindValue(khachHangDTO.gettenKHString());
            procedureCall.registerParameter("SDT", String.class, ParameterMode.IN)
                    .bindValue(khachHangDTO.getSDTKHString());

            // Thực hiện stored procedure
            // ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
            // ResultSetOutput resultSetOutput = (ResultSetOutput)
            // procedureOutputs.getCurrent();
            procedureCall.execute();

            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteKhachHang(String ma) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            ProcedureCall procedureCall = session.createStoredProcedureCall("DELETE_KHACHHANG");

            // Đăng ký các tham số và thiết lập giá trị
            procedureCall.registerParameter("MA", String.class, ParameterMode.IN).bindValue(ma);

            // Thực hiện stored procedure
            // ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
            // ResultSetOutput resultSetOutput = (ResultSetOutput)
            // procedureOutputs.getCurrent();
            procedureCall.execute();

            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }



    public List<String> getMaKHList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query<String> query = session.createQuery("SELECT s.maKHString FROM KhachHangDTO s", String.class);
        List<String> maKHList = query.getResultList();

        session.getTransaction().commit();

        return maKHList;
    }

    public String AutoGenerateMaKH() {
        // Lấy danh sách MaKH trong CSDL
        List<String> maKHList = new ArrayList<>();
        maKHList = getMaKHList();
        List<Integer> numberList = new ArrayList<>();
        // Tách riêng phần số trong MaKH ra
        for (String maKH : maKHList) {
            // Xóa tất cả các ký tự không phải số từ chuỗi
            String numberString = maKH.replaceAll("[^\\d]", "");
            numberList.add(Integer.parseInt(numberString));
        }
        // Tạo MaKH mới theo định dạng "KHxxxxxx"
        String MaKH = "KH" + String.format("%06d", Collections.max(numberList) + 1);
        return MaKH;
    }
}
