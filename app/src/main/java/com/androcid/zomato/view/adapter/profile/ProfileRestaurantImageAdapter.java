package com.androcid.zomato.view.adapter.profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androcid.zomato.R;
import com.androcid.zomato.model.RestaurantImage;
import com.androcid.zomato.model.RestaurantItem;
import com.androcid.zomato.retro.RetroInterface;
import com.androcid.zomato.util.CommonFunctions;
import com.androcid.zomato.util.MyFont;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileRestaurantImageAdapter extends RecyclerView.Adapter<ProfileRestaurantImageAdapter.ViewHolder> {

    Context context;
    List<RestaurantImage> mList;
    MyFont myFont;
    //FOR NEW ACTIVITY
    private ClickListener clickListener;

    public ProfileRestaurantImageAdapter(Context context, List<RestaurantImage> list) {
        this.mList = list;
        this.context = context;
        myFont = new MyFont(context);
    }

    @Override
    public ProfileRestaurantImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_profile_restaurant_image_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProfileRestaurantImageAdapter.ViewHolder holder, final int position) {

        RestaurantImage item = mList.get(position);
        RestaurantItem restaurantItem = item.getRestaurant();

        holder.place_name.setText(restaurantItem.getName());
        holder.place_location.setText(restaurantItem.getLocation());

        if (!CommonFunctions.checkNull(restaurantItem.getImage()).equals("")) {
            Picasso.with(context)
                    .load(RetroInterface.IMAGE_URL + restaurantItem.getImage())
                    .resize(200, 200)
                    .placeholder(R.drawable.placeholder_200)
                    .error(R.drawable.placeholder_200)
                    .into(holder.place_image);
        }

        if (!CommonFunctions.checkNull(item.getImage()).equals("")) {

            Picasso.with(context)
                    .load(RetroInterface.IMAGE_URL + item.getImage())
                   // .resize(600, 600)
                    .placeholder(R.drawable.placeholder_200)
                    .error(R.drawable.placeholder_200)
                    .into(holder.image);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (clickListener != null) {
                    clickListener.onItemClickListener(view, position);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void refresh(List<RestaurantImage> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public interface ClickListener {
        public void onItemClickListener(View v, int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView place_image;
        TextView place_name;
        TextView place_location;

        ImageView image;
        TextView created_time;
        TextView imageLikeCount;

        public ViewHolder(View itemView) {
            super(itemView);

            place_image = (ImageView) itemView.findViewById(R.id.place_image);
            place_name = (TextView) itemView.findViewById(R.id.place_name);
            place_location = (TextView) itemView.findViewById(R.id.place_location);

            image = (ImageView) itemView.findViewById(R.id.image);
            created_time = (TextView) itemView.findViewById(R.id.created_time);
            imageLikeCount = (TextView) itemView.findViewById(R.id.imageLikeCount);

            myFont.setAppFont((ViewGroup) itemView, MyFont.FONT_REGULAR);

        }
    }

}
