package adlr.ltmit.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import adlr.ltmit.R;
import adlr.ltmit.controllers.ProfileController;

public class ProfileFragment extends Fragment {

    private static final String prefs = "MyPreferences";
    private SharedPreferences sp;

    private ProfileController pc;

    private TextView helloTextView, profileName2, profileEmail2;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        sp = getActivity().getSharedPreferences(prefs, Context.MODE_PRIVATE);

        pc = new ProfileController();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        helloTextView = (TextView) getActivity().findViewById(R.id.helloTextView);
        helloTextView.setText("Hello " + pc.getName(sp));
        profileName2 = (TextView) getActivity().findViewById(R.id.profileName2);
        profileName2.setText(pc.getName(sp));
        profileEmail2 = (TextView) getActivity().findViewById(R.id.profileEmail2);
        profileEmail2.setText(pc.getEmail(sp));
    }
}
