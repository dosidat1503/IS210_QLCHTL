package BUS;

import java.util.ArrayList;
import java.util.List;

import DAO_Hibernate.KhoDAO;
import DTO.KhoDTO;

public class KhoBUS {
    private List<KhoDTO> list_KhoDTOs;
    /**
     * Xử lý các lệnh trong database
     */
    private KhoDAO khoDAO;

    /**
     * @return the list_KhoDTOs
     */
    public List<KhoDTO> getList_KhoVanDTOs() {
        return list_KhoDTOs;
    }

    /**
     * @param list_KhoDTOs the list_KhoDTOs to set
     */
    public void setList_KhoVanDTOs(List<KhoDTO> list_KhoDTOs) {
        this.list_KhoDTOs = list_KhoDTOs;
    }

    public KhoBUS() {
        list_KhoDTOs = new ArrayList<>();
        khoDAO = new KhoDAO();
        list_KhoDTOs = khoDAO.readDB();
    }

    /**
     * Thêm 1 Lô hàng vào danh sách và database
     * 
     * @param khoDTO
     * @return true nếu thành công
     * @throws Exception
     */
    public Boolean them(KhoDTO khoDTO) throws Exception {
        if (khoDAO.them_optimized(khoDTO)) {
            list_KhoDTOs.add(khoDTO);
            return true;
        }
        return false;
    }

    /**
     * Xóa 1 Lô hàng khỏi danh sách và database
     * 
     * @param khoDTO
     * @return true nếu thành công
     * @throws Exception
     */
    public Boolean xoa(KhoDTO khoDTO) throws Exception {
        if (khoDAO.xoa(khoDTO)) {
            // Duyệt từng phần tử của list_KhoDTOs
            for (KhoDTO nv : list_KhoDTOs) {
                if (nv.getMaLoHangString().equals(khoDTO.getMaLoHangString())) {
                    list_KhoDTOs.remove(nv);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sửa thông tin của 1 Lô hàng trong danh sách và database
     * 
     * @param khoDTO
     * @return true nếu thành công
     * @throws Exception
     */
    public Boolean sua(KhoDTO khoDTO) throws Exception {
        if (khoDAO.sua(khoDTO)) {
            // Duyệt từng phần tử của list_KhoDTOs
            for (int i = 0; i < list_KhoDTOs.size(); i++) {
                if (list_KhoDTOs.get(i).getMaLoHangString().equals(khoDTO.getMaLoHangString())) {
                    list_KhoDTOs.set(i, khoDTO);
                    return true;
                }
            }
        }

        return false;
    }

}
