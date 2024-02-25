/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.Staff;

import BUS.SanPhamBUS;
import DAO_Hibernate.ChiTietHoaDonDAO;
import DAO_Hibernate.HoaDonDAO;
import DAO_Hibernate.KhachHangDAO;
import DAO_Hibernate.TestProcedure;
import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.SanPhamDTO;
import GUI.Product;
import GUI.Product_icon;
import static GUI.DangNhap.nhanVien;
import GUI.LimitDigitsDocumentFilter;

import java.awt.Component;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author Locc
 */
public class BanHang extends javax.swing.JFrame {

    private SanPhamBUS sanPhamBUS;
    private boolean isjTextField_TimKiemActionPerformedEnable = true;

    /**
     * Creates new form BanHang
     */
    public BanHang() {
        initComponents();
        setLocationRelativeTo(null);
        sanPhamBUS = new SanPhamBUS();
        for (int i = 0; i < sanPhamBUS.getList_SanPhamDTOs().size(); i++) {
            Product_icon product_icon = new Product_icon(sanPhamBUS.getList_SanPhamDTOs().get(i));

            product_icon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Click");
                    Component[] components = jPanelGioHang.getComponents();
                    if (components.length == 0) {

                        Product p = new Product(product_icon.getSanPhamDTO());
                        jPanelGioHang.add(p);
                        updateTongGia();
                        jPanelGioHang.revalidate();
                        jPanelGioHang.repaint();
                    } else {
                        boolean isExist = false;
                        // Iterate over the components in reverse order
                        for (int i = components.length - 1; i >= 0; i--) {
                            Component component = components[i];
                            if (component instanceof Product) {
                                Product product = (Product) component;
                                // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                    // Tăng số lượng lên
                                    product.tangSoLuong();
                                    updateTongGia();
                                    jPanelGioHang.revalidate();
                                    jPanelGioHang.repaint();
                                    isExist = true;
                                    System.out.println("Sản phẩm đã tồn tại");
                                }
                            }
                        }

                        if (!isExist) { // Chưa tồn tại
                            Product p = new Product(product_icon.getSanPhamDTO());
                            jPanelGioHang.add(p);
                            updateTongGia();
                            jPanelGioHang.revalidate();
                            jPanelGioHang.repaint();
                            System.out.println("Sản phẩm chưa tồn tại");
                        }
                    }
                }
            });

            product_icon.getjPanel11().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Component[] components = jPanelGioHang.getComponents();
                    if (components.length == 0) {

                        Product p = new Product(product_icon.getSanPhamDTO());
                        jPanelGioHang.add(p);
                        updateTongGia();
                        jPanelGioHang.revalidate();
                        jPanelGioHang.repaint();
                    } else {
                        boolean isExist = false;
                        for (Component component : components) {
                            if (component instanceof Product) {
                                Product product = (Product) component;
                                // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                    // Tăng số lượng lên
                                    product.tangSoLuong();
                                    updateTongGia();
                                    jPanelGioHang.revalidate();
                                    jPanelGioHang.repaint();
                                    isExist = true;
                                    System.out.println("Sản phẩm đã tồn tại");

                                }
                            }
                        }

                        if (!isExist) { // Chưa tồn tại
                            Product p = new Product(product_icon.getSanPhamDTO());
                            jPanelGioHang.add(p);
                            updateTongGia();
                            jPanelGioHang.revalidate();
                            jPanelGioHang.repaint();
                            System.out.println("Sản phẩm chưa tồn tại");
                        }
                    }
                }
            });

            product_icon.getjLabelTen().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Component[] components = jPanelGioHang.getComponents();
                    if (components.length == 0) {

                        Product p = new Product(product_icon.getSanPhamDTO());
                        jPanelGioHang.add(p);
                        updateTongGia();
                        jPanelGioHang.revalidate();
                        jPanelGioHang.repaint();
                    } else {
                        boolean isExist = false;
                        for (Component component : components) {
                            if (component instanceof Product) {
                                Product product = (Product) component;
                                // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                    // Tăng số lượng lên
                                    product.tangSoLuong();
                                    updateTongGia();
                                    jPanelGioHang.revalidate();
                                    jPanelGioHang.repaint();
                                    isExist = true;
                                    System.out.println("Sản phẩm đã tồn tại");

                                }
                            }
                        }

                        if (!isExist) { // Chưa tồn tại
                            Product p = new Product(product_icon.getSanPhamDTO());
                            jPanelGioHang.add(p);
                            updateTongGia();
                            jPanelGioHang.revalidate();
                            jPanelGioHang.repaint();
                            System.out.println("Sản phẩm chưa tồn tại");
                        }
                    }
                }
            });

            product_icon.getjLabelGia().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Component[] components = jPanelGioHang.getComponents();
                    if (components.length == 0) {

                        Product p = new Product(product_icon.getSanPhamDTO());
                        jPanelGioHang.add(p);
                        updateTongGia();
                        jPanelGioHang.revalidate();
                        jPanelGioHang.repaint();
                    } else {
                        boolean isExist = false;
                        for (Component component : components) {
                            if (component instanceof Product) {
                                Product product = (Product) component;
                                // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                    // Tăng số lượng lên
                                    product.tangSoLuong();
                                    updateTongGia();
                                    jPanelGioHang.revalidate();
                                    jPanelGioHang.repaint();
                                    isExist = true;
                                    System.out.println("Sản phẩm đã tồn tại");

                                }
                            }
                        }

                        if (!isExist) { // Chưa tồn tại
                            Product p = new Product(product_icon.getSanPhamDTO());
                            jPanelGioHang.add(p);
                            updateTongGia();
                            jPanelGioHang.revalidate();
                            jPanelGioHang.repaint();
                            System.out.println("Sản phẩm chưa tồn tại");
                        }
                    }
                }
            });

            product_icon.getjLabelSL().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Component[] components = jPanelGioHang.getComponents();
                    if (components.length == 0) {

                        Product p = new Product(product_icon.getSanPhamDTO());
                        jPanelGioHang.add(p);
                        updateTongGia();
                        jPanelGioHang.revalidate();
                        jPanelGioHang.repaint();
                    } else {
                        boolean isExist = false;
                        for (Component component : components) {
                            if (component instanceof Product) {
                                Product product = (Product) component;
                                // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                    // Tăng số lượng lên
                                    product.tangSoLuong();
                                    updateTongGia();
                                    jPanelGioHang.revalidate();
                                    jPanelGioHang.repaint();
                                    isExist = true;
                                    System.out.println("Sản phẩm đã tồn tại");

                                }
                            }
                        }

                        if (!isExist) { // Chưa tồn tại
                            Product p = new Product(product_icon.getSanPhamDTO());
                            jPanelGioHang.add(p);
                            updateTongGia();
                            jPanelGioHang.revalidate();
                            jPanelGioHang.repaint();
                            System.out.println("Sản phẩm chưa tồn tại");
                        }
                    }
                }
            });
            jPanelSanPham.add(product_icon);
            product_icon.addMouseListener(product_icon);

        }

        AbstractDocument doc = (AbstractDocument) jTextField1.getDocument();
        doc.setDocumentFilter(new LimitDigitsDocumentFilter(10));
        jPanelSanPham.revalidate();
        jPanelSanPham.repaint();
        // System.out.println("GUI.BanHang.jPanel7MouseClicked()");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelProduct = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        panelRound1 = new GUI.PanelRound();
        jTextField_TimKiem = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanelThanhToan = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanelGioHang = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanelSanPham = new javax.swing.JPanel();

        jPanelProduct.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("icon");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel11.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 150));

        jLabel5.setText("Tên ");
        jPanel11.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 150, 25));

        jLabel6.setText("Giá");
        jPanel11.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 175, 150, 25));

        jPanelProduct.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 200));

        jPanel15.setBackground(new java.awt.Color(204, 204, 255));

        jLabel8.setText("Product 1");

        jLabel9.setText("Số lượng:");

        jLabel10.setText("Tiền");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(128, 128, 128)
                        .addComponent(jLabel10))
                    .addComponent(jLabel8))
                .addContainerGap(274, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(143, 203, 253));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search 1.png"))); // NOI18N
        jPanel16.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 21, 48, 48));

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setMaximumSize(new java.awt.Dimension(330, 50));
        panelRound1.setMinimumSize(new java.awt.Dimension(330, 50));
        panelRound1.setPreferredSize(new java.awt.Dimension(330, 50));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField_TimKiem.setFont(new java.awt.Font("Be Vietnam Pro", 0, 24)); // NOI18N
        jTextField_TimKiem.setBorder(null);
        jTextField_TimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_TimKiemMouseClicked(evt);
            }
        });
        jTextField_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_TimKiemActionPerformed(evt);
            }
        });
        panelRound1.add(jTextField_TimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 280, 50));

        jPanel16.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 330, 50));

        jPanel1.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 0, 430, 90));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151), 3));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Thoát");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 150, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 170, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 90));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelThanhToan.setBackground(new java.awt.Color(143, 203, 253));
        jPanelThanhToan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanelThanhToan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelThanhToanMouseClicked(evt);
            }
        });
        jPanelThanhToan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thanh Toán");
        jPanelThanhToan.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 18, 174, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ThanhToan.png"))); // NOI18N
        jPanelThanhToan.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 57, 201, 201));

        jPanel2.add(jPanelThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 250));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 250, 250));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel5.setLayout(new java.awt.GridLayout(4, 4));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("7");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanel5.add(jPanel7);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("8");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPanel12.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanel5.add(jPanel12);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("9");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        jPanel13.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanel5.add(jPanel13);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel14.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanel5.add(jPanel14);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("4");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        jPanel17.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanel5.add(jPanel17);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("5");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        jPanel18.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanel5.add(jPanel18);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("6");
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });
        jPanel19.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanel5.add(jPanel19);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel20.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanel5.add(jPanel20);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("1");
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });
        jPanel21.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanel5.add(jPanel21);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("2");
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });
        jPanel22.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanel5.add(jPanel22);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("3");
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
        });
        jPanel23.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanel5.add(jPanel23);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel24.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanel5.add(jPanel24);

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("C");
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });
        jPanel25.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanel5.add(jPanel25);

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("0");
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
        });
        jPanel26.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanel5.add(jPanel26);

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel27.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanel5.add(jPanel27);

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Del");
        jLabel31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel31MouseClicked(evt);
            }
        });
        jPanel28.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanel5.add(jPanel28);

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 600, 250, 250));

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Be Vietnam Pro", 0, 24)); // NOI18N
        jLabel11.setText("SDT KH:");
        jPanel8.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 40));

        jTextField1.setFont(new java.awt.Font("Be Vietnam Pro ExtraLight", 0, 24)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField1.setBorder(null);
        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel8.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 1, 170, 38));

        jPanel9.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 40));

        jPanel4.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 500, 40));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(45, 96, 151)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Tổng:");
        jPanel10.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 60));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("0");
        jPanel10.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 230, 60));

        jPanel4.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 500, 60));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(45, 96, 151)));

        jPanelGioHang.setBackground(new java.awt.Color(255, 255, 255));
        jPanelGioHang.setLayout(new javax.swing.BoxLayout(jPanelGioHang, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(jPanelGioHang);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 500, 460));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 500, 850));

        jPanel6.setBackground(new java.awt.Color(45, 96, 151));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tên sản phẩm");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 490, 40));

        jLabel13.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Giá");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 5, 60, 40));

        jLabel14.setFont(new java.awt.Font("Be Vietnam Pro SemiBold", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Số lượng");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 5, 150, 40));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 1100, 50));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanelSanPham.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSanPham.setLayout(new javax.swing.BoxLayout(jPanelSanPham, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(jPanelSanPham);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, 1100, 760));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelThanhToanMouseClicked
        // TODO add your handling code here:
        // Tạo mới một hóa đơn

        HoaDonDTO hoaDonDTO = new HoaDonDTO();
        HoaDonDAO hoaDonDAO = new HoaDonDAO();
        hoaDonDTO.setMaHDString(hoaDonDAO.AutoGenerateMaHD());
        KhachHangDAO khachHangDAO = new KhachHangDAO();
        KhachHangDTO khachHangDTO = new KhachHangDTO();
        String sdtKH = jTextField1.getText();
        if (!sdtKH.equals("")) {
            khachHangDTO = khachHangDAO.tim(jTextField1.getText());
            if (khachHangDTO == null) {
                JOptionPane.showMessageDialog(null, "Số điện thoại của khách hàng này không tồn tại!");
                return;
            }
            hoaDonDTO.setMaKHString(khachHangDTO.getMaKHString());
        }

        hoaDonDTO.setNgayMuaHangHDDate(new Date());
        hoaDonDTO.setMaNVString(nhanVien.getMaNVString());
        hoaDonDTO.setHinhThucThanhToanHDString("Tiền mặt");
//        hoaDonDTO.setTriGiaHDInteger(Integer.valueOf(jLabel7.getText()));
        hoaDonDTO.setTriGiaHDInteger(0);
        hoaDonDAO.them_optimized(hoaDonDTO);

        Component[] components = jPanelGioHang.getComponents();
        List<SanPhamDTO> sanPhamGioHangList = new ArrayList<>();
        int index = 0;
        // Iterate over the components in reverse order
        for (int i = components.length - 1; i >= 0; i--) {
            Component component = components[i];

            // Check if the component is an instance of Product
            if (component instanceof Product) {
                Product product = (Product) component;
                sanPhamGioHangList.add(index, product.getSanPhamDTO());

                // Thêm sản phẩm vào chi tiết hóa đơn
                ChiTietHoaDonDTO chiTietHoaDonDTO = new ChiTietHoaDonDTO();
                chiTietHoaDonDTO.setMaHoaDon(hoaDonDTO.getMaHDString());
                chiTietHoaDonDTO.setMaSP(sanPhamGioHangList.get(index).getMaSPString());
                chiTietHoaDonDTO.setSoLuong(product.getSl());
                chiTietHoaDonDTO.setGia(Integer.valueOf(product.getjLabelThanhTien().getText()));
                // Thêm chi tiết hóa đơn vào CSDL
                ChiTietHoaDonDAO chiTietHoaDonDAO = new ChiTietHoaDonDAO();
                try {
                    chiTietHoaDonDAO.them_optimized(chiTietHoaDonDTO);
                    jPanelGioHang.removeAll();
                    jLabel7.setText("0");
                    jPanelSanPham.removeAll();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Số lượng sản phẩm không đủ!");
                    hoaDonDAO.xoa(hoaDonDTO);
                }
                index++;
            }
        }
        JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
        //-----------------------------------------
        sanPhamBUS = new SanPhamBUS();
        for (int i = 0; i < sanPhamBUS.getList_SanPhamDTOs().size(); i++) {
            Product_icon product_icon = new Product_icon(sanPhamBUS.getList_SanPhamDTOs().get(i));

            product_icon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Click");
                    Component[] components = jPanelGioHang.getComponents();
                    if (components.length == 0) {

                        Product p = new Product(product_icon.getSanPhamDTO());
                        jPanelGioHang.add(p);
                        updateTongGia();
                        jPanelGioHang.revalidate();
                        jPanelGioHang.repaint();
                    } else {
                        boolean isExist = false;
                        // Iterate over the components in reverse order
                        for (int i = components.length - 1; i >= 0; i--) {
                            Component component = components[i];
                            if (component instanceof Product) {
                                Product product = (Product) component;
                                // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                    // Tăng số lượng lên
                                    product.tangSoLuong();
                                    updateTongGia();
                                    jPanelGioHang.revalidate();
                                    jPanelGioHang.repaint();
                                    isExist = true;
                                    System.out.println("Sản phẩm đã tồn tại");
                                }
                            }
                        }

                        if (!isExist) { // Chưa tồn tại
                            Product p = new Product(product_icon.getSanPhamDTO());
                            jPanelGioHang.add(p);
                            updateTongGia();
                            jPanelGioHang.revalidate();
                            jPanelGioHang.repaint();
                            System.out.println("Sản phẩm chưa tồn tại");
                        }
                    }
                }
            });

            product_icon.getjPanel11().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Component[] components = jPanelGioHang.getComponents();
                    if (components.length == 0) {

                        Product p = new Product(product_icon.getSanPhamDTO());
                        jPanelGioHang.add(p);
                        updateTongGia();
                        jPanelGioHang.revalidate();
                        jPanelGioHang.repaint();
                    } else {
                        boolean isExist = false;
                        for (Component component : components) {
                            if (component instanceof Product) {
                                Product product = (Product) component;
                                // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                    // Tăng số lượng lên
                                    product.tangSoLuong();
                                    updateTongGia();
                                    jPanelGioHang.revalidate();
                                    jPanelGioHang.repaint();
                                    isExist = true;
                                    System.out.println("Sản phẩm đã tồn tại");

                                }
                            }
                        }

                        if (!isExist) { // Chưa tồn tại
                            Product p = new Product(product_icon.getSanPhamDTO());
                            jPanelGioHang.add(p);
                            updateTongGia();
                            jPanelGioHang.revalidate();
                            jPanelGioHang.repaint();
                            System.out.println("Sản phẩm chưa tồn tại");
                        }
                    }
                }
            });

            product_icon.getjLabelTen().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Component[] components = jPanelGioHang.getComponents();
                    if (components.length == 0) {

                        Product p = new Product(product_icon.getSanPhamDTO());
                        jPanelGioHang.add(p);
                        updateTongGia();
                        jPanelGioHang.revalidate();
                        jPanelGioHang.repaint();
                    } else {
                        boolean isExist = false;
                        for (Component component : components) {
                            if (component instanceof Product) {
                                Product product = (Product) component;
                                // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                    // Tăng số lượng lên
                                    product.tangSoLuong();
                                    updateTongGia();
                                    jPanelGioHang.revalidate();
                                    jPanelGioHang.repaint();
                                    isExist = true;
                                    System.out.println("Sản phẩm đã tồn tại");

                                }
                            }
                        }

                        if (!isExist) { // Chưa tồn tại
                            Product p = new Product(product_icon.getSanPhamDTO());
                            jPanelGioHang.add(p);
                            updateTongGia();
                            jPanelGioHang.revalidate();
                            jPanelGioHang.repaint();
                            System.out.println("Sản phẩm chưa tồn tại");
                        }
                    }
                }
            });

            product_icon.getjLabelGia().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Component[] components = jPanelGioHang.getComponents();
                    if (components.length == 0) {

                        Product p = new Product(product_icon.getSanPhamDTO());
                        jPanelGioHang.add(p);
                        updateTongGia();
                        jPanelGioHang.revalidate();
                        jPanelGioHang.repaint();
                    } else {
                        boolean isExist = false;
                        for (Component component : components) {
                            if (component instanceof Product) {
                                Product product = (Product) component;
                                // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                    // Tăng số lượng lên
                                    product.tangSoLuong();
                                    updateTongGia();
                                    jPanelGioHang.revalidate();
                                    jPanelGioHang.repaint();
                                    isExist = true;
                                    System.out.println("Sản phẩm đã tồn tại");

                                }
                            }
                        }

                        if (!isExist) { // Chưa tồn tại
                            Product p = new Product(product_icon.getSanPhamDTO());
                            jPanelGioHang.add(p);
                            updateTongGia();
                            jPanelGioHang.revalidate();
                            jPanelGioHang.repaint();
                            System.out.println("Sản phẩm chưa tồn tại");
                        }
                    }
                }
            });

            product_icon.getjLabelSL().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Component[] components = jPanelGioHang.getComponents();
                    if (components.length == 0) {

                        Product p = new Product(product_icon.getSanPhamDTO());
                        jPanelGioHang.add(p);
                        updateTongGia();
                        jPanelGioHang.revalidate();
                        jPanelGioHang.repaint();
                    } else {
                        boolean isExist = false;
                        for (Component component : components) {
                            if (component instanceof Product) {
                                Product product = (Product) component;
                                // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                    // Tăng số lượng lên
                                    product.tangSoLuong();
                                    updateTongGia();
                                    jPanelGioHang.revalidate();
                                    jPanelGioHang.repaint();
                                    isExist = true;
                                    System.out.println("Sản phẩm đã tồn tại");

                                }
                            }
                        }

                        if (!isExist) { // Chưa tồn tại
                            Product p = new Product(product_icon.getSanPhamDTO());
                            jPanelGioHang.add(p);
                            updateTongGia();
                            jPanelGioHang.revalidate();
                            jPanelGioHang.repaint();
                            System.out.println("Sản phẩm chưa tồn tại");
                        }
                    }
                }
            });
            jPanelSanPham.add(product_icon);
            product_icon.addMouseListener(product_icon);

        }
        jPanelSanPham.revalidate();
        jPanelSanPham.repaint();
        //-------------------------------------------------------------
        revalidate();
        repaint();
    }//GEN-LAST:event_jPanelThanhToanMouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        // TODO add your handling code here:
        NhanVienHomePage nhanVienHomePage = new NhanVienHomePage();
        nhanVienHomePage.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jLabel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseClicked
        // TODO add your handling code here:
        Component[] components = jPanelGioHang.getComponents();

        // Iterate over the components in reverse order
        for (int i = components.length - 1; i >= 0; i--) {
            Component component = components[i];

            // Check if the component is an instance of Product
            if (component instanceof Product) {
                Product product = (Product) component;

                // Check if the product needs to be deleted
                if (product.isSelect()) {
                    // Remove the product from the panel and update the UI
                    jPanelGioHang.remove(product);
                    updateTongGia();
                }
            }
        }

        // Update the UI after all products have been removed
        jPanelGioHang.revalidate();
        jPanelGioHang.repaint();
    }//GEN-LAST:event_jLabel31MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
        Component[] components = jPanelGioHang.getComponents();

        // Iterate over the components in reverse order
        for (int i = components.length - 1; i >= 0; i--) {
            Component component = components[i];

            // Check if the component is an instance of Product
            if (component instanceof Product) {
                Product product = (Product) component;

                // Check if the product needs to be deleted
                if (product.isSelect()) {
                    String gtri = product.getjLabelSoLuong().getText();

                    if (gtri.length() < 10) {
                        if (gtri.equals("0")) {
                            gtri = "";
                        }
                        product.getjLabelSoLuong().setText(gtri + jLabel16.getText());
                        product.updateSoLuong();
                    }
//                    jPanelGioHang.remove(product);
                    updateTongGia();
                    jPanel10.revalidate();
                    jPanel10.repaint();
                }
            }
        }
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        Component[] components = jPanelGioHang.getComponents();

        // Iterate over the components in reverse order
        for (int i = components.length - 1; i >= 0; i--) {
            Component component = components[i];

            // Check if the component is an instance of Product
            if (component instanceof Product) {
                Product product = (Product) component;

                // Check if the product needs to be deleted
                if (product.isSelect()) {
                    String gtri = product.getjLabelSoLuong().getText();

                    if (gtri.length() < 10) {
                        if (gtri.equals("0")) {
                            gtri = "";
                        }
                        product.getjLabelSoLuong().setText(gtri + jLabel17.getText());
                        product.updateSoLuong();
                    }
//                    jPanelGioHang.remove(product);
                    updateTongGia();
                    jPanel10.revalidate();
                    jPanel10.repaint();
                }
            }
        }
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
        Component[] components = jPanelGioHang.getComponents();

        // Iterate over the components in reverse order
        for (int i = components.length - 1; i >= 0; i--) {
            Component component = components[i];

            // Check if the component is an instance of Product
            if (component instanceof Product) {
                Product product = (Product) component;

                // Check if the product needs to be deleted
                if (product.isSelect()) {
                    String gtri = product.getjLabelSoLuong().getText();

                    if (gtri.length() < 10) {
                        if (gtri.equals("0")) {
                            gtri = "";
                        }
                        product.getjLabelSoLuong().setText(gtri + jLabel18.getText());
                        product.updateSoLuong();
                    }
//                    jPanelGioHang.remove(product);
                    updateTongGia();
                    jPanel10.revalidate();
                    jPanel10.repaint();
                }
            }
        }
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
        Component[] components = jPanelGioHang.getComponents();

        // Iterate over the components in reverse order
        for (int i = components.length - 1; i >= 0; i--) {
            Component component = components[i];

            // Check if the component is an instance of Product
            if (component instanceof Product) {
                Product product = (Product) component;

                // Check if the product needs to be deleted
                if (product.isSelect()) {
                    String gtri = product.getjLabelSoLuong().getText();

                    if (gtri.length() < 10) {
                        if (gtri.equals("0")) {
                            gtri = "";
                        }
                        product.getjLabelSoLuong().setText(gtri + jLabel20.getText());
                        product.updateSoLuong();
                    }
//                    jPanelGioHang.remove(product);
                    updateTongGia();
                    jPanel10.revalidate();
                    jPanel10.repaint();
                }
            }
        }
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        Component[] components = jPanelGioHang.getComponents();

        // Iterate over the components in reverse order
        for (int i = components.length - 1; i >= 0; i--) {
            Component component = components[i];

            // Check if the component is an instance of Product
            if (component instanceof Product) {
                Product product = (Product) component;

                // Check if the product needs to be deleted
                if (product.isSelect()) {
                    String gtri = product.getjLabelSoLuong().getText();

                    if (gtri.length() < 10) {
                        if (gtri.equals("0")) {
                            gtri = "";
                        }
                        product.getjLabelSoLuong().setText(gtri + jLabel21.getText());
                        product.updateSoLuong();
                    }
//                    jPanelGioHang.remove(product);
                    updateTongGia();
                    jPanel10.revalidate();
                    jPanel10.repaint();
                }
            }
        }
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        // TODO add your handling code here:
        Component[] components = jPanelGioHang.getComponents();

        // Iterate over the components in reverse order
        for (int i = components.length - 1; i >= 0; i--) {
            Component component = components[i];

            // Check if the component is an instance of Product
            if (component instanceof Product) {
                Product product = (Product) component;

                // Check if the product needs to be deleted
                if (product.isSelect()) {
                    String gtri = product.getjLabelSoLuong().getText();

                    if (gtri.length() < 10) {
                        if (gtri.equals("0")) {
                            gtri = "";
                        }
                        product.getjLabelSoLuong().setText(gtri + jLabel22.getText());
                        product.updateSoLuong();
                    }
//                    jPanelGioHang.remove(product);
                    updateTongGia();
                    jPanel10.revalidate();
                    jPanel10.repaint();
                }
            }
        }
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        // TODO add your handling code here:
        Component[] components = jPanelGioHang.getComponents();

        // Iterate over the components in reverse order
        for (int i = components.length - 1; i >= 0; i--) {
            Component component = components[i];

            // Check if the component is an instance of Product
            if (component instanceof Product) {
                Product product = (Product) component;

                // Check if the product needs to be deleted
                if (product.isSelect()) {
                    String gtri = product.getjLabelSoLuong().getText();

                    if (gtri.length() < 10) {
                        if (gtri.equals("0")) {
                            gtri = "";
                        }
                        product.getjLabelSoLuong().setText(gtri + jLabel24.getText());
                        product.updateSoLuong();
                    }
//                    jPanelGioHang.remove(product);
                    updateTongGia();
                    jPanel10.revalidate();
                    jPanel10.repaint();
                }
            }
        }
    }//GEN-LAST:event_jLabel24MouseClicked

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
        // TODO add your handling code here:
        Component[] components = jPanelGioHang.getComponents();

        // Iterate over the components in reverse order
        for (int i = components.length - 1; i >= 0; i--) {
            Component component = components[i];

            // Check if the component is an instance of Product
            if (component instanceof Product) {
                Product product = (Product) component;

                // Check if the product needs to be deleted
                if (product.isSelect()) {
                    String gtri = product.getjLabelSoLuong().getText();

                    if (gtri.length() < 10) {
                        if (gtri.equals("0")) {
                            gtri = "";
                        }
                        product.getjLabelSoLuong().setText(gtri + jLabel25.getText());
                        product.updateSoLuong();
                    }
//                    jPanelGioHang.remove(product);
                    updateTongGia();
                    jPanel10.revalidate();
                    jPanel10.repaint();
                }
            }
        }
    }//GEN-LAST:event_jLabel25MouseClicked

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
        // TODO add your handling code here:
        Component[] components = jPanelGioHang.getComponents();

        // Iterate over the components in reverse order
        for (int i = components.length - 1; i >= 0; i--) {
            Component component = components[i];

            // Check if the component is an instance of Product
            if (component instanceof Product) {
                Product product = (Product) component;

                // Check if the product needs to be deleted
                if (product.isSelect()) {
                    String gtri = product.getjLabelSoLuong().getText();

                    if (gtri.length() < 10) {
                        if (gtri.equals("0")) {
                            gtri = "";
                        }
                        product.getjLabelSoLuong().setText(gtri + jLabel26.getText());
                        product.updateSoLuong();
                    }
//                    jPanelGioHang.remove(product);
                    updateTongGia();
                    jPanel10.revalidate();
                    jPanel10.repaint();
                }
            }
        }
    }//GEN-LAST:event_jLabel26MouseClicked

    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        // TODO add your handling code here:
        Component[] components = jPanelGioHang.getComponents();

        // Iterate over the components in reverse order
        for (int i = components.length - 1; i >= 0; i--) {
            Component component = components[i];

            // Check if the component is an instance of Product
            if (component instanceof Product) {
                Product product = (Product) component;

                // Check if the product needs to be deleted
                if (product.isSelect()) {
                    String gtri = product.getjLabelSoLuong().getText();

                    if (gtri.length() < 10) {
                        if (gtri.equals("0")) {
                            gtri = "";
                        }
                        product.getjLabelSoLuong().setText(gtri + jLabel29.getText());
                        product.updateSoLuong();
                    }
//                    jPanelGioHang.remove(product);
                    updateTongGia();
                    jPanel10.revalidate();
                    jPanel10.repaint();
                }
            }
        }
    }//GEN-LAST:event_jLabel29MouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        // TODO add your handling code here:

        Component[] components = jPanelGioHang.getComponents();

        // Iterate over the components in reverse order
        for (int i = components.length - 1; i >= 0; i--) {
            Component component = components[i];

            // Check if the component is an instance of Product
            if (component instanceof Product) {
                Product product = (Product) component;

                // Check if the product needs to be deleted
                if (product.isSelect()) {
                    String gtri = product.getjLabelSoLuong().getText();

                    if (gtri.length() < 10) {

                        product.getjLabelSoLuong().setText("0");
                        product.updateSoLuong();
                    }
//                    jPanelGioHang.remove(product);
                    updateTongGia();
                    jPanel10.revalidate();
                    jPanel10.repaint();
                }
            }
        }
    }//GEN-LAST:event_jLabel28MouseClicked

    private void jButtonXoaMouseClicked(java.awt.event.MouseEvent evt) {
        Component[] components = jPanelGioHang.getComponents();

        // Iterate over the components in reverse order
        for (int i = components.length - 1; i >= 0; i--) {
            Component component = components[i];

            // Check if the component is an instance of Product
            if (component instanceof Product) {
                Product product = (Product) component;

                // Check if the product needs to be deleted
                if (product.isSelect()) {
                    // Remove the product from the panel and update the UI
                    jPanelGioHang.remove(product);
                    updateTongGia();
                }
            }
        }

        // Update the UI after all products have been removed
        jPanelGioHang.revalidate();
        jPanelGioHang.repaint();
    }

    private void jTextField_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField_TimKiemActionPerformed
        String keyword = jTextField_TimKiem.getText();
        TestProcedure testProcedure = new TestProcedure();
        jPanelSanPham.removeAll();
        if (keyword.length() != 0) {
            List<SanPhamDTO> sanPhamDTOs = testProcedure.SEARCH_SANPHAM(keyword);
            if (!sanPhamDTOs.isEmpty()) {
                for (SanPhamDTO sanPhamDTO : sanPhamDTOs) {
                    Product_icon product_icon = new Product_icon(sanPhamDTO);
                    product_icon.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            System.out.println("Click");
                            Component[] components = jPanelGioHang.getComponents();
                            if (components.length == 0) {

                                Product p = new Product(product_icon.getSanPhamDTO());
                                jPanelGioHang.add(p);
                                updateTongGia();
                                jPanelGioHang.revalidate();
                                jPanelGioHang.repaint();
                            } else {
                                boolean isExist = false;
                                // Iterate over the components in reverse order
                                for (int i = components.length - 1; i >= 0; i--) {
                                    Component component = components[i];
                                    if (component instanceof Product) {
                                        Product product = (Product) component;
                                        // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                        if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                            // Tăng số lượng lên
                                            product.tangSoLuong();
                                            updateTongGia();
                                            jPanelGioHang.revalidate();
                                            jPanelGioHang.repaint();
                                            isExist = true;
                                            System.out.println("Sản phẩm đã tồn tại");
                                        }
                                    }
                                }

                                if (!isExist) { // Chưa tồn tại
                                    Product p = new Product(product_icon.getSanPhamDTO());
                                    jPanelGioHang.add(p);
                                    updateTongGia();
                                    jPanelGioHang.revalidate();
                                    jPanelGioHang.repaint();
                                    System.out.println("Sản phẩm chưa tồn tại");
                                }
                            }
                        }
                    });

                    product_icon.getjPanel11().addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            Component[] components = jPanelGioHang.getComponents();
                            if (components.length == 0) {

                                Product p = new Product(product_icon.getSanPhamDTO());
                                jPanelGioHang.add(p);
                                updateTongGia();
                                jPanelGioHang.revalidate();
                                jPanelGioHang.repaint();
                            } else {
                                boolean isExist = false;
                                for (Component component : components) {
                                    if (component instanceof Product) {
                                        Product product = (Product) component;
                                        // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                        if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                            // Tăng số lượng lên
                                            product.tangSoLuong();
                                            updateTongGia();
                                            jPanelGioHang.revalidate();
                                            jPanelGioHang.repaint();
                                            isExist = true;
                                            System.out.println("Sản phẩm đã tồn tại");

                                        }
                                    }
                                }

                                if (!isExist) { // Chưa tồn tại
                                    Product p = new Product(product_icon.getSanPhamDTO());
                                    jPanelGioHang.add(p);
                                    updateTongGia();
                                    jPanelGioHang.revalidate();
                                    jPanelGioHang.repaint();
                                    System.out.println("Sản phẩm chưa tồn tại");
                                }
                            }
                        }
                    });

                    product_icon.getjLabelTen().addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            Component[] components = jPanelGioHang.getComponents();
                            if (components.length == 0) {

                                Product p = new Product(product_icon.getSanPhamDTO());
                                jPanelGioHang.add(p);
                                updateTongGia();
                                jPanelGioHang.revalidate();
                                jPanelGioHang.repaint();
                            } else {
                                boolean isExist = false;
                                for (Component component : components) {
                                    if (component instanceof Product) {
                                        Product product = (Product) component;
                                        // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                        if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                            // Tăng số lượng lên
                                            product.tangSoLuong();
                                            updateTongGia();
                                            jPanelGioHang.revalidate();
                                            jPanelGioHang.repaint();
                                            isExist = true;
                                            System.out.println("Sản phẩm đã tồn tại");

                                        }
                                    }
                                }

                                if (!isExist) { // Chưa tồn tại
                                    Product p = new Product(product_icon.getSanPhamDTO());
                                    jPanelGioHang.add(p);
                                    updateTongGia();
                                    jPanelGioHang.revalidate();
                                    jPanelGioHang.repaint();
                                    System.out.println("Sản phẩm chưa tồn tại");
                                }
                            }
                        }
                    });

                    product_icon.getjLabelGia().addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            Component[] components = jPanelGioHang.getComponents();
                            if (components.length == 0) {

                                Product p = new Product(product_icon.getSanPhamDTO());
                                jPanelGioHang.add(p);
                                updateTongGia();
                                jPanelGioHang.revalidate();
                                jPanelGioHang.repaint();
                            } else {
                                boolean isExist = false;
                                for (Component component : components) {
                                    if (component instanceof Product) {
                                        Product product = (Product) component;
                                        // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                        if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                            // Tăng số lượng lên
                                            product.tangSoLuong();
                                            updateTongGia();
                                            jPanelGioHang.revalidate();
                                            jPanelGioHang.repaint();
                                            isExist = true;
                                            System.out.println("Sản phẩm đã tồn tại");

                                        }
                                    }
                                }

                                if (!isExist) { // Chưa tồn tại
                                    Product p = new Product(product_icon.getSanPhamDTO());
                                    jPanelGioHang.add(p);
                                    updateTongGia();
                                    jPanelGioHang.revalidate();
                                    jPanelGioHang.repaint();
                                    System.out.println("Sản phẩm chưa tồn tại");
                                }
                            }
                        }
                    });

                    product_icon.getjLabelSL().addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            Component[] components = jPanelGioHang.getComponents();
                            if (components.length == 0) {

                                Product p = new Product(product_icon.getSanPhamDTO());
                                jPanelGioHang.add(p);
                                updateTongGia();
                                jPanelGioHang.revalidate();
                                jPanelGioHang.repaint();
                            } else {
                                boolean isExist = false;
                                for (Component component : components) {
                                    if (component instanceof Product) {
                                        Product product = (Product) component;
                                        // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                        if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                            // Tăng số lượng lên
                                            product.tangSoLuong();
                                            updateTongGia();
                                            jPanelGioHang.revalidate();
                                            jPanelGioHang.repaint();
                                            isExist = true;
                                            System.out.println("Sản phẩm đã tồn tại");

                                        }
                                    }
                                }

                                if (!isExist) { // Chưa tồn tại
                                    Product p = new Product(product_icon.getSanPhamDTO());
                                    jPanelGioHang.add(p);
                                    updateTongGia();
                                    jPanelGioHang.revalidate();
                                    jPanelGioHang.repaint();
                                    System.out.println("Sản phẩm chưa tồn tại");
                                }
                            }
                        }
                    });
                    jPanelSanPham.add(product_icon);
                }
            }
        } else {
            for (int i = 0; i < sanPhamBUS.getList_SanPhamDTOs().size(); i++) {
                Product_icon product_icon = new Product_icon(sanPhamBUS.getList_SanPhamDTOs().get(i));
                product_icon.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("Click");
                        Component[] components = jPanelGioHang.getComponents();
                        if (components.length == 0) {

                            Product p = new Product(product_icon.getSanPhamDTO());
                            jPanelGioHang.add(p);
                            updateTongGia();
                            jPanelGioHang.revalidate();
                            jPanelGioHang.repaint();
                        } else {
                            boolean isExist = false;
                            // Iterate over the components in reverse order
                            for (int i = components.length - 1; i >= 0; i--) {
                                Component component = components[i];
                                if (component instanceof Product) {
                                    Product product = (Product) component;
                                    // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                    if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                        // Tăng số lượng lên
                                        product.tangSoLuong();
                                        updateTongGia();
                                        jPanelGioHang.revalidate();
                                        jPanelGioHang.repaint();
                                        isExist = true;
                                        System.out.println("Sản phẩm đã tồn tại");
                                    }
                                }
                            }

                            if (!isExist) { // Chưa tồn tại
                                Product p = new Product(product_icon.getSanPhamDTO());
                                jPanelGioHang.add(p);
                                updateTongGia();
                                jPanelGioHang.revalidate();
                                jPanelGioHang.repaint();
                                System.out.println("Sản phẩm chưa tồn tại");
                            }
                        }
                    }
                });

                product_icon.getjPanel11().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Component[] components = jPanelGioHang.getComponents();
                        if (components.length == 0) {

                            Product p = new Product(product_icon.getSanPhamDTO());
                            jPanelGioHang.add(p);
                            updateTongGia();
                            jPanelGioHang.revalidate();
                            jPanelGioHang.repaint();
                        } else {
                            boolean isExist = false;
                            for (Component component : components) {
                                if (component instanceof Product) {
                                    Product product = (Product) component;
                                    // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                    if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                        // Tăng số lượng lên
                                        product.tangSoLuong();
                                        updateTongGia();
                                        jPanelGioHang.revalidate();
                                        jPanelGioHang.repaint();
                                        isExist = true;
                                        System.out.println("Sản phẩm đã tồn tại");

                                    }
                                }
                            }

                            if (!isExist) { // Chưa tồn tại
                                Product p = new Product(product_icon.getSanPhamDTO());
                                jPanelGioHang.add(p);
                                updateTongGia();
                                jPanelGioHang.revalidate();
                                jPanelGioHang.repaint();
                                System.out.println("Sản phẩm chưa tồn tại");
                            }
                        }
                    }
                });

                product_icon.getjLabelTen().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Component[] components = jPanelGioHang.getComponents();
                        if (components.length == 0) {

                            Product p = new Product(product_icon.getSanPhamDTO());
                            jPanelGioHang.add(p);
                            updateTongGia();
                            jPanelGioHang.revalidate();
                            jPanelGioHang.repaint();
                        } else {
                            boolean isExist = false;
                            for (Component component : components) {
                                if (component instanceof Product) {
                                    Product product = (Product) component;
                                    // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                    if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                        // Tăng số lượng lên
                                        product.tangSoLuong();
                                        updateTongGia();
                                        jPanelGioHang.revalidate();
                                        jPanelGioHang.repaint();
                                        isExist = true;
                                        System.out.println("Sản phẩm đã tồn tại");

                                    }
                                }
                            }

                            if (!isExist) { // Chưa tồn tại
                                Product p = new Product(product_icon.getSanPhamDTO());
                                jPanelGioHang.add(p);
                                updateTongGia();
                                jPanelGioHang.revalidate();
                                jPanelGioHang.repaint();
                                System.out.println("Sản phẩm chưa tồn tại");
                            }
                        }
                    }
                });

                product_icon.getjLabelGia().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Component[] components = jPanelGioHang.getComponents();
                        if (components.length == 0) {

                            Product p = new Product(product_icon.getSanPhamDTO());
                            jPanelGioHang.add(p);
                            updateTongGia();
                            jPanelGioHang.revalidate();
                            jPanelGioHang.repaint();
                        } else {
                            boolean isExist = false;
                            for (Component component : components) {
                                if (component instanceof Product) {
                                    Product product = (Product) component;
                                    // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                    if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                        // Tăng số lượng lên
                                        product.tangSoLuong();
                                        updateTongGia();
                                        jPanelGioHang.revalidate();
                                        jPanelGioHang.repaint();
                                        isExist = true;
                                        System.out.println("Sản phẩm đã tồn tại");

                                    }
                                }
                            }

                            if (!isExist) { // Chưa tồn tại
                                Product p = new Product(product_icon.getSanPhamDTO());
                                jPanelGioHang.add(p);
                                updateTongGia();
                                jPanelGioHang.revalidate();
                                jPanelGioHang.repaint();
                                System.out.println("Sản phẩm chưa tồn tại");
                            }
                        }
                    }
                });

                product_icon.getjLabelSL().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Component[] components = jPanelGioHang.getComponents();
                        if (components.length == 0) {

                            Product p = new Product(product_icon.getSanPhamDTO());
                            jPanelGioHang.add(p);
                            updateTongGia();
                            jPanelGioHang.revalidate();
                            jPanelGioHang.repaint();
                        } else {
                            boolean isExist = false;
                            for (Component component : components) {
                                if (component instanceof Product) {
                                    Product product = (Product) component;
                                    // TODO: Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
                                    if (product.getSanPhamDTO() == product_icon.getSanPhamDTO()) { // Đã tồn tại
                                        // Tăng số lượng lên
                                        product.tangSoLuong();
                                        updateTongGia();
                                        jPanelGioHang.revalidate();
                                        jPanelGioHang.repaint();
                                        isExist = true;
                                        System.out.println("Sản phẩm đã tồn tại");

                                    }
                                }
                            }

                            if (!isExist) { // Chưa tồn tại
                                Product p = new Product(product_icon.getSanPhamDTO());
                                jPanelGioHang.add(p);
                                updateTongGia();
                                jPanelGioHang.revalidate();
                                jPanelGioHang.repaint();
                                System.out.println("Sản phẩm chưa tồn tại");
                            }
                        }
                    }
                });
                jPanelSanPham.add(product_icon);
            }

        }
        jPanelSanPham.revalidate();
        jPanelSanPham.repaint();
        //---------------------------------------------------------------------------------------------------------
//        String text = jTextField_TimKiem.getText();
//        if (text.length() != 0) {
//            TestProcedure testProcedure = new TestProcedure();
//
//            sanPhamBUS = new SanPhamBUS();
//            SanPhamDTO sp = sanPhamBUS.tim(text);
//            if (sp != null) {
//                Product_icon p = new Product_icon(sp);
//                jPanelSanPham.removeAll();
//                jPanelSanPham.add(p);
//
//            } else {
//                jPanelSanPham.removeAll();
//            }
//            jPanelSanPham.revalidate();
//            jPanelSanPham.repaint();
//            System.out.println("TextField contains text");
//        } else {
//            jPanelSanPham.removeAll();
//            sanPhamBUS = new SanPhamBUS();
//            for (int i = 0; i < sanPhamBUS.getList_SanPhamDTOs().size(); i++) {
//                Product_icon product_icon = new Product_icon(sanPhamBUS.getList_SanPhamDTOs().get(i));
//                jPanelSanPham.add(product_icon);
//            }
//            jPanelSanPham.revalidate();
//            jPanelSanPham.repaint();
//        }
    }// GEN-LAST:event_jTextField_TimKiemActionPerformed

    private void jTextField_TimKiemMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jTextField_TimKiemMouseClicked
        if (isjTextField_TimKiemActionPerformedEnable) {
            jTextField_TimKiem.setText("");
            isjTextField_TimKiemActionPerformedEnable = false;
        }

    }// GEN-LAST:event_jTextField_TimKiemMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
    }// GEN-LAST:event_jLabel4MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jPanel7MouseClicked
        // TODO add your handling code here:

    }// GEN-LAST:event_jPanel7MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jPanel8MouseClicked
        // TODO add your handling code here:
        Product p = new Product();
        jPanelGioHang.add(p);
        jPanelSanPham.revalidate();
        jPanelSanPham.repaint();
    }// GEN-LAST:event_jPanel8MouseClicked

    public void updateTongGia() {
        Integer tongGia = 0;
        Component[] components = jPanelGioHang.getComponents();
        // Iterate over the components in reverse order
        for (int i = components.length - 1; i >= 0; i--) {
            Component component = components[i];

            // Check if the component is an instance of Product
            if (component instanceof Product) {
                Product product = (Product) component;

                tongGia += product.getSl() * Integer.valueOf(product.getSanPhamDTO().getGiaInt());
            }
        }
        jLabel7.setText(tongGia.toString());
        // Update the UI after all products have been removed
        jPanelGioHang.revalidate();
        jPanelGioHang.repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BanHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelGioHang;
    private javax.swing.JPanel jPanelProduct;
    private javax.swing.JPanel jPanelSanPham;
    private javax.swing.JPanel jPanelThanhToan;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField_TimKiem;
    private GUI.PanelRound panelRound1;
    // End of variables declaration//GEN-END:variables
}
