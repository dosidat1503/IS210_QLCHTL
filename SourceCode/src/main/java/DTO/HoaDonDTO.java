package DTO;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "HOADON")
public class HoaDonDTO implements Serializable {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAHD")
    private String maHDString;

    @Column(name = "NGAYMUA")
    private Date ngayMuaHangHDDate;

    /**
     * Mã nhân viên bán hàng
     */
    @Column(name = "MANV")
    private String maNVString;

    @Column(name = "MAKH")
    private String maKHString;

    @Column(name = "HINHTHUC")
    private String hinhThucThanhToanHDString;

    @Column(name = "TRIGIA")
    private Integer triGiaHDInteger;

    /**
     * @return the maKHString
     */
    public String getMaKHString() {
        return maKHString;
    }

    /**
     * @param maKHString the maKHString to set
     */
    public void setMaKHString(String maKHString) {
        this.maKHString = maKHString;
    }

    /**
     * Dùng để cấu hình mối quan hệ ManyToOne giữa Hóa đơn và Nhân viên: Nhiều
     * Hóa đơn có thể được tạo bởi một Nhân viên
     */
    @ManyToOne
    @JoinColumn(name = "MANV", insertable = false, updatable = false)
    private NhanVienDTO nhanVienDTO;

    /**
     * Dùng để cấu hình mối quạn hệ ManyToMany giữa Hóa đơn và Sản phẩm: - Một
     * hóa đơn có thể có nhiều sản phẩm - Một sản phẩm có thể được bán trong
     * nhiều hóa đơn
     */
    @ManyToMany
    @JoinTable(name = "CHITIETHOADON", // Tên của bảng trung gian dưới CSDL
            joinColumns = {
                    @JoinColumn(name = "MAHD") // Tên trường khóa ngoại trỏ đến HD
            }, inverseJoinColumns = {
                    @JoinColumn(name = "MASP") // Tên trường khóa ngoại trỏ đến SP
            })
    private Set<SanPhamDTO> sanPhamDTOs;

    /**
     *
     */
    public HoaDonDTO() {
    }

    /**
     * @param maHDString
     * @param ngayMuaHangHDDate
     * @param tenNVBanHangHDString
     * @param tenKhachHangHDString
     * @param hinhThucThanhToanHDString
     */
    public HoaDonDTO(String maHDString, String ngayMuaHangHDDate, String maNVString,
            String maKHString, String hinhThucThanhToanHDString) {
        this.maHDString = maHDString;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            this.ngayMuaHangHDDate = formatter.parse(ngayMuaHangHDDate);
        } catch (ParseException e) {
            System.out.println("Cannot convert String to Date");
            e.printStackTrace();
        }
        this.maNVString = maNVString;
        this.maKHString = maKHString;
        this.hinhThucThanhToanHDString = hinhThucThanhToanHDString;
    }

    /**
     * @return the maHDString
     */
    public String getMaHDString() {
        return maHDString;
    }

    /**
     * @param maHDString the maHDString to set
     */
    public void setMaHDString(String maHDString) {
        this.maHDString = maHDString;
    }

    /**
     * @return the ngayMuaHangHDDate
     */
    public Date getNgayMuaHangHDDate() {
        return ngayMuaHangHDDate;
    }

    /**
     * @param ngayMuaHangHDDate the ngayMuaHangHDDate to set
     */
    public void setNgayMuaHangHDDate(String ngayMuaHangHDDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            this.ngayMuaHangHDDate = formatter.parse(ngayMuaHangHDDate);
        } catch (ParseException e) {
            System.out.println("Cannot convert String to Date");
            e.printStackTrace();
        }
    }

    /**
     * @param hinhThucThanhToanHDString the hinhThucThanhToanHDString to set
     */
    public void setHinhThucThanhToanHDString(String hinhThucThanhToanHDString) {
        this.hinhThucThanhToanHDString = hinhThucThanhToanHDString;
    }

    /**
     * @param ngayMuaHangHDDate the ngayMuaHangHDDate to set
     */
    public void setNgayMuaHangHDDate(Date ngayMuaHangHDDate) {
        this.ngayMuaHangHDDate = ngayMuaHangHDDate;
    }

    /**
     * @return the maNVString
     */
    public String getMaNVString() {
        return maNVString;
    }

    /**
     * @param maNVString the maNVString to set
     */
    public void setMaNVString(String maNVString) {
        this.maNVString = maNVString;
    }

    /**
     * @return the hinhThucThanhToanHDString
     */
    public String getHinhThucThanhToanHDString() {
        return hinhThucThanhToanHDString;
    }

    /**
     * @return the nhanVienDTO
     */
    public NhanVienDTO getNhanVienDTO() {
        return nhanVienDTO;
    }

    /**
     * @param nhanVienDTO the nhanVienDTO to set
     */
    public void setNhanVienDTO(NhanVienDTO nhanVienDTO) {
        this.nhanVienDTO = nhanVienDTO;
    }

    /**
     * @return the triGiaHDInteger
     */
    public Integer getTriGiaHDInteger() {
        return triGiaHDInteger;
    }

    /**
     * @param triGiaHDInteger the triGiaHDInteger to set
     */
    public void setTriGiaHDInteger(Integer triGiaHDInteger) {
        this.triGiaHDInteger = triGiaHDInteger;
    }

    /**
     * @return the sanPhamDTOs
     */
    public Set<SanPhamDTO> getSanPhamDTOs() {
        return sanPhamDTOs;
    }

    /**
     * @param sanPhamDTOs the sanPhamDTOs to set
     */
    public void setSanPhamDTOs(Set<SanPhamDTO> sanPhamDTOs) {
        this.sanPhamDTOs = sanPhamDTOs;
    }

    @Override
    public String toString() {
        return "HoaDonDTO [maHDString=" + maHDString + ", ngayMuaHangHDDate=" + ngayMuaHangHDDate + ", maNVString="
                + maNVString + ", maKHString=" + maKHString + ", hinhThucThanhToanHDString=" + hinhThucThanhToanHDString
                + ", triGiaHDInteger=" + triGiaHDInteger + ", nhanVienDTO=" + nhanVienDTO + ", sanPhamDTOs="
                + sanPhamDTOs + "]";
    }
}
