package BUS;

import java.util.ArrayList;
import java.util.List;

import DAO_Hibernate.KhachHangDAO;
import DTO.KhachHangDTO;

public class KhachHangBUS {

    private List<KhachHangDTO> list_KhachHangDTOs;
    /**
     * Xử lý các lệnh trong database
     */
    private KhachHangDAO khachHangDAO;

    /**
     * @return the list_KhachHangDTOs
     */
    public List<KhachHangDTO> getlist_KhachHangDTOs() {
        return list_KhachHangDTOs;
    }

    /**
     * @param list_KhachHangDTOs the list_KhachHangDTOs to set
     */
    public void setlist_KhachHangDTOs(List<KhachHangDTO> list_KhachHangDTOs) {
        this.list_KhachHangDTOs = list_KhachHangDTOs;
    }

    public KhachHangBUS() {
        list_KhachHangDTOs = new ArrayList<>();
        khachHangDAO = new KhachHangDAO();
        list_KhachHangDTOs = khachHangDAO.readDB();
    }

    /**
     * Thêm 1 Khách hàng vào danh sách và database
     * 
     * @param khachHangDTO
     * @return true nếu thành công
     * @throws Exception
     */
    public Boolean them(KhachHangDTO khachHangDTO) throws Exception {
        if (khachHangDAO.them_optimized(khachHangDTO)) {
            list_KhachHangDTOs.add(khachHangDTO);
            return true;
        }
        return false;
    }

    /**
     * Xóa 1 Khách hàng khỏi danh sách và database
     * 
     * @param khachHangDTO
     * @return true nếu thành công
     * @throws Exception
     */
    public Boolean xoa(KhachHangDTO khachHangDTO) throws Exception {
        if (khachHangDAO.xoa(khachHangDTO)) {
            // Duyệt từng phần tử của list_KhachHangDTOs
            for (KhachHangDTO nv : list_KhachHangDTOs) {
                if (nv.getSDTKHString().equals(khachHangDTO.getSDTKHString())) {
                    list_KhachHangDTOs.remove(nv);
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean xoa_proc(KhachHangDTO khachHangDTO) throws Exception {
        if (khachHangDAO.deleteKhachHang(khachHangDTO.getMaKHString())) {
            // Duyệt từng phần tử của list_KhachHangDTOs
            for (KhachHangDTO nv : list_KhachHangDTOs) {
                if (nv.getSDTKHString().equals(khachHangDTO.getSDTKHString())) {
                    list_KhachHangDTOs.remove(nv);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sửa thông tin của 1 Khách hàng trong danh sách và database
     * 
     * @param khachHangDTO
     * @return true nếu thành công
     * @throws Exception
     */
    public Boolean sua_optimized(KhachHangDTO khachHangDTO) throws Exception {
        int index = list_KhachHangDTOs.indexOf(khachHangDTO);
        if (khachHangDAO.sua(khachHangDTO)) {
            list_KhachHangDTOs.set(index, khachHangDTO);
            return true;
        }
        return false;
    }

}
