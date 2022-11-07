package com.example.schedule;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    CardView firstShift;
    CardView secondShift;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        HomeActivity home = (HomeActivity) getActivity();

        secondShift = view.findViewById(R.id.secondShift);
        firstShift = view.findViewById(R.id.firstShift);

        //LÄS PASS FRÅN DATABAS OCH SÄTT VÄRDEN FÖR 'KOMMANDE PASS'
        TextView shiftLabel = view.findViewById(R.id.txtComingShift);
        int shiftAmount = home.getComingShifts().size();
        shiftLabel.setText(shiftLabel.getText() + " (" + shiftAmount + ")");

        //Har användaren bara ett skift:
        if(shiftAmount > 0) {
            firstShift.setVisibility(View.VISIBLE);
            Shift first = home.getComingShifts().get(0);
            TextView date = view.findViewById(R.id.txtFirstShiftDate);
            date.setText(first.getDate());
            TextView time = view.findViewById(R.id.txtFirstShiftHours);
            time.setText(first.getStartTime() + "\n" + first.getStopTime());

            //if-sats beroende på 'time' hämtat från databasen
            TextView shift = view.findViewById(R.id.txtFirstShift);
            shift.setText(first.getShift());
            TextView info = view.findViewById(R.id.txtFirstShiftInfo);
            info.setText(first.getWorkType());
            if(shiftAmount > 1){
                secondShift.setVisibility(View.VISIBLE);
                Shift second = home.getComingShifts().get(1);
                date = view.findViewById(R.id.txtSecondShiftDate);
                date.setText(second.getDate());
                time = view.findViewById(R.id.txtSecondShiftHours);
                time.setText(second.getStartTime() + "\n" + second.getStopTime());

                //if-sats beroende på 'time' hämtat från databasen
                shift = view.findViewById(R.id.txtSecondShift);
                shift.setText(second.getShift());
                info = view.findViewById(R.id.txtSecondShiftInfo);
                info.setText(second.getWorkType());
            }
        }

        //LÄS ANTAL LEDIGA PASS FRÅN DATABASEN
        TextView freeShift = view.findViewById(R.id.txtTotalFreeShift);
        int freeShiftAmount = 2;
        freeShift.setText(Integer.toString(freeShiftAmount));

        FrameLayout frameLayout = view.findViewById(R.id.frameFreeShift);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Öppna kalendersidan
                home.binding.bottomNavigationView.setSelectedItemId(R.id.schedule);
            }
        });
        return view;
    }
}