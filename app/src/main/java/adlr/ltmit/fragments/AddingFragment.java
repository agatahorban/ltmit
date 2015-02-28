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
import android.widget.RadioButton;
import android.widget.TextView;

import adlr.ltmit.R;
import adlr.ltmit.activities.CreateNewCategoryActivity;

/**
 * Created by Agata on 2015-02-26.
 */
public class AddingFragment extends Fragment {

    private EditText categoryNameET2;
//    private TextView categoryNameTv2;
    private Button buttonCategoryOk2;

    private TextView priorityTv2;
    private RadioButton radioButton1, radioButton2, radioButton3;
    private EditText categoryDbNameET2;

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
//            categoryNameTv2 = (TextView) getActivity().findViewById(R.id.categoryNameTv2);
            buttonCategoryOk2 = (Button) getActivity().findViewById(R.id.buttonCategoryOk2);

            priorityTv2 = (TextView) getActivity().findViewById(R.id.priorityTv2);
//            categoryDbNameTv2 = (TextView) getActivity().findViewById(R.id.categoryDbNameTv2);
            categoryDbNameET2 = (EditText) getActivity().findViewById(R.id.categoryDbNameET2);
            radioButton1 = (RadioButton) getActivity().findViewById(R.id.radioButton);
            radioButton2 = (RadioButton) getActivity().findViewById(R.id.radioButton2);
            radioButton3 = (RadioButton) getActivity().findViewById(R.id.radioButton3);

           categoryNameET2.setVisibility(View.GONE);
//            categoryNameTv2.setVisibility(View.GONE);
            buttonCategoryOk2.setVisibility(View.GONE);

            priorityTv2.setVisibility(View.GONE);
//            categoryDbNameTv2.setVisibility(View.GONE);
            categoryDbNameET2.setVisibility(View.GONE);
            radioButton1.setVisibility(View.GONE);
            radioButton2.setVisibility(View.GONE);
            radioButton3.setVisibility(View.GONE);

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
