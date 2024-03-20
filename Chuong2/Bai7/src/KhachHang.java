public class KhachHang {
    private String MaKH;
    private String TenKH;
    private String SDT;
    private String Email;

    public String getMaKH()
    {
        return MaKH;
    }
    public void setMaKH(String maKH)
    {
        this.MaKH=maKH;
    }

    public String getTenKH()
    {
        return TenKH;
    }
    public void setTenKH(String tenKH)
    {
        this.TenKH=tenKH;
    }

    public String getSDT()
    {
        return SDT;
    }
    public void setSDT(String sdt)
    {
        this.SDT=sdt;
    }

    public String getEmail()
    {
        return Email;
    }
    public void setEmail(String email)
    {
        this.Email=email;
    }

    public KhachHang()
    {

    }

    public KhachHang(String maKH, String tenKH, String sdt, String email)
    {
        setMaKH(maKH);
        setTenKH(tenKH);
        setSDT(sdt);
        setEmail(email);
    }

}
