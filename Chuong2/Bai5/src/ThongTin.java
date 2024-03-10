import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.swing.JOptionPane.showMessageDialog;

public class ThongTin {

    JFrame frame = new JFrame("Bai5");
    private String name;
    private String dateofbirth;
    private String email;
    private String sex;
    private String province;

    public ThongTin() {

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getSex() {
        return sex;
    }

    public String getProvince() {
        return province;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public ThongTin(String name, String dateofbirth, String email, String sex, String province) {
        this.name = name;
        this.dateofbirth = dateofbirth;
        this.email = email;
        this.sex = sex;
        this.province = province;
    }


}

