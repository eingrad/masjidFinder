package com.example.levonovo.peta;


public class tmptdftr {
    private String Nama;
    private String Jalan;
    private String Kota;
    private int Logo;

    public tmptdftr(String nama,String jalan,String kota,int logo){
        Nama = nama;
        Jalan = jalan;
        Kota = kota;
        Logo = logo;
    }

    public String getNama (){ return Nama;}
    public String getJalan(){ return Jalan;}
    public String getKota(){ return Kota;}
    public int getLogo    (){ return Logo;}
    public void setNama( String nama){ Nama=nama;}
    public void setJalan( String jalan){ Jalan=jalan;}
    public void setKota( String kota){ Kota=kota;}
    public void setLogo( int logo){Logo=logo;}
}
