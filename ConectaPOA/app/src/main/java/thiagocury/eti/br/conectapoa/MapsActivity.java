package thiagocury.eti.br.conectapoa;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<Wifi> wf = MainActivity.wf;
    private ClusterManager<Wifi> cluster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //##### PERMISSÃO LOCAL #####

        ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);

        if (ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(
                        getBaseContext(),
                        getResources().getString(R.string.explicacao1) +
                                "\n" + getResources().getString(R.string.explicacao2),
                        Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            } else {
                ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //##### CLUSTER #####

        cluster = new ClusterManager<Wifi>(this, mMap);

        mMap.setOnCameraIdleListener(cluster);
        mMap.setOnMarkerClickListener(cluster);

        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        if (ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        cluster.addItems(wf);

        cluster.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<Wifi>() {
            @Override
            public boolean onClusterItemClick(Wifi wifi) {

                Toast.makeText(getBaseContext(), wifi.getNomeRede(), Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        final CustomClusterRenderer renderer = new CustomClusterRenderer(this, mMap, cluster);
        cluster.setRenderer(renderer);

        //##### CÂMERA INICIAL COM LAT, LNG E ZOOM VARIÁVEIS #####

        int zoom = (int) getIntent().getSerializableExtra("zoom");
        double lat = (double) getIntent().getSerializableExtra("lat");
        double lng = (double) getIntent().getSerializableExtra("lng");

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lng),zoom));
    }

}
