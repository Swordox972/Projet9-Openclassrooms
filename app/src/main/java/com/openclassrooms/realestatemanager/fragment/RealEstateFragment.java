package com.openclassrooms.realestatemanager.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.openclassrooms.realestatemanager.adapter.MyRealEstateRecyclerViewAdapter;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.databinding.FragmentRealEstateBinding;
import com.openclassrooms.realestatemanager.event.OpenRealEstateEvent;
import com.openclassrooms.realestatemanager.model.RealEstate;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class RealEstateFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private RealEstateViewModel viewModel;

    private MyRealEstateRecyclerViewAdapter adapter;

    public static final String KEY = "RealEstateClicked";

    public RealEstateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_real_estate_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        adapter = new MyRealEstateRecyclerViewAdapter();
        mRecyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(RealEstateViewModel.class);
        viewModel.list.observe(getViewLifecycleOwner(), new Observer<List<RealEstate>>() {
            @Override
            public void onChanged(List<RealEstate> realEstates) {
                adapter.setRealEstateList(realEstates);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onOpenRealEstate(OpenRealEstateEvent event) {
        RealEstate mRealEstate = event.mRealEstate;

        OnClickRealEstateFragment onClickRealEstateFragment = new OnClickRealEstateFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY, mRealEstate);
        onClickRealEstateFragment.setArguments(args);

        Fragment fragmentContainerViewDetail =  getParentFragmentManager().findFragmentById(
                R.id.activity_main_fragment_container_view_detail);


        //Ensure that real estate is not null
        if (mRealEstate != null && fragmentContainerViewDetail ==null ) {
           getParentFragmentManager()
                   .beginTransaction()
                   .replace(R.id.activity_main_fragment_container_view_list,
                           onClickRealEstateFragment)
                   .addToBackStack(OnClickRealEstateFragment.class.getSimpleName())
                   .commit();
        }
        else if (mRealEstate != null && fragmentContainerViewDetail.isVisible()) {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_main_fragment_container_view_detail,
                            onClickRealEstateFragment)
                    .commit();
        }
    }
}