package com.ustc.lifeasst.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ustc.lifeasst.R;
import com.ustc.lifeasst.utils.UIUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SpitFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SpitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpitFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context mContext;

    @SuppressLint("ValidFragment")
    public SpitFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public SpitFragment(Context context) {
        super();
        mContext = context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//         TextView view = new TextView(UIUtils.getContext());
//         view.setText("哈哈");

        View view = View.inflate(mContext, R.layout.home_pager, null);
        return view;
    }


}
