package BUS;

import java.util.ArrayList;
import java.util.List;

import DAO_Hibernate.SanPhamDAO;
import DTO.SanPhamDTO;

public class SanPhamBUS {

    private List<SanPhamDTO> list_SanPhamDTOs;
    /**
     * Xử lý các lệnh trong database
     */
    private SanPhamDAO sanPhamDAO;

    /**
     * @return the list_SanPhamDTOs
     */
    public List<SanPhamDTO> getList_SanPhamDTOs() {
        return list_SanPhamDTOs;
    }

    /**
     * @param list_SanPhamDTOs the list_SanPhamDTOs to set
     */
    public void setList_SanPhamDTOs(List<SanPhamDTO> list_SanPhamDTOs) {
        this.list_SanPhamDTOs = list_SanPhamDTOs;
    }

    public SanPhamBUS() {
        list_SanPhamDTOs = new ArrayList<>();
        sanPhamDAO = new SanPhamDAO();
        list_SanPhamDTOs = sanPhamDAO.readDB("", "phanLoaiString");
    }

    /**
     * Thêm 1 Sản phẩm vào danh sách và database
     *
     * @param sanPhamDTO
     * @return true nếu thành công
     * @throws Exception
     */
    public Boolean them(SanPhamDTO sanPhamDTO) throws Exception {
        if (sanPhamDAO.them_optimized(sanPhamDTO)) {
            list_SanPhamDTOs.add(sanPhamDTO);
            return true;
        }
        return false;
    }

    /**
     * Xóa 1 Sản phẩm khỏi danh sách và database
     *
     * @param sanPhamDTO
     * @return true nếu thành công
     * @throws Exception
     */
    public Boolean xoa(SanPhamDTO sanPhamDTO) throws Exception {
        if (sanPhamDAO.xoa(sanPhamDTO)) {
            // Duyệt từng phần tử của list_SanPhamDTOs
            for (SanPhamDTO sp : list_SanPhamDTOs) {
                if (sp.getMaSPString().equals(sanPhamDTO.getMaSPString())) {
                    list_SanPhamDTOs.remove(sp);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sửa thông tin của 1 Sản phẩm trong danh sách và database
     *
     * @param sanPhamDTO
     * @return true nếu thành công
     * @throws Exception
     */
    public Boolean sua(SanPhamDTO sanPhamDTO) throws Exception {
        if (sanPhamDAO.sua(sanPhamDTO)) {
            // Duyệt từng phần tử của list_SanPhamDTOs
            for (int i = 0; i < list_SanPhamDTOs.size(); i++) {
                if (list_SanPhamDTOs.get(i).getMaSPString().equals(sanPhamDTO.getMaSPString())) {
                    list_SanPhamDTOs.set(i, sanPhamDTO);
                    return true;
                }
            }
        }

        return false;
    }

    public SanPhamDTO tim(String keyword) {
        return sanPhamDAO.tim(keyword);
    }
}
