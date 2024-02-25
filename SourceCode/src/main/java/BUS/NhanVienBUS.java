package BUS;

import java.util.ArrayList;
import java.util.List;

import DAO_Hibernate.NhanVienDAO;
import DTO.NhanVienDTO;

public class NhanVienBUS {

    private List<NhanVienDTO> list_NhanVienDTOs;
    /**
     * Xử lý các lệnh trong database
     */
    private NhanVienDAO nhanVienDAO;

    /**
     * @return the list_NhanVienDTOs
     */
    public List<NhanVienDTO> getListnNhanVienDTOs() {
        return list_NhanVienDTOs;
    }

    /**
     * @param list_NhanVienDTOs the list_NhanVienDTOs to set
     */
    public void setListnNhanVienDTOs(List<NhanVienDTO> list_NhanVienDTOs) {
        this.list_NhanVienDTOs = list_NhanVienDTOs;
    }

    public NhanVienBUS() {
        list_NhanVienDTOs = new ArrayList<>();
        nhanVienDAO = new NhanVienDAO();
        list_NhanVienDTOs = nhanVienDAO.readDB("", "manv");
    }

    /**
     * Thêm 1 Nhân viên vào danh sách và database
     *
     * @param nhanVienDTO
     * @return true nếu thành công
     * @throws Exception
     */
    public Boolean them(NhanVienDTO nhanVienDTO) throws Exception {
        if (nhanVienDAO.them_optimized(nhanVienDTO)) {
            list_NhanVienDTOs.add(nhanVienDTO);
            return true;
        }
        return false;
    }

    /**
     * Xóa 1 Nhân viên khỏi danh sách và database
     *
     * @param nhanVienDTO
     * @return true nếu thành công
     * @throws Exception
     */
    public Boolean xoa(NhanVienDTO nhanVienDTO) throws Exception {
        if (nhanVienDAO.xoa(nhanVienDTO)) {
            // Duyệt từng phần tử của list_NhanVienDTOs
            for (NhanVienDTO nv : list_NhanVienDTOs) {
                if (nv.getMaNVString().equals(nhanVienDTO.getMaNVString())) {
                    list_NhanVienDTOs.remove(nv);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sửa thông tin của 1 Nhân viên trong danh sách và database
     *
     * @param nhanVienDTO
     * @return true nếu thành công
     * @throws Exception
     */
    public Boolean sua(NhanVienDTO nhanVienDTO) throws Exception {
        if (nhanVienDAO.sua(nhanVienDTO)) {
            // Duyệt từng phần tử của list_NhanVienDTOs
            for (int i = 0; i < list_NhanVienDTOs.size(); i++) {
                if (list_NhanVienDTOs.get(i).getMaNVString().equals(nhanVienDTO.getMaNVString())) {
                    list_NhanVienDTOs.set(i, nhanVienDTO);
                    return true;
                }
            }
        }

        return false;
    }
}
