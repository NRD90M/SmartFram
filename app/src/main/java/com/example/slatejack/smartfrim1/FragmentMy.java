package com.example.slatejack.smartfrim1;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
/*public class FragmentMy extends PreferenceFragmentCompat {*///只有当确认最后一页为设置页时将此行取消注释，并将下面19行定义的Fragemet删除

    public class FragmentMy extends Fragment{
    /*@Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource( R.xml.activity_settings,rootKey );
    }*///只有确认将最后一页为设置界面时将此处取消注释


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View txview=inflater.inflate( R.layout.fragment_fragment_my, container, false );
        return txview;
    }
}
