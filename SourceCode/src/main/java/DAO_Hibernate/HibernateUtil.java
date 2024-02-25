package DAO_Hibernate;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import DTO.ChiTietHoaDonDTO;
import DTO.ChiTietKhoDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.KhoDTO;
import DTO.NguoiDungDTO;
import DTO.NhanVienDTO;
import DTO.SanPhamDTO;

// Xây dựng Session Factory
// Muốn thực hiện truy vấn thì phải tạo ra một Session
// Session tồn tại trong một tgian ngắn
public class HibernateUtil {

    private final static SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();
        // conf.configure("hibernate.cfg.xml");

        // Cấu hình connection bằng Java
        Properties props = new Properties();
        props.put(Environment.DIALECT, "org.hibernate.dialect.OracleDialect");
        props.put(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver");
        props.put(Environment.URL, "jdbc:oracle:thin:@localhost:1521:orcl");
        props.put(Environment.USER, "ConvinienceStore");
        props.put(Environment.PASS, "conviniencestore");
        conf.setProperties(props);

        // Thêm các persistent class vào
        // Trong đối tượng Configuration chỉ định lớp annotation để ánh xạ bằng
        // addAnnotatedClass()
        conf.addAnnotatedClass(SanPhamDTO.class);
        conf.addAnnotatedClass(NhanVienDTO.class);
        conf.addAnnotatedClass(KhoDTO.class);
        conf.addAnnotatedClass(KhachHangDTO.class);
        conf.addAnnotatedClass(HoaDonDTO.class);
        conf.addAnnotatedClass(NguoiDungDTO.class);
        conf.addAnnotatedClass(ChiTietKhoDTO.class);
        conf.addAnnotatedClass(ChiTietHoaDonDTO.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);
    }

    /**
     * SessionFactory factory = HibernateUtil.getFactory(); Session session =
     * factory.openSession(); session.close();
     *
     * @return the factory
     */
    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }
}
