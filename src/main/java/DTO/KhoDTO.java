package DTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "KHO")
public class KhoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MALOHANG")
    private String maLoHangString;

    @Column(name = "NGAYNHAP")
    private Date ngayNhapDate;

    @Column(name = "MANV")
    private String maNhanVienString;

    @Column(name = "TENNHACUNGCAP")
    private String tenNhaCungCapString;
    // @Column(name = "NGAYXUAT")
    // private Date ngayXuatDate;

    // @OneToMany(mappedBy = "kho", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<ChiTietKhoDTO> chiTietKhoList;
    /**
     * Lưu trữ thông tin tên của cửa hàng được nhận sản phẩm từ kho (có nhiều cửa
     * hàng tiện lợi)
     */
    // @Column(name = "TENCH")
    // private String tenCHXuatKhoString;

    /**
     *
     */
    public KhoDTO() {
    }

    /**
     * @param maLoHangString
     * @param ngayNhapDate
     * @param ngayXuatDate
     * @param tenCHXuatKhoString
     */
    public KhoDTO(String maLoHangString, Date ngayNhapDate/* , Date ngayXuatDate, String tenCHXuatKhoString */) {
        this.maLoHangString = maLoHangString;
        this.ngayNhapDate = ngayNhapDate;
        // this.ngayXuatDate = ngayXuatDate;
        // this.tenCHXuatKhoString = tenCHXuatKhoString;
    }

    /**
     * @return the maLoHangString
     */
    public String getMaLoHangString() {
        return maLoHangString;
    }

    /**
     * @param maLoHangString the maLoHangString to set
     */
    public void setMaLoHangString(String maLoHangString) {
        this.maLoHangString = maLoHangString;
    }

    /**
     * @return the ngayNhapDate
     */
    public Date getNgayNhapDate() {
        return ngayNhapDate;
    }

    /**
     * @param ngayNhapDate the ngayNhapDate to set
     */
    public void setNgayNhapDate(Date ngayNhapDate) {
        this.ngayNhapDate = ngayNhapDate;
    }

    /**
     * @param ngayNhapDate "dd/MM/yyyy" the ngayNhapDate to set
     */
    public void setNgayNhapDate(String ngayNhapDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            this.ngayNhapDate = formatter.parse(ngayNhapDate);
        } catch (ParseException e) {
            System.out.println("Cannot convert String to Date");
            e.printStackTrace();
        }
    }

    /**
     * @return the maNhanVienString
     */
    public String getMaNhanVienString() {
        return maNhanVienString;
    }

    /**
     * @param maNhanVienString the maNhanVienString to set
     */
    public void setMaNhanVienString(String maNhanVienString) {
        this.maNhanVienString = maNhanVienString;
    }

    /**
     * @return the tenNhaCungCapString
     */
    public String getTenNhaCungCapString() {
        return tenNhaCungCapString;
    }

    /**
     * @param tenNhaCungCapString the tenNhaCungCapString to set
     */
    public void setTenNhaCungCapString(String tenNhaCungCapString) {
        this.tenNhaCungCapString = tenNhaCungCapString;
    }

    @Override
    public String toString() {
        return "KhoDTO [maLoHangString=" + maLoHangString + ", ngayNhapDate=" + ngayNhapDate + ", maNhanVienString="
                + maNhanVienString + ", tenNhaCungCapString=" + tenNhaCungCapString + "]";
    }
}
