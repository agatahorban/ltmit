package adlr.ltmit.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import adlr.ltmit.R;
import adlr.ltmit.activities.CreateNewCategoryActivity;

/**
 * Created by Agata on 2015-02-26.
 */
public class AddingFragment extends Fragment {

    private EditText categoryNameET2;
    private TextView categoryNameTv2;
    private Button buttonCategoryOk2;
    public AddingFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adding, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getScreenOrientation()== Configuration.ORIENTATION_LANDSCAPE) {
            categoryNameET2 = (EditText) getActivity().findViewById(R.id.categoryNameET2);
            categoryNameTv2 = (TextView) getActivity().findViewById(R.id.categoryNameTv2);
            buttonCategoryOk2 = (Button) getActivity().findViewById(R.id.buttonCategoryOk2);
            categoryNameET2.setVisibility(View.GONE);
            categoryNameTv2.setVisibility(View.GONE);
            buttonCategoryOk2.setVisibility(View.GONE);
        }
    }


    public int getScreenOrientation()
    {
        Display getOrient = getActivity().getWindowManager().getDefaultDisplay();
        int orientation = Configuration.ORIENTATION_UNDEFINED;
        if(getOrient.getWidth()==getOrient.getHeight()){
            orientation = Configuration.ORIENTATION_SQUARE;
        } else{
            if(getOrient.getWidth() < getOrient.getHeight()){
                orientation = Configuration.ORIENTATION_PORTRAIT;
            }else {
                orientation = Configuration.ORIENTATION_LANDSCAPE;
            }
        }
        return orientation;
    }
}
