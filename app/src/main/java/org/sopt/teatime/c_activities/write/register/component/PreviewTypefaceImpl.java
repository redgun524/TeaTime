package org.sopt.teatime.c_activities.write.register.component;

import android.graphics.Typeface;

import org.sopt.teatime.a_others.function.FontController;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 품파파품파 on 2016-07-08.
 */
public class PreviewTypefaceImpl implements PreviewTypeface, Serializable {

    ArrayList<Typeface> typefaces;

    public PreviewTypefaceImpl(ArrayList<Typeface> typefaces) {
        this.typefaces = typefaces;
    }

    @Override
    public Typeface getTypeFace(String path) {
        switch (path) {
            case FontController.PATH_NANUM_GOTHIC :
                return typefaces.get(0);
            case FontController.PATH_NANUM_GOTHIC_BOLD :
                return typefaces.get(1);
            case FontController.PATH_NANUM_BARUN_GOTHIC :
                return typefaces.get(2);
            case FontController.PATH_NANUM_MYEONGJO :
                return typefaces.get(3);
            case FontController.PATH_NANUM_BRUSH :
                return typefaces.get(4);
            case FontController.PATH_NANUM_PEN :
                return typefaces.get(5);
            case FontController.PATH_JUA :
                return typefaces.get(6);
            default:
                return typefaces.get(0);
        }
    }
/*
    public static final Creator<PreviewTypefaceImpl> CREATOR = new Creator<PreviewTypefaceImpl>() {
        @Override
        public PreviewTypefaceImpl createFromParcel(Parcel in) {
            return new PreviewTypefaceImpl(in);
        }

        @Override
        public PreviewTypefaceImpl[] newArray(int size) {
            return new PreviewTypefaceImpl[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(typefaces);
    }

    protected PreviewTypefaceImpl(Parcel in) {
        typefaces = in.readArrayList(PreviewTypefaceImpl.class.getClassLoader());
    }*/
}
