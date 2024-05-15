import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.util.EventListener;
public class TimKiem extends JDialog
{
    private JTextField txt_input;
    private JButton btn_TimKiem;
    private JTable tbl2;
    private JPanel panelB2_tk;
    private DefaultTableModel model;

    public TimKiem( ){
        setContentPane(panelB2_tk);
        setModal(true);
        getRootPane().setDefaultButton(btn_TimKiem);


        loadData();
        txt_input.requestFocus();
        btn_TimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchQuery = txt_input.getText().toLowerCase();
                model = new DefaultTableModel(null, new Object[]{"Mã", "Tiêu đề", "Loại", "Năm xuất bản"});
                tbl2.setModel(model);

                try {
                        timKiem(searchQuery);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error performing search: " + ex.getMessage());
                }
            }
        });



    }
// BÀI 2
//    public void timKiem(String searchQuery) throws SQLException {
//        DBConnect dbConnect = new DBConnect();
//
//        try (ResultSet resultSet = dbConnect.executeQuery("SELECT * FROM CDDVDCollection WHERE Ma LIKE '%" + searchQuery + "%' OR TieuDe LIKE '%" + searchQuery + "%' OR LoaiDia LIKE '%" + searchQuery + "%' OR NamXuatBan LIKE '%" + searchQuery + "%'"))
//        {
//            while (resultSet.next())
//            {
//                Object[] row = new Object[4];
//                row[0] = resultSet.getString("Ma");
//                row[1] = resultSet.getString("TieuDe");
//                row[2] = resultSet.getString("LoaiDia");
//                row[3] = resultSet.getString("NamXuatBan");
//
//                model.addRow(row);
//            }
//        }
//    }


    //BÀI 3
    public void timKiem(String searchQuery) throws SQLException {
        DBConnect dbConnect = new DBConnect();

        // Determine search type based on input format
        if (isNumeric(searchQuery) && searchQuery.length() == 4) {
            // Search by year using stored procedure
            int namXuatBan = Integer.parseInt(searchQuery);
            timKiemTheoNam(namXuatBan);

        }
        //CD
        else if (searchQuery.startsWith("cd "))
        {
            // Search by year and type using stored procedure (assuming "CD YYYY" format)
            String[] splitQuery = searchQuery.split(" ");
            if (splitQuery.length == 2 && isNumeric(splitQuery[1])) {
                int namXuatBan = Integer.parseInt(splitQuery[1]);
                String loaiDia = splitQuery[0].toString(); // Remove "CD" prefix
                timKiemTheoNamXuatBanVaLoaiDia(namXuatBan, loaiDia);
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }

        //DVD
        else if (searchQuery.startsWith("dvd "))
        {
            String[] splitQuery = searchQuery.split(" ");
            if (splitQuery.length == 2 && isNumeric(splitQuery[1])) {
                int namXuatBan = Integer.parseInt(splitQuery[1]);
                String loaiDia = splitQuery[0].toString();
                timKiemTheoNamXuatBanVaLoaiDia(namXuatBan, loaiDia);
            } else {
                // Handle invalid "CD YYYY" format
                JOptionPane.showMessageDialog(null, "Error");
            }
        }
        //từng cái
        else if(searchQuery.startsWith("all")){
            showAll();
        }
        else
        {
            timKiemTheoLoai(searchQuery);
        }
    }
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) return false;
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private void timKiemTheoLoai(String loaiDia) throws SQLException {
        // Validate and search by type (CD or DVD)
        if (loaiDia.equalsIgnoreCase("CD") || loaiDia.equalsIgnoreCase("DVD")) {
            // Use the ShowListCDDVDByLoai stored procedure
            DBConnect dbConnect = new DBConnect();

            try (CallableStatement callableStatement = dbConnect.getConnection().prepareCall("{call ShowListCDDVDByLoai(?)}")) {
                callableStatement.setString(1, loaiDia);
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    // Process the result set
                    while (resultSet.next()) {
                        Object[] row = new Object[4];
                        row[0] = resultSet.getString("Ma");
                        row[1] = resultSet.getString("TieuDe");
                        row[2] = resultSet.getString("LoaiDia");
                        row[3] = resultSet.getString("NamXuatBan");

                        model.addRow(row);
                    }
                }
            }
        } else {
            // Handle invalid type (neither CD nor DVD)
            JOptionPane.showMessageDialog(null, "Invalid type: Please search by 'CD' or 'DVD'.");
        }
    }

    private void timKiemTheoNamXuatBanVaLoaiDia(int namXuatBan, String loaiDia) throws SQLException {

        DBConnect dbConnect = new DBConnect();

        // Prepare and call the stored procedure
        try (CallableStatement callableStatement = dbConnect.getConnection().prepareCall("{call ShowListCDDVDByNamXBAndLoai(?, ?)}")) {
            callableStatement.setInt(1, namXuatBan);
            callableStatement.setString(2, loaiDia);
            try (ResultSet resultSet = callableStatement.executeQuery()) {
                // Process the result set
                while (resultSet.next()) {
                    Object[] row = new Object[4];
                    row[0] = resultSet.getString("Ma");
                    row[1] = resultSet.getString("TieuDe");
                    row[2] = resultSet.getString("LoaiDia");
                    row[3] = resultSet.getString("NamXuatBan");

                    model.addRow(row);
                }
            }
        }
    }
    public void timKiemTheoNam(int namXuatBan) throws SQLException {
        DBConnect dbConnect = new DBConnect();

        // Prepare and call the stored procedure
        try (CallableStatement callableStatement = dbConnect.getConnection().prepareCall("{call ShowListCDDVDByNamXuatBan(?)}")) {
            callableStatement.setInt(1, namXuatBan); // Set the input parameter
            try (ResultSet resultSet = callableStatement.executeQuery()) {
                // Process the result set
                while (resultSet.next()) {
                    Object[] row = new Object[4];
                    row[0] = resultSet.getString("Ma");
                    row[1] = resultSet.getString("TieuDe");
                    row[2] = resultSet.getString("LoaiDia");
                    row[3] = resultSet.getString("NamXuatBan");

                    model.addRow(row);
                }
            }
        }
    }
    public void showAll() throws SQLException {
        DBConnect dbConnect = new DBConnect();

        // Prepare and call the stored procedure
        try (CallableStatement callableStatement = dbConnect.getConnection().prepareCall("{call ShowListCDDVD()}")) {
            try (ResultSet resultSet = callableStatement.executeQuery()) {
                // Process the result set
                while (resultSet.next()) {
                    Object[] row = new Object[4];
                    row[0] = resultSet.getString("Ma");
                    row[1] = resultSet.getString("TieuDe");
                    row[2] = resultSet.getString("LoaiDia");
                    row[3] = resultSet.getString("NamXuatBan");

                    model.addRow(row);
                }
            }
        }
    }





    public void loadData() {
        new DBConnect();

        try {
            String sql = "select * from CDDVDCollection";
            ResultSet resultSet = DBConnect.getInstance().executeQuery(sql);

            // Set column titles before adding data rows
            DefaultTableModel model = new DefaultTableModel(null, new Object[]{"Mã", "Tiêu đề", "Loại", "Năm xuất bản"});
            tbl2.setModel(model);

            while (resultSet.next()) {
                Object[] row = new Object[4];
                row[0] = resultSet.getString("Ma");
                row[1] = resultSet.getString("TieuDe");
                row[2] = resultSet.getString("LoaiDia");
                row[3] = resultSet.getString("NamXuatBan");

                // Add data rows after setting column titles
                model.addRow(row);
            }

            resultSet.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        TimKiem dialog = new TimKiem();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }


}
