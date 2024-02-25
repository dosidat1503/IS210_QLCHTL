package BUS;

public class TestKhachHang {
    public static void main(String[] args) {
        KhachHangBUS khachHangBUS = new KhachHangBUS();
        for (int i = 0; i < khachHangBUS.getlist_KhachHangDTOs().size(); i++){
            System.out.println(khachHangBUS.getlist_KhachHangDTOs().get(i).toString());
        }
    }
}
