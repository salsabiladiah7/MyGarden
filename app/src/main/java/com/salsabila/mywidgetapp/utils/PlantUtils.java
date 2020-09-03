package com.salsabila.mywidgetapp.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.salsabila.mywidgetapp.R;

public class PlantUtils {

    private static final long MINUTE_MILLISECONDS = 1000 * 60;
    private static final long HOUR_MILLISECONDS = MINUTE_MILLISECONDS * 60;
    public static final long MIN_AGE_BETWEEN_WATER = HOUR_MILLISECONDS * 2; // can water every 2 hours
    static final long DANGER_AGE_WITHOUT_WATER = HOUR_MILLISECONDS * 6; // in danger after 6 hours
    public static final long MAX_AGE_WITHOUT_WATER = HOUR_MILLISECONDS * 12; // plants die after 12 hours
    private static final long DAY_MILLISECONDS = HOUR_MILLISECONDS * 24;
    static final long TINY_AGE = DAY_MILLISECONDS * 0; // plants start tiny
    static final long JUVENILE_AGE = DAY_MILLISECONDS * 1; // 1 day old
    static final long FULLY_GROWN_AGE = DAY_MILLISECONDS * 2; // 2 days old

    public static int getPlantImageRes(Context context, long plantAge, long waterAge, int type) {
        PlantStatus status = PlantStatus.ALIVE;
        if (waterAge > MAX_AGE_WITHOUT_WATER) status = PlantStatus.DEAD;
        else if (waterAge > DANGER_AGE_WITHOUT_WATER) status = PlantStatus.DYING;

        if (plantAge > FULLY_GROWN_AGE) {
            return getPlantImgRes(context, type, status, PlantSize.FULLY_GROWN);
        } else if (plantAge > JUVENILE_AGE) {
            return getPlantImgRes(context, type, status, PlantSize.JUVENILE);
        } else if (plantAge > TINY_AGE) {
            return getPlantImgRes(context, type, status, PlantSize.TINY);
        } else {
            return R.drawable.empty_pot;
        }
    }

    public static int getPlantImgRes(Context context, int type, PlantStatus status, PlantSize size) {
        Resources res = context.getResources();
        TypedArray plantTypes = res.obtainTypedArray(R.array.plant_types);
        String resName = plantTypes.getString(type);
        if (status == PlantStatus.DYING) resName += "_danger";
        else if (status == PlantStatus.DEAD) resName += "_dead";
        if (size == PlantSize.TINY) resName += "_1";
        else if (size == PlantSize.JUVENILE) resName += "_2";
        else if (size == PlantSize.FULLY_GROWN) resName += "_3";
        return context.getResources().getIdentifier(resName, "drawable", context.getPackageName());
    }

    public static String getPlantTypeName(Context context, int type) {
        Resources res = context.getResources();
        TypedArray plantTypes = res.obtainTypedArray(R.array.plant_types);
        String resName = plantTypes.getString(type);
        int resId = context.getResources().getIdentifier(resName, "string", context.getPackageName());
        try {
            return context.getResources().getString(resId);
        } catch (Resources.NotFoundException ex) {
            return context.getResources().getString(R.string.unknown_type);
        }
    }

    public static int getDisplayAgeInt(long milliSeconds) {
        int days = (int) (milliSeconds / DAY_MILLISECONDS);
        if (days >= 1) return days;
        int hours = (int) (milliSeconds / HOUR_MILLISECONDS);
        if (hours >= 1) return hours;
        return (int) (milliSeconds / MINUTE_MILLISECONDS);
    }

    public static String getDisplayAgeUnit(Context context, long milliSeconds) {
        int days = (int) (milliSeconds / DAY_MILLISECONDS);
        if (days >= 1) return context.getString(R.string.days);
        int hours = (int) (milliSeconds / HOUR_MILLISECONDS);
        if (hours >= 1) return context.getString(R.string.hours);
        return context.getString(R.string.minutes);
    }

    public enum PlantStatus {ALIVE, DYING, DEAD}

    public enum PlantSize {TINY, JUVENILE, FULLY_GROWN}
}
