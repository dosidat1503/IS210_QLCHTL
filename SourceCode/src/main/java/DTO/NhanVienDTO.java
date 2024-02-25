package DTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "NHANVIEN")
public class NhanVienDTO implements Serializable {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MANV")
    private String maNVString;

    @Column(name = "TENNV")
    private String hoTenNVString;

    @Column(name = "SDTNV")
    private String SDTNVString;

    @Column(name = "NGAYSINH")
    private Date ngaySinhNVDate;

    @Column(name = "CCCD")
    private String CCCDNVString;

    @Column(name = "DIACHI")
    private String diaChiNVString;

    @Column(name = "CHUCVU")
    private String chucVuNVString;

    @Column(name = "LUONG")
    private Integer luongInteger;

    @Column(name = "NGAYVL")
    private Date ngayVaoLamDate;

    // One-to-one relationship with NGUOIDUNG entity, using MaNV as foreign key
    @OneToOne(mappedBy = "nhanVienDTO", fetch = FetchType.LAZY)
    private NguoiDungDTO nguoiDungDTO;

    /**
     *
     */
    public NhanVienDTO() {
    }

    /**
     * @param maNVString
     * @param hoTenNVString
     * @param sDTNVString
     * @param cCCDNVString
     * @param diaChiNVString
     * @param chucVuNVString
     * @param ngaySinhNVDate
     */
    public NhanVienDTO(String maNVString, String hoTenNVString, String sDTNVString, String cCCDNVString,
            String diaChiNVString, String chucVuNVString, String ngaySinhNVDate) {
        this.maNVString = maNVString;
        this.hoTenNVString = hoTenNVString;
        SDTNVString = sDTNVString;
        CCCDNVString = cCCDNVString;
        this.diaChiNVString = diaChiNVString;
        this.chucVuNVString = chucVuNVString;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            this.ngaySinhNVDate = formatter.parse(ngaySinhNVDate);
        } catch (ParseException e) {
            System.out.println("Cannot convert String to Date");
            e.printStackTrace();
        }
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
     * @return the hoTenNVString
     */
    public String getHoTenNVString() {
        return hoTenNVString;
    }

    /**
     * @param hoTenNVString the hoTenNVString to set
     */
    public void setHoTenNVString(String hoTenNVString) {
        this.hoTenNVString = hoTenNVString;
    }

    /**
     * @return the sDTNVString
     */
    public String getSDTNVString() {
        return SDTNVString;
    }

    /**
     * @param sDTNVString the sDTNVString to set
     */
    public void setSDTNVString(String sDTNVString) {
        SDTNVString = sDTNVString;
    }

    /**
     * @return the cCCDNVString
     */
    public String getCCCDNVString() {
        return CCCDNVString;
    }

    /**
     * @param cCCDNVString the cCCDNVString to set
     */
    public void setCCCDNVString(String cCCDNVString) {
        CCCDNVString = cCCDNVString;
    }

    /**
     * @return the diaChiNVString
     */
    public String getDiaChiNVString() {
        return diaChiNVString;
    }

    /**
     * @param diaChiNVString the diaChiNVString to set
     */
    public void setDiaChiNVString(String diaChiNVString) {
        this.diaChiNVString = diaChiNVString;
    }

    /**
     * @return the chucVuNVString
     */
    public String getChucVuNVString() {
        return chucVuNVString;
    }

    /**
     * @param chucVuNVString the chucVuNVString to set
     */
    public void setChucVuNVString(String chucVuNVString) {
        this.chucVuNVString = chucVuNVString;
    }

    /**
     * @return the ngaySinhNVDate
     */
    public Date getNgaySinhNVDate() {
        return ngaySinhNVDate;
    }

    /**
     * @param ngaySinhNVDate "dd/MM/yyyy" the ngaySinhNVDate to set
     */
    public void setNgaySinhNVDate(String ngaySinhNVDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            this.ngaySinhNVDate = formatter.parse(ngaySinhNVDate);
        } catch (ParseException e) {
            System.out.println("Cannot convert String to Date");
            e.printStackTrace();
        }
    }

    public void setNgaySinhNVDate(Date ngaySinhNVDate) {
        this.ngaySinhNVDate = ngaySinhNVDate;
    }

    /**
     * @return the nguoiDungDTO
     */
    public NguoiDungDTO getNguoiDungDTO() {
        return nguoiDungDTO;
    }

    /**
     * @param nguoiDungDTO the nguoiDungDTO to set
     */
    public void setNguoiDungDTO(NguoiDungDTO nguoiDungDTO) {
        this.nguoiDungDTO = nguoiDungDTO;
    }

    /**
     * @return the luongInteger
     */
    public Integer getLuongInteger() {
        return luongInteger;
    }

    /**
     * @param luongInteger the luongInteger to set
     */
    public void setLuongInteger(Integer luongInteger) {
        this.luongInteger = luongInteger;
    }

    /**
     * @return the ngayVaoLamDate
     */
    public Date getNgayVaoLamDate() {
        return ngayVaoLamDate;
    }

    /**
     * @param ngayVaoLamDate the ngayVaoLamDate to set
     */
    public void setNgayVaoLamDate(Date ngayVaoLamDate) {
        this.ngayVaoLamDate = ngayVaoLamDate;
    }

    /**
     * @param ngaySinhNVDate "dd/MM/yyyy" the ngaySinhNVDate to set
     */
    public void setNgayVaoLamDate(String ngayVaoLamNVDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            this.ngayVaoLamDate = formatter.parse(ngayVaoLamNVDate);
        } catch (ParseException e) {
            System.out.println("Cannot convert String to Date");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "NhanVienDTO [maNVString=" + maNVString + ", hoTenNVString=" + hoTenNVString + ", SDTNVString="
                + SDTNVString + ", ngaySinhNVDate=" + ngaySinhNVDate + ", CCCDNVString=" + CCCDNVString
                + ", diaChiNVString=" + diaChiNVString + ", chucVuNVString=" + chucVuNVString + ", luongInteger="
                + luongInteger + ", ngayVaoLamDate=" + ngayVaoLamDate + ", nguoiDungDTO=" + nguoiDungDTO + "]";
    }
}
