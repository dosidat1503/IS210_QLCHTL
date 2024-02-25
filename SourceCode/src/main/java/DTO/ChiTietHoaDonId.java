package DTO;

import java.io.Serializable;

public class ChiTietHoaDonId implements Serializable {
    private String maHoaDon;
    private String maSP;

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
    public ChiTietHoaDonId() {
    }
}
