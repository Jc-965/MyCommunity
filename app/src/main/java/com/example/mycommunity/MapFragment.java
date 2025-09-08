package com.example.mycommunity;

import static com.example.mycommunity.MyTroopActivity.sAddress;
import static com.example.mycommunity.MyTroopActivity.sName;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class MapFragment extends Fragment {
    String LatLong;
    Integer index;
    public static Double Latitude, Longitude;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container,false);
        SupportMapFragment map = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_map);

//        map.getMapAsync(new OnMapReadyCallback() {
//
//            @Override
//            public void onMapReady(@NonNull GoogleMap googleMap) {
//
//                DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Troop Details");
//                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity().getBaseContext());
//                db = db.child(acct.getEmail().replaceAll("[.#$]" , ","));
//
//                ValueEventListener postListener = new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        HashMap o = (HashMap) dataSnapshot.getValue();
//                        if(o != null){
//                            LatLong = (String) o.get("Troop Address (Latitude, Longitude)");
//                            System.out.println(LatLong);
//                            index = LatLong.indexOf(',');
//                            Latitude = Double.parseDouble(LatLong.substring(0,index));
//                            Longitude = Double.parseDouble(LatLong.substring(index+2));
//
//                            MarkerOptions marker = new MarkerOptions();
//                            LatLng p = new LatLng(Latitude, Longitude);
//                            marker.position(p);
//                            marker.title("Your Troop");
//                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(p, 50));
//                            googleMap.addMarker(marker);
//                        }
//
//                        else {
//                            MarkerOptions marker = new MarkerOptions();
//                            LatLng p = new LatLng(34.052235, -118.243683);
//                            marker.position(p);
//                            marker.title("LA");
//                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(p, 50));
//                            googleMap.addMarker(marker);
//                        }
//
//
//
//                        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//
//                            @Override
//                            public void onMapClick(@NonNull LatLng latLng) {
//                                MarkerOptions marker = new MarkerOptions();
//
//                                marker.position(latLng);
//                                marker.title(latLng.latitude + "" + latLng.longitude);
//
//                                //googleMap.clear();
//                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 50));
//                                googleMap.addMarker(marker);
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                    }
//                };
//                db.addValueEventListener(postListener);
//            }
//
//        });

        return view;
    }
}