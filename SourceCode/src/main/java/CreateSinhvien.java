public class CreateSinhvien {
    public static void main(String[] args) {
        // for (int i = 1; i <= 20; i++) {
        // if (i < 10) {
        // System.out.println("-- Create user0" + i);
        // System.out.println("CREATE USER user0" + i + " IDENTIFIED BY abc;");
        // System.out.println("GRANT CONNECT TO user0" + i + ";\n");
        // } else {
        // System.out.println("-- Create user" + i);
        // System.out.println("CREATE USER user" + i + " IDENTIFIED BY abc;");
        // System.out.println("GRANT CONNECT TO user" + i + ";\n");
        // }
        // }
        for (int i = 1; i <= 20; i++) {
            if (i < 10) {
                System.out.println("GRANT Role_QUANTRI to user0" + i + ";");
            } else {
                System.out.println("GRANT Role_NGUOIDUNG to user" + i + ";");
            }

        }
    }
}
