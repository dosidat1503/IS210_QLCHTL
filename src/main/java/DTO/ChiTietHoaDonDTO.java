package DTO;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "CHITIETHOADON")
@IdClass(ChiTietHoaDonId.class)
public class ChiTietHoaDonDTO implements Serializable {
    @Id
    @Column(name = "MAHD")
    private String maHoaDon;

    @Id
    @Column(name = "MaSP")
    private String maSP;

    @Column(name = "SOLUONG")
    private Integer soLuong;

    @Column(name = "GIA")
    private Integer gia;

    /**
     * @return the soLuong
     */
    public Integer getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the maSP
     */
    public String getMaSP() {
        return maSP;
    }

    /**
     * @param maSP the maSP to set
     */
    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    /**
     * 
     */
    public ChiTietHoaDonDTO() {
    }

    /**
     * @return the maHoaDon
     */
    public String getMaHoaDon() {
        return maHoaDon;
    }

    /**
     * @param maHoaDon the maHoaDon to set
     */
    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    /**
     * @return the gia
     */
    public Integer getGia() {
        return gia;
    }

    /**
     * @param gia the gia to set
     */
    public void setGia(Integer gia) {
        this.gia = gia;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDonDTO [maHoaDon=" + maHoaDon + ", maSP=" + maSP + ", soLuong=" + soLuong + ", gia=" + gia
                + "]";
    }
}
