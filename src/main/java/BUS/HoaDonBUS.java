package BUS;

import java.util.ArrayList;
import java.util.List;

import DAO_Hibernate.HoaDonDAO;
import DTO.HoaDonDTO;

public class HoaDonBUS {

    private List<HoaDonDTO> list_HoaDonDTOs;
    /**
     * Xử lý các lệnh trong database
     */
    private HoaDonDAO hoaDonDAO;

    /**
     * @return the list_HoaDonDTOs
     */
    public List<HoaDonDTO> getList_HoaDonDTOs() {
        return list_HoaDonDTOs;
    }

    /**
     * @param list_HoaDonDTOs the list_HoaDonDTOs to set
     */
    public void setList_HoaDonDTOs(List<HoaDonDTO> list_HoaDonDTOs) {
        this.list_HoaDonDTOs = list_HoaDonDTOs;
    }

    public HoaDonBUS() {
        list_HoaDonDTOs = new ArrayList<>();
        hoaDonDAO = new HoaDonDAO();
        list_HoaDonDTOs = hoaDonDAO.readDB("", "NGAYMUA DESC");
    }

    /**
     * Thêm 1 Hóa đơn vào danh sách và database
     *
     * @param hoaDonDTO
     * @return true nếu thành công
     * @throws Exception
     */
    public Boolean them(HoaDonDTO hoaDonDTO) throws Exception {
        if (hoaDonDAO.them_optimized(hoaDonDTO)) {
            list_HoaDonDTOs.add(hoaDonDTO);
            return true;
        }
        return false;
    }

    /**
     * Xóa 1 Hóa đơn khỏi danh sách và database
     *
     * @param hoaDonDTO
     * @return true nếu thành công
     * @throws Exception
     */
    public Boolean xoa(HoaDonDTO hoaDonDTO) throws Exception {
        if (hoaDonDAO.xoa(hoaDonDTO)) {
            // Duyệt từng phần tử của list_HoaDonDTOs
            for (HoaDonDTO nv : list_HoaDonDTOs) {
                if (nv.getMaHDString().equals(hoaDonDTO.getMaHDString())) {
                    list_HoaDonDTOs.remove(nv);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sửa thông tin của 1 Hóa đơn trong danh sách và database
     *
     * @param hoaDonDTO
     * @return true nếu thành công
     * @throws Exception
     */
    public Boolean sua(HoaDonDTO hoaDonDTO) throws Exception {
        if (hoaDonDAO.sua(hoaDonDTO)) {
            // Duyệt từng phần tử của list_HoaDonDTOs
            for (int i = 0; i < list_HoaDonDTOs.size(); i++) {
                if (list_HoaDonDTOs.get(i).getMaHDString().equals(hoaDonDTO.getMaHDString())) {
                    list_HoaDonDTOs.set(i, hoaDonDTO);
                    return true;
                }
            }
        }

        return false;
    }

}
