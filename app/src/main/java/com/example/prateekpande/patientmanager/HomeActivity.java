package com.example.prateekpande.patientmanager;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.prateekpande.patientmanager.fragment.AddAppointment;
import com.example.prateekpande.patientmanager.fragment.ExistingAppointment;
import com.example.prateekpande.patientmanager.model.Appointment;

public class HomeActivity extends AppCompatActivity implements AddAppointment.OnFragmentInteractionListener, ExistingAppointment.OnFragmentInteractionListener {

    private ListView leftDrawerListView;
    private String[] leftDrawerItem;
    private Fragment fragment;
    private DrawerLayout leftDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        leftDrawerListView = (ListView) findViewById(R.id.leftDrawerListView);
        leftDrawerItem = getResources().getStringArray(R.array.navigation_items);
        leftDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        populateDrawerListView();
    }

    public void populateDrawerListView(){

        //set adapter
        leftDrawerListView.setAdapter(new ArrayAdapter<String>(this,R.layout.text_view_drawer_left,leftDrawerItem));
        leftDrawerListView.setOnItemClickListener(new DrawerItemClickListener());
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            launchViewOnItemClick(position);
        }
    }

    /**
     * This method is responsible for directing to
     * a view as per list item selection.
     * @param position
     */
    private void launchViewOnItemClick(int position){

        switch(position){

            case 0:{
                // existing appointment view
                break;
            }
            case 1:{
                // new appointment
                fragment = new AddAppointment();
                break;
            }
            case 2:{
                //existing appointment
                fragment= new ExistingAppointment();
                break;
            }
            case 3:{
                //
                break;
            }
        }
        if(fragment!=null) {
//            setTitle(leftDrawerItem[position]);
            replaceFragment(fragment);
            leftDrawerLayout.closeDrawers();
        }
    }

    /**
     * This method replaces fragment
     * @param fragment
     */
    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainFragment,fragment)
                .commit();
    }

    @Override
    public void onAddApointmentFragmentInteraction(Appointment appointment) {

    }

    @Override
    public void onExistingAppointmentFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
