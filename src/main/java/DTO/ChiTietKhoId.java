package DTO;

import java.io.Serializable;

public class ChiTietKhoId implements Serializable {
    private String maLoHang;
    private String maSP;

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
    public ChiTietKhoId() {
    }
}
