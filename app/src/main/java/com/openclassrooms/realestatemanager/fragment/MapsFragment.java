package com.openclassrooms.realestatemanager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.service.MyRealEstateHandlerThread;

import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private RealEstateViewModel viewModel;
    private GoogleMap mMap;
    public static final String MAPS_MARKER_CLICK_REAL_ESTATE = "MAPS_MARKER_CLICK_REAL_ESTATE";
    private List<RealEstate> realEstateList;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng newYork = new LatLng(40.75691, -73.97619);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newYork, 11));

        realEstateList = new ArrayList<>();

        viewModel = new ViewModelProvider(this).get(RealEstateViewModel.class);

        viewModel.getRealEstates().observe(getViewLifecycleOwner(), realEstates -> {
            for (RealEstate realEstate : realEstates) {
                String realEstateId = Long.toString(realEstate.getId());
                LatLng latLng = new LatLng(realEstate.getLatitude(), realEstate.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title(realEstateId));

                realEstateList.add(realEstate);
            }

            mMap.setOnMarkerClickListener(marker -> {
                for (int i = 0; i < realEstateList.size(); i++) {
                    String realEstateId = Long.toString(realEstateList.get(i).getId());
                    if (marker.getTitle().equals(realEstateId)) {
                        OnClickRealEstateFragment onClickRealEstateFragment = new
                                OnClickRealEstateFragment();
                        Bundle args = new Bundle();
                        args.putSerializable(MAPS_MARKER_CLICK_REAL_ESTATE, realEstateList.get(i));
                        onClickRealEstateFragment.setArguments(args);

                        Fragment fragmentContainerViewDetail = getParentFragmentManager()
                                .findFragmentById(R.id.activity_main_fragment_container_view_detail);
                        if (fragmentContainerViewDetail == null) {
                            getParentFragmentManager().beginTransaction()
                                    .addToBackStack("MapsFragment")
                                    .replace(R.id.activity_main_fragment_container_view_list,
                                            onClickRealEstateFragment)
                                    .commit();
                        } else if (fragmentContainerViewDetail.isVisible()) {
                            getParentFragmentManager().beginTransaction()
                                    .addToBackStack("MapsFragment")
                                    .replace(R.id.activity_main_fragment_container_view_detail,
                                            onClickRealEstateFragment)
                                    .commit();
                        }
                    }
                }
                return true;
            });
    });
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_maps, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}