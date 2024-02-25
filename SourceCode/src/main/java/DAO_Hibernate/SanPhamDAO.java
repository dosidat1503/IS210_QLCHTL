package DAO_Hibernate;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.ParameterMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.query.Query;
import DTO.SanPhamDTO;

public class SanPhamDAO {

    Session session;

    /**
     * Lấy thông tin từ Database
     *
     * @param condition
     * @param orderBy
     * @return Danh sách Sản phẩm
     */
    public List<SanPhamDTO> readDB(String condition, String orderBy) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<SanPhamDTO> sanPhamDTOs = null;

        try {
            String queryString = "from SanPhamDTO";

            if (condition != null && !condition.isEmpty()) {
                queryString += " where " + condition;
            }

            if (orderBy != null && !orderBy.isEmpty()) {
                queryString += " order by " + orderBy;
            }

            Query<SanPhamDTO> query = session.createQuery(queryString, SanPhamDTO.class);
            sanPhamDTOs = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return sanPhamDTOs;
    }

    /**
     * Override lại phương thức readDB() cho TH truyền vào một tham số Condition
     *
     * @param condition
     * @return readDB(condition, null)
     */
    public List<SanPhamDTO> readDB(String condition) {
        return readDB(condition, null);
    }

    /**
     * Override lại phương thức readDB() cho TH không truyền vào tham số
     *
     * @return readDB(null, null)
     */
    public List<SanPhamDTO> readDB() {
        return readDB(null, null);
    }

    /**
     * Thêm một Sản phẩm mới đã có thông tin vào CSDL
     *
     * @param sanpham
     * @return True nếu thành công
     * @throws Exception và rollback Transaction
     */
    public boolean them(SanPhamDTO sanpham) {
        boolean result = false;
        session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            SanPhamDTO newSanPham = new SanPhamDTO();
            newSanPham.setMaSPString(sanpham.getMaSPString());
            newSanPham.setTenSPString(sanpham.getTenSPString());
            newSanPham.setGiaInt(sanpham.getGiaInt());
            newSanPham.setSoLuongSPInt(sanpham.getSoLuongSPInt());
            newSanPham.setPhanLoaiString(sanpham.getPhanLoaiString());
            newSanPham.setNSXDate(sanpham.getNSXDate());
            newSanPham.setHSDDate(sanpham.getHSDDate());
            newSanPham.setMoTaString(sanpham.getMoTaString());
            // newSanPham.setThongTinChiTietString(sanpham.getThongTinChiTietString());
            // newSanPham.setGiaGiamInt(sanpham.getGiaGiamInt());

            session.save(newSanPham);
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
     * Thêm một Sản phẩm mới đã có thông tin vào CSDL
     *
     * @param sanpham
     * @return True nếu thành công
     * @throws Exception và rollback Transaction
     */
    public boolean them_optimized(SanPhamDTO sanpham) throws Exception {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(sanpham);
        session.getTransaction().commit();
        return true;
    }

    /**
     * Xóa một Sản phẩm khỏi CSDL
     *
     * @param sanpham
     * @return True nếu thành công
     * @throws Exception và rollback Transaction
     */
    public boolean xoa(SanPhamDTO sanpham) {
        boolean result = false;
        session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.delete(sanpham); // xóa đối tượng SanPhamDTO khỏi database

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
     * Sửa một Sản phẩm có trong CSDL
     *
     * @param sanpham
     * @return
     * @throws Exception
     */
    public boolean sua(SanPhamDTO sanpham) throws Exception {
        boolean result = false;
        Transaction transaction = null;

        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        session.update(sanpham); // sửa thông tin đối tượng SanPhamDTO trong database

        transaction.commit(); // commit transaction
        result = true;

        return result;
    }

    public SanPhamDTO tim(String keyword) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query<SanPhamDTO> query = session
                .createQuery("FROM SanPhamDTO WHERE MaSP = :keyword OR TenSP LIKE :searchKeyword", SanPhamDTO.class);
        query.setParameter("keyword", keyword);
        query.setParameter("searchKeyword", "%" + keyword + "%");

        SanPhamDTO sanPham = query.uniqueResult();

        session.getTransaction().commit();
        session.close();
        return sanPham;
    }

    public void insertSanPham(String ma, String ten, double gia, int sl, String pl, String nsx, String hsd, String mt) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        ProcedureCall procedureCall = session.createStoredProcedureCall("INSERT_SANPHAM");

        // Đăng ký các tham số và thiết lập giá trị
        procedureCall.registerParameter("MA", String.class, ParameterMode.IN).bindValue(ma);
        procedureCall.registerParameter("TEN", String.class, ParameterMode.IN).bindValue(ten);
        procedureCall.registerParameter("GIA", Double.class, ParameterMode.IN).bindValue(gia);
        procedureCall.registerParameter("SL", Integer.class, ParameterMode.IN).bindValue(sl);
        procedureCall.registerParameter("PL", String.class, ParameterMode.IN).bindValue(pl);
        procedureCall.registerParameter("NSX", String.class, ParameterMode.IN).bindValue(nsx);
        procedureCall.registerParameter("HSD", String.class, ParameterMode.IN).bindValue(hsd);
        procedureCall.registerParameter("MT", String.class, ParameterMode.IN).bindValue(mt);

        // Thực hiện stored procedure
        // ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
        // ResultSetOutput resultSetOutput = (ResultSetOutput)
        // procedureOutputs.getCurrent();
        procedureCall.execute();

        session.getTransaction().commit();
    }

    public void updateSanPham(String ma, String ten, double gia, int sl, String pl, String nsx, String hsd, String mt) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        ProcedureCall procedureCall = session.createStoredProcedureCall("UPDATE_SANPHAM");

        // Đăng ký các tham số và thiết lập giá trị
        procedureCall.registerParameter("MA", String.class, ParameterMode.IN).bindValue(ma);
        procedureCall.registerParameter("TEN", String.class, ParameterMode.IN).bindValue(ten);
        procedureCall.registerParameter("GIA", Double.class, ParameterMode.IN).bindValue(gia);
        procedureCall.registerParameter("SL", Integer.class, ParameterMode.IN).bindValue(sl);
        procedureCall.registerParameter("PL", String.class, ParameterMode.IN).bindValue(pl);
        procedureCall.registerParameter("NSXUAT", String.class, ParameterMode.IN).bindValue(nsx);
        procedureCall.registerParameter("HSDUNG", String.class, ParameterMode.IN).bindValue(hsd);
        procedureCall.registerParameter("MT", String.class, ParameterMode.IN).bindValue(mt);

        // Thực hiện stored procedure
        // ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
        // ResultSetOutput resultSetOutput = (ResultSetOutput)
        // procedureOutputs.getCurrent();
        procedureCall.execute();

        session.getTransaction().commit();
    }

    public void deleteSanPham(String ma) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        ProcedureCall procedureCall = session.createStoredProcedureCall("DELETE_SANPHAM");

        // Đăng ký các tham số và thiết lập giá trị
        procedureCall.registerParameter("MA", String.class, ParameterMode.IN).bindValue(ma);

        // Thực hiện stored procedure
        // ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
        // ResultSetOutput resultSetOutput = (ResultSetOutput)
        // procedureOutputs.getCurrent();
        procedureCall.execute();

        session.getTransaction().commit();
    }

    public ResultSet searchSanPham(String keyword) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        ProcedureCall procedureCall = session.createStoredProcedureCall("SEARCH_SANPHAM");

        // Đăng ký các tham số và thiết lập giá trị
        procedureCall.registerParameter("TEXT_SEARCH", String.class, ParameterMode.IN).bindValue(keyword);
        procedureCall.registerParameter("DSSP", ResultSet.class, ParameterMode.REF_CURSOR);

        // Thực hiện stored procedure
        ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
        // ResultSetOutput resultSetOutput = (ResultSetOutput)
        // procedureOutputs.getCurrent();
        procedureCall.execute();
        ResultSet resultSet = (ResultSet) procedureOutputs.getOutputParameterValue("DSSP");

        session.getTransaction().commit();

        return resultSet;
    }

    public Integer DOANHTHU(String from, String to) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        ProcedureCall procedureCall = session.createStoredProcedureCall("DOANHTHU");

        // Đăng ký các tham số và thiết lập giá trị
        procedureCall.registerParameter("FR_DATE", String.class, ParameterMode.IN).bindValue(from);
        procedureCall.registerParameter("END_DATE", String.class, ParameterMode.IN).bindValue(to);
        procedureCall.registerParameter("TONG", Integer.class, ParameterMode.OUT);

        // Thực hiện procedure
        procedureCall.execute();

        // Lấy giá trị từ tham số đầu ra
        Integer totalRevenue = (Integer) procedureCall.getOutputParameterValue("TONG");

        session.getTransaction().commit();

        return totalRevenue;
    }

    public List<String> readPhanLoai() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<String> phanloaiList = null;

        try {
            String queryString = "select phanLoaiString from SanPhamDTO";

            Query<String> query = session.createQuery(queryString, String.class);
            phanloaiList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return phanloaiList;
    }

    public List<String> getMaSPList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query<String> query = session.createQuery("SELECT s.maSPString FROM SanPhamDTO s", String.class);
        List<String> maSPList = query.getResultList();

        session.getTransaction().commit();

        return maSPList;
    }

    public String AutoGenerateMaSP() {
        // Lấy danh sách MaSP trong CSDL
        List<String> maSPList = new ArrayList<>();
        maSPList = getMaSPList();
        List<Integer> numberList = new ArrayList<>();
        // Tách riêng phần số trong MaSP ra
        for (String maSP : maSPList) {
            // Xóa tất cả các ký tự không phải số từ chuỗi
            String numberString = maSP.replaceAll("[^\\d]", "");
            numberList.add(Integer.parseInt(numberString));
        }
        // Tạo MaSP mới theo định dạng "SPxxxxxx"
        String MaSP = "SP" + String.format("%06d", Collections.max(numberList) + 1);
        return MaSP;
    }
}

// getResultSet()
// getNextResultSet()
// unwarp(ResultSet.class)
