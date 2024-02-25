package DTO;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "CHITIETKHO")
@IdClass(ChiTietKhoId.class)
public class ChiTietKhoDTO implements Serializable{
    @Id
    @Column(name = "MaLoHang")
    private String maLoHang;

    @Id
    @Column(name = "MaSP")
    private String maSP;

    @Column(name = "SOLUONG")
    private Integer soLuong;

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
     * @return the maLoHang
     */
    public String getMaLoHang() {
        return maLoHang;
    }

    /**
     * @param maLoHang the maLoHang to set
     */
    public void setMaLoHang(String maLoHang) {
        this.maLoHang = maLoHang;
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
    public ChiTietKhoDTO() {
    }
}
