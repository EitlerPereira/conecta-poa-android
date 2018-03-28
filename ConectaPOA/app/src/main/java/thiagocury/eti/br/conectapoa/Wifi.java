package thiagocury.eti.br.conectapoa;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class Wifi implements ClusterItem{

    private String Empresa;
    private String Site;
    private String NomeRede;
    private String Endereco;
    private double Longitude;
    private double Latitude;
    private String Setor;

    public Wifi() {
    }

    public Wifi(String empresa, String site, String nomeRede, String endereco, double longitude, double latitude, String setor) {
        Empresa = empresa;
        Site = site;
        NomeRede = nomeRede;
        Endereco = endereco;
        Longitude = longitude;
        Latitude = latitude;
        Setor = setor;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String empresa) {
        Empresa = empresa;
    }

    public String getSite() {
        return Site;
    }

    public void setSite(String site) {
        Site = site;
    }

    public String getNomeRede() {
        return NomeRede;
    }

    public void setNomeRede(String nomeRede) {
        NomeRede = nomeRede;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public String getSetor() {
        return Setor;
    }

    public void setSetor(String setor) {
        Setor = setor;
    }

    //LATITUDE E LONGITUDE PARA O CLUSTER

    @Override
    public LatLng getPosition() {

        LatLng pos = new LatLng(getLatitude(),getLongitude());

        return pos;
    }
}
