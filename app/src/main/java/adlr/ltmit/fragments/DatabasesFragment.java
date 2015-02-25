package adlr.ltmit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import adlr.ltmit.R;



public class DatabasesFragment extends Fragment {
    private ListView listView;

    public DatabasesFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_databases, container, false);
        listView = (ListView) getActivity().findViewById(R.id.listView1);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.row_layout, R.id.label, values);
        listView.setAdapter(adapter);
        return view;
    }


}
