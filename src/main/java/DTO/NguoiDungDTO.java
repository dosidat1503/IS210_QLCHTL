package DTO;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "TAIKHOAN")
public class NguoiDungDTO implements Serializable {
    @Id
    @Column(name = "USERNAME")
    private String usernameString;

    @Column(name = "PASSWORD")
    private String passwordString;

    @Column(name = "MANV")
    private String maNVString;

    // Khai báo mối quan hệ One-to-One với NHANVIEN
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNV")
    private NhanVienDTO nhanVienDTO;

    /**
     * @param maNVString
     * @param usernameString
     * @param passwordString
     * @param nhanVienDTO
     */
    public NguoiDungDTO(String maNVString, String usernameString, String passwordString, NhanVienDTO nhanVienDTO) {
        this.maNVString = maNVString;
        this.usernameString = usernameString;
        this.passwordString = passwordString;
        this.nhanVienDTO = nhanVienDTO;
    }

    /**
     * 
     */
    public NguoiDungDTO() {
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
     * @return the usernameString
     */
    public String getUsernameString() {
        return usernameString;
    }

    /**
     * @param usernameString the usernameString to set
     */
    public void setUsernameString(String usernameString) {
        this.usernameString = usernameString;
    }

    /**
     * @return the passwordString
     */
    public String getPasswordString() {
        return passwordString;
    }

    /**
     * @param passwordString the passwordString to set
     */
    public void setPasswordString(String passwordString) {
        this.passwordString = passwordString;
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

    @Override
    public String toString() {
        return "NguoiDungDTO [maNVString=" + maNVString + ", usernameString=" + usernameString + ", passwordString="
                + passwordString + "]";
    }
}
