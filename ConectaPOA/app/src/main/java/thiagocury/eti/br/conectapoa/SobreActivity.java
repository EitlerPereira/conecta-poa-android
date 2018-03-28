package thiagocury.eti.br.conectapoa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class SobreActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Drawer result = null;
    private MapsActivity latlngzoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        toolbar = (Toolbar) findViewById(R.id.toolbar_sobre);
        setSupportActionBar(toolbar);

        //##### MENU #####

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.banner)
                .build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(getResources().getString(R.string.redes_wifi)).withIdentifier(0).withIcon(R.drawable.ic_wifi_black_24dp),
                        new PrimaryDrawerItem().withName(getResources().getString(R.string.mapa)).withIdentifier(1).withIcon(R.drawable.ic_place_black_24dp),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(getResources().getString(R.string.sobre)).withIdentifier(2).withIcon(R.drawable.ic_info_black_24dp),
                        new PrimaryDrawerItem().withName(getResources().getString(R.string.sair)).withIdentifier(3).withIcon(R.drawable.ic_exit_to_app)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        switch ((int)drawerItem.getIdentifier()){
                            case 0:
                                finish();
                                break;

                            case 1:
                                int zoom = 11;
                                double lat = -30.0582850;
                                double lng = -51.1787050;
                                Intent it = new Intent(SobreActivity.this,MapsActivity.class);
                                it.putExtra("zoom",zoom);
                                it.putExtra("lat",lat);
                                it.putExtra("lng",lng);
                                startActivity(it);
                                finish();
                                break;

                            case 2:

                                break;

                            case 3:
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("SAIR",true);
                                startActivity(intent);
                                break;
                        }
                        return false;
                    }
                }).build();
        result.setSelection(2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        result.setSelection(2);
    }
}
