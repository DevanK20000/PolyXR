package com.devan.arcore.project.ui.main;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.devan.arcore.project.R;
import com.devan.arcore.project.RecycleView.product.productset;

import java.util.List;

import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

//import com.devan.arcore.project.ui.main.randomFragment.OnListFragmentInteractionListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link} and makes a call to the
 * specified {@link //OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyproductRecyclerViewAdapter extends RecyclerView.Adapter<MyproductRecyclerViewAdapter.ViewHolder> {

    //private final List<DummyItem> mValues;
    private final List<productset> productsetList;
    //private final OnListFragmentInteractionListener mListener;

    public MyproductRecyclerViewAdapter(List<productset> items/*, OnListFragmentInteractionListener listener*/) {
         productsetList = items;
      //  mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
       holder.title.setText(productsetList.get(position).getTitle());
       holder.subtitle.setText(productsetList.get(position).getSubtitle());
       holder.image.setImageResource(productsetList.get(position).getImage());
       holder.description.setText(productsetList.get(position).getDescription());
       holder.link.setText(productsetList.get(position).getLink());

        holder.mView.setOnClickListener(v -> {

            Intent intent=new Intent(v.getContext(),ScrollingActivityProduct.class);
            intent.putExtra("id",position);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) v.getContext() ,holder.image,"MainImage");
            v.getContext().startActivity(intent,options.toBundle());

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
        return productsetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title;
        public final TextView subtitle;
        public ImageView image;
        public final TextView description;
        public final TextView link;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = view.findViewById(R.id.tv_card_2_title);
            subtitle = view.findViewById(R.id.tv_card_2_subtitle);
            image=view.findViewById(R.id.img_card_2);
            description=view.findViewById(R.id.description);
            link=view.findViewById(R.id.link);
        }
    }

}
