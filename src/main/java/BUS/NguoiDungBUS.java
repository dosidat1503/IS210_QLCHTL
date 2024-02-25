package BUS;

import java.util.ArrayList;
import java.util.List;

import DAO_Hibernate.NguoiDungDAO;
import DTO.NguoiDungDTO;

public class NguoiDungBUS {
    private List<NguoiDungDTO> list_NguoiDungDTOs;
    /**
     * Xử lý các lệnh trong database
     */
    private NguoiDungDAO nguoiDungDAO;

    /**
     * @return the list_NguoiDungDTOs
     */
    public List<NguoiDungDTO> getList_NguoiDungDTOs() {
        return list_NguoiDungDTOs;
    }

    /**
     * @param list_NguoiDungDTOs the list_NguoiDungDTOs to set
     */
    public void setList_NguoiDungDTOs(List<NguoiDungDTO> list_NguoiDungDTOs) {
        this.list_NguoiDungDTOs = list_NguoiDungDTOs;
    }

    public NguoiDungBUS() {
        list_NguoiDungDTOs = new ArrayList<>();
        nguoiDungDAO = new NguoiDungDAO();
        list_NguoiDungDTOs = nguoiDungDAO.readDB();
    }

    /**
     * Thêm 1 Người dùng vào danh sách và database
     * 
     * @param nguoiDungDTO
     * @return true nếu thành công
     * @throws Exception
     */
    public Boolean them(NguoiDungDTO nguoiDungDTO) throws Exception {
        if (nguoiDungDAO.them_optimized(nguoiDungDTO)) {
            list_NguoiDungDTOs.add(nguoiDungDTO);
            return true;
        }
        return false;
    }

    /**
     * Xóa 1 Người dùng khỏi danh sách và database
     * 
     * @param nguoiDungDTO
     * @return true nếu thành công
     * @throws Exception
     */
    public Boolean xoa(NguoiDungDTO nguoiDungDTO) throws Exception {
        if (nguoiDungDAO.xoa(nguoiDungDTO)) {
            // Duyệt từng phần tử của list_NguoiDungDTOs
            for (NguoiDungDTO sp : list_NguoiDungDTOs) {
                if (sp.getMaNVString().equals(nguoiDungDTO.getMaNVString())) {
                    list_NguoiDungDTOs.remove(sp);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sửa thông tin của 1 Người dùng trong danh sách và database
     * 
     * @param nguoiDungDTO
     * @return true nếu thành công
     * @throws Exception
     */
    public Boolean sua(NguoiDungDTO nguoiDungDTO) throws Exception {
        if (nguoiDungDAO.sua(nguoiDungDTO)) {
            // Duyệt từng phần tử của list_NguoiDungDTOs
            for (int i = 0; i < list_NguoiDungDTOs.size(); i++) {
                if (list_NguoiDungDTOs.get(i).getMaNVString().equals(nguoiDungDTO.getMaNVString())) {
                    list_NguoiDungDTOs.set(i, nguoiDungDTO);
                    return true;
                }
            }
        }

        return false;
    }

}
