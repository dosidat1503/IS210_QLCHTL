package DAO_Hibernate;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.Query;

import DTO.NguoiDungDTO;

public class NguoiDungDAO {

    Session session;

    /**
     * Lấy thông tin từ Database
     *
     * @param condition
     * @param orderBy
     * @return Danh sách Người dùng
     */
    public List<NguoiDungDTO> readDB(String condition, String orderBy) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<NguoiDungDTO> nguoiDungDTOs = null;

        try {
            String queryString = "from NguoiDungDTO";

            if (condition != null && !condition.isEmpty()) {
                queryString += " where " + condition;
            }

            if (orderBy != null && !orderBy.isEmpty()) {
                queryString += " order by " + orderBy;
            }

            Query<NguoiDungDTO> query = session.createQuery(queryString, NguoiDungDTO.class);
            nguoiDungDTOs = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return nguoiDungDTOs;
    }

    /**
     * Override lại phương thức readDB() cho TH truyền vào một tham số Condition
     *
     * @param condition
     * @return readDB(condition, null)
     */
    public List<NguoiDungDTO> readDB(String condition) {
        return readDB(condition, null);
    }

    /**
     * Override lại phương thức readDB() cho TH không truyền vào tham số
     *
     * @return readDB(null, null)
     */
    public List<NguoiDungDTO> readDB() {
        return readDB(null, null);
    }

    /**
     * Thêm một Người dùng mới đã có thông tin vào CSDL
     *
     * @param nguoidung
     * @return True nếu thành công
     * @throws Exception và rollback Transaction
     */
    public boolean them(NguoiDungDTO nguoidung) {
        boolean result = false;
        session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            NguoiDungDTO newNguoiDung = new NguoiDungDTO();
            newNguoiDung.setMaNVString(nguoidung.getMaNVString());
            newNguoiDung.setUsernameString(nguoidung.getUsernameString());
            newNguoiDung.setPasswordString(nguoidung.getPasswordString());

            session.save(newNguoiDung);
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
     * Thêm một Người dùng mới đã có thông tin vào CSDL
     *
     * @param nguoidung
     * @return True nếu thành công
     * @throws Exception và rollback Transaction
     */
    public boolean them_optimized(NguoiDungDTO nguoidung) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(nguoidung);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Xóa một Người dùng khỏi CSDL
     *
     * @param nguoidung
     * @return True nếu thành công
     * @throws Exception và rollback Transaction
     */
    public boolean xoa(NguoiDungDTO nguoidung) {
        boolean result = false;
        session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.delete(nguoidung); // xóa đối tượng NguoiDungDTO khỏi database

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
     * Sửa một Người dùng có trong CSDL
     *
     * @param nguoidung
     * @return
     * @throws Exception
     */
    public boolean sua(NguoiDungDTO nguoidung) {
        boolean result = false;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.update(nguoidung); // sửa thông tin đối tượng NguoiDungDTO trong database

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

    public void insertTaiKhoan(NguoiDungDTO nguoiDungDTO) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        ProcedureCall procedureCall = session.createStoredProcedureCall("INSERT_TAIKHOAN");

        // Đăng ký các tham số và thiết lập giá trị
        procedureCall.registerParameter("USR", String.class, ParameterMode.IN)
                .bindValue(nguoiDungDTO.getUsernameString());
        procedureCall.registerParameter("PASS", String.class, ParameterMode.IN)
                .bindValue(nguoiDungDTO.getPasswordString());
        procedureCall.registerParameter("MNV", String.class, ParameterMode.IN).bindValue(nguoiDungDTO.getMaNVString());

        // Thực hiện stored procedure
        // ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
        // ResultSetOutput resultSetOutput = (ResultSetOutput)
        // procedureOutputs.getCurrent();
        procedureCall.execute();

        session.getTransaction().commit();
    }

    public void deleteTaiKhoan(String username) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        ProcedureCall procedureCall = session.createStoredProcedureCall("DELETE_TK");

        // Đăng ký các tham số và thiết lập giá trị
        procedureCall.registerParameter("MTK", String.class, ParameterMode.IN).bindValue(username);

        // Thực hiện stored procedure
        // ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
        // ResultSetOutput resultSetOutput = (ResultSetOutput)
        // procedureOutputs.getCurrent();
        procedureCall.execute();

        session.getTransaction().commit();
    }

    /**
     * Tìm kiếm một người dùng dựa trên maND cho trước
     *
     * @param maND
     * @return null nếu không tìm thấy
     */
    public NguoiDungDTO findByUsername(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query<NguoiDungDTO> query = session.createQuery("FROM NguoiDungDTO WHERE maNVString LIKE :searchKeyword",
                NguoiDungDTO.class);
        query.setParameter("searchKeyword", "%" + username + "%");

        NguoiDungDTO nguoiDung = query.uniqueResult();

        session.getTransaction().commit();
        return nguoiDung;
    }

    public NguoiDungDTO findByMaNV(String MaNV) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String hql = "FROM NguoiDungDTO WHERE NguoiDungDTO.maNV = :MaNV";
        Query<NguoiDungDTO> query = session.createQuery(hql, NguoiDungDTO.class);
        query.setParameter("MaNV", MaNV);
        NguoiDungDTO nguoiDungDTO = query.uniqueResult();

        session.getTransaction().commit();

        return nguoiDungDTO;
    }

    public NguoiDungDTO tim(String keyword) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query<NguoiDungDTO> query = session
                .createQuery("FROM NguoiDungDTO WHERE MaNV = :keyword OR username = :searchKeyword",
                        NguoiDungDTO.class);
        query.setParameter("keyword", keyword);
        query.setParameter("searchKeyword", keyword);

        NguoiDungDTO nguoiDung = query.uniqueResult();

        session.getTransaction().commit();
        return nguoiDung;
    }

}
