package com.devan.arcore.project.ui.main;

import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.devan.arcore.project.Distance.distance;
import com.devan.arcore.project.R;
import com.devan.arcore.project.RecycleView.random.randomset;
import com.devan.arcore.project.drawing.DrawingActivity;
import com.devan.arcore.project.planet.SolarActivity;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link// DummyItem} and makes a call to the
 * specified {@link //OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyrandomRecyclerViewAdapter extends RecyclerView.Adapter<MyrandomRecyclerViewAdapter.ViewHolder> {

    //private final List<DummyItem> mValues;
    private final List<randomset> randomsetList;
    //private final OnListFragmentInteractionListener mListener;

    public MyrandomRecyclerViewAdapter(List<randomset> items/*, OnListFragmentInteractionListener listener*/) {
        randomsetList = items;
      //  mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
       /* holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
        */
       holder.title.setText(randomsetList.get(position).getTitle());
       holder.subtitle.setText(randomsetList.get(position).getSubtitle());
       holder.image.setImageResource(randomsetList.get(position).getImage());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==0){
                    Intent intent=new Intent(v.getContext(), DrawingActivity.class);
                    v.getContext().startActivity(intent);
                }
                else if(position==1){
                    Intent intent=new Intent(v.getContext(), SolarActivity.class);
                    v.getContext().startActivity(intent);
                }
                else if(position==2){
                    Intent intent=new Intent(v.getContext(), distance.class);
                    v.getContext().startActivity(intent);
                }
                else {
                    Intent intent=new Intent(v.getContext(),AR_activity_random.class);
                    intent.putExtra("prandom",position);
                    v.getContext().startActivity(intent);

                }

            }

        });
        holder.mView.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ObjectAnimator downAnimator = ObjectAnimator.ofFloat(view, "translationZ", 16);
                        downAnimator.setDuration(200);
                        downAnimator.setInterpolator(new DecelerateInterpolator());
                        downAnimator.start();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        ObjectAnimator upAnimator = ObjectAnimator.ofFloat(view, "translationZ", 0);
                        upAnimator.setDuration(200);
                        upAnimator.setInterpolator(new AccelerateInterpolator());
                        upAnimator.start();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return randomsetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title;
        public final TextView subtitle;
        public ImageView image;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = view.findViewById(R.id.tv_card_1_title);
            subtitle = view.findViewById(R.id.tv_card_1_subtitle);
            image=view.findViewById(R.id.img_card_1);
        }
    }

}
