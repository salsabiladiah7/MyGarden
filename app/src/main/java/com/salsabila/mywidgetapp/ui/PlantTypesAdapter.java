package com.salsabila.mywidgetapp.ui;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.salsabila.mywidgetapp.R;
import com.salsabila.mywidgetapp.utils.PlantUtils;

public class PlantTypesAdapter extends RecyclerView.Adapter<PlantTypesAdapter.PlantViewHolder> {

    Context mContext;
    TypedArray mPlantTypes;

    public PlantTypesAdapter(Context context) {
        mContext = context;
        Resources res = mContext.getResources();
        mPlantTypes = res.obtainTypedArray(R.array.plant_types);
    }

    @Override
    public PlantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.plant_types_list_item, parent, false);
        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlantViewHolder holder, int position) {
        int imgRes = PlantUtils.getPlantImgRes(
                mContext, position,
                PlantUtils.PlantStatus.ALIVE,
                PlantUtils.PlantSize.FULLY_GROWN);
        holder.plantImageView.setImageResource(imgRes);
        holder.plantTypeText.setText(PlantUtils.getPlantTypeName(mContext, position));
        holder.plantImageView.setTag(position);
    }

    @Override
    public int getItemCount() {
        if (mPlantTypes == null) return 0;
        return mPlantTypes.length();
    }

    class PlantViewHolder extends RecyclerView.ViewHolder {

        ImageView plantImageView;
        TextView plantTypeText;

        public PlantViewHolder(View itemView) {
            super(itemView);
            plantImageView = (ImageView) itemView.findViewById(R.id.plant_type_image);
            plantTypeText = (TextView) itemView.findViewById(R.id.plant_type_text);
        }

    }
}
