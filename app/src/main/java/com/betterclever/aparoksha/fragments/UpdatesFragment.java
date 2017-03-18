package com.betterclever.aparoksha.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.betterclever.aparoksha.R;
import com.betterclever.aparoksha.activities.EventDetailActivity;
import com.betterclever.aparoksha.model.Update;
import com.betterclever.aparoksha.viewholder.UpdateViewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class UpdatesFragment extends Fragment {
    
    RecyclerView recyclerView;
    boolean isEventSpecific = false;
    
    public UpdatesFragment() {
        
    }
    
    public static UpdatesFragment newInstance() {
        
        Bundle args = new Bundle();
        args.putBoolean("isEventSpecific",false);
        UpdatesFragment fragment = new UpdatesFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    public static UpdatesFragment newInstance(String eventID) {
        
        Bundle args = new Bundle();
        args.putBoolean("isEventSpecific",true);
        args.putString("eventId",eventID);
        UpdatesFragment fragment = new UpdatesFragment();
        fragment.setArguments(args);
        return fragment;
        
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_updates, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        
        isEventSpecific = getArguments().getBoolean("isEventSpecific");
        
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("updates");
        Query query = ref;
        
        if(isEventSpecific){
            String eventID = getArguments().getString("eventId");
            query = ref.orderByChild("eventID").equalTo(eventID);
        }
        
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Update,UpdateViewholder>(
            Update.class,
            R.layout.update_card,
            UpdateViewholder.class,
            query
        ) {
            @Override
            protected void populateViewHolder(UpdateViewholder viewHolder, final Update model, int position) {
                viewHolder.setData(model);
                
                if(!isEventSpecific) {
                    viewHolder.getRootView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            
                            if(model.getEventID().equals("100")){
                                return;
                            }
                            
                            Intent intent = new Intent(getActivity(), EventDetailActivity.class);
                            intent.putExtra("eventID", model.getEventID());
                            startActivity(intent);
                            ((Activity) getContext()).overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    
                        }
                    });
                }
                
            }
        };
    
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        
        return v;
    }
    
}
