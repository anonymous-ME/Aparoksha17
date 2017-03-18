package com.betterclever.aparoksha.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.betterclever.aparoksha.R;
import com.betterclever.aparoksha.activities.EventDetailActivity;
import com.betterclever.aparoksha.model.Organizer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrganizerFragment extends Fragment {
    
    private static final String TAG = OrganizerFragment.class.getSimpleName() ;
    ScrollView scrollView;
    LinearLayout scrollableLayout;
    List<Organizer> organizers = new ArrayList<>();
    
    public OrganizerFragment() {
    }
    
    public static OrganizerFragment newInstance() {
        
        Bundle args = new Bundle();
        OrganizerFragment fragment = new OrganizerFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        View v = inflater.inflate(R.layout.fragment_organizer, container, false);
        scrollView = (ScrollView) v.findViewById(R.id.scrollView);
        scrollableLayout = (LinearLayout) v.findViewById(R.id.scrollViewLL);
    
        Log.d(TAG, "onCreateView() called with: inflater = [" + inflater + "], container = [" + container + "], savedInstanceState = [" + savedInstanceState + "]");
        for(Organizer organizer: organizers){
        
            View child = inflater.inflate(R.layout.card_organizer,scrollView,false);
        
            TextView organizerName = (TextView) child.findViewById(R.id.organizer_name);
            TextView organizerPhone = (TextView) child.findViewById(R.id.organizer_phone);
            Button callButton = (Button) child.findViewById(R.id.call_button);
        
            organizerName.setText(organizer.getName());
            organizerPhone.setText(organizer.getContact());
        
            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: Perform Call
                }
            });
        
            scrollableLayout.addView(child);
        }
        return v;
    }
    
    public void update(List<Organizer> organizers) {

        if(organizers == null){
            return;
        }
        
        if(scrollableLayout!=null ) {
            
            scrollableLayout.removeAllViews();
            this.organizers.clear();
            this.organizers.addAll(organizers);
    
            LayoutInflater inflater = LayoutInflater.from(getContext());
            for (Organizer organizer : organizers) {
        
                final String number = organizer.getContact();
                View child = inflater.inflate(R.layout.card_organizer, scrollView, false);
        
                TextView organizerName = (TextView) child.findViewById(R.id.organizer_name);
                TextView organizerPhone = (TextView) child.findViewById(R.id.organizer_phone);
                Button callButton = (Button) child.findViewById(R.id.call_button);
        
                organizerName.setText(organizer.getName());
                organizerPhone.setText(organizer.getContact());
                Log.d(TAG, "update: "+ organizer.getContact());
                
                callButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((EventDetailActivity) getActivity()).call(number);
                    }
                });
        
                scrollableLayout.addView(child);
            }
        }
        
    }
}
