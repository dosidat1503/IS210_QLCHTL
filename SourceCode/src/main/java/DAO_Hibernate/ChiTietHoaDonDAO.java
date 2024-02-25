package DAO_Hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import DTO.ChiTietHoaDonDTO;
import java.sql.SQLException;

public class ChiTietHoaDonDAO {

    Session session;

    public List<String> getSanPhamListByMaHoaDon(String maHoaDon) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Tạo câu truy vấn để lấy danh sách sản phẩm trong lô hàng
        String hql = "SELECT ch.maSP FROM ChiTietHoaDonDTO ch WHERE ch.maHoaDon = :maHoaDon";
        Query<String> query = session.createQuery(hql, String.class);
        query.setParameter("maHoaDon", maHoaDon);

        // Thực hiện truy vấn và lấy danh sách sản phẩm
        List<String> sanPhamList = query.getResultList();

        session.getTransaction().commit();

        return sanPhamList;
    }

    /**
     * Thêm một Chi tiết hóa đơn mới đã có thông tin vào CSDL
     *
     * @param chiTietHoaDonDTO
     * @return True nếu thành công
     * @throws Exception và rollback Transaction
     */
    public boolean them_optimized(ChiTietHoaDonDTO chiTietHoaDonDTO) throws Exception {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(chiTietHoaDonDTO);
        session.getTransaction().commit();
        return true;

    }

    public List<ChiTietHoaDonDTO> tim(String keyword) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query<ChiTietHoaDonDTO> query = session
                .createQuery("FROM ChiTietHoaDonDTO WHERE maHoaDon = :keyword", ChiTietHoaDonDTO.class);
        query.setParameter("keyword", keyword);
        // query.setParameter("searchKeyword", "%" + keyword + "%");

        List<ChiTietHoaDonDTO> chiTietHoaDonDTOList = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return chiTietHoaDonDTOList;
    }

    public static void main(String[] args) {
        ChiTietHoaDonDAO chiTietHoaDonDAO = new ChiTietHoaDonDAO();
        List<ChiTietHoaDonDTO> chiTietHoaDonDTOs = new ArrayList<>();
        chiTietHoaDonDTOs = chiTietHoaDonDAO.tim("HD8");
        for (int i = 0; i < chiTietHoaDonDTOs.size(); i++) {
            System.out.println(chiTietHoaDonDTOs.get(i).toString());
        }
    }
}
