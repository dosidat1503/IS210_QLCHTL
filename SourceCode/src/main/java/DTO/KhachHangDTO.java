package DTO;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "KHACHHANG")
public class KhachHangDTO implements Serializable {
    @Id
    @Column(name = "MAKH")
    private String maKHString;

    @Column(name = "SDTKH")
    private String SDTKHString;

    @Column(name = "TENKH")
    private String tenKHString;

    @Column(name = "NGAYDK")
    private Date ngayDangKyDate;

    /**
     * @param maKHString
     * @param sDTKHString
     * @param tenKHString
     */
    public KhachHangDTO(String maKHString, String sDTKHString, String tenKHString) {
        this.maKHString = maKHString;
        SDTKHString = sDTKHString;
        this.tenKHString = tenKHString;
    }

    /**
     *
     */
    public KhachHangDTO() {
    }

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
     * @return the SDTKHString
     */
    public String getSDTKHString() {
        return SDTKHString;
    }

    /**
     * @param sDTKHString the sDTKHString to set
     */
    public void setSDTKHString(String sDTKHString) {
        SDTKHString = sDTKHString;
    }

    /**
     * @return the tenKHString
     */
    public String gettenKHString() {
        return tenKHString;
    }

    /**
     * @param tenKHString the tenKHString to set
     */
    public void settenKHString(String tenKHString) {
        this.tenKHString = tenKHString;
    }

    /**
     * @return the tenKHString
     */
    public String getTenKHString() {
        return tenKHString;
    }

    /**
     * @param tenKHString the tenKHString to set
     */
    public void setTenKHString(String tenKHString) {
        this.tenKHString = tenKHString;
    }

    /**
     * @param ngayDangKyDate "dd/MM/yyyy" the ngayDangKyDate to set
     */
    public void setNgayDangKyDate(String ngayDangKyDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            this.ngayDangKyDate = formatter.parse(ngayDangKyDate);
        } catch (ParseException e) {
            System.out.println("Cannot convert String to Date");
            e.printStackTrace();
        }
    }

    /**
     * @return the ngayDangKyDate
     */
    public Date getNgayDangKyDate() {
        return ngayDangKyDate;
    }

    /**
     * @param ngayDangKyDate the ngayDangKyDate to set
     */
    public void setNgayDangKyDate(Date ngayDangKyDate) {
        this.ngayDangKyDate = ngayDangKyDate;
    }

    @Override
    public String toString() {
        return "KhachHangDTO [maKHString=" + maKHString + ", SDTKHString=" + SDTKHString + ", tenKHString="
                + tenKHString + ", ngayDangKyDate=" + ngayDangKyDate + "]";
    }

    // @Override
    // public String toString() {
    // return "KhachHangDTO [SDTKHString=" + SDTKHString + ", tenKHString=" +
    // tenKHString + "]";
    // }
}
