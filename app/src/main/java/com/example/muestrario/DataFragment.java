package com.example.muestrario;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View view;
    TextView txt_name;
    TextView txt_nrc;
    TextView txt_ms;
    TextView txt_pc;
    TextView txt_em;
    TextView txt_ca;
    TextView txt_p;
    TextView txt_property;
    TextView txt_factors;
    DatabaseReference db;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public DataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataFragment newInstance(String param1, String param2) {
        DataFragment fragment = new DataFragment();
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

        view = inflater.inflate(R.layout.fragment_data, container, false);
        db = FirebaseDatabase.getInstance().getReference("Muestras");

        txt_name = view.findViewById(R.id.Item_name);
        txt_nrc = view.findViewById(R.id.nrc);
        txt_ms = view.findViewById(R.id.ms);
        txt_pc = view.findViewById(R.id.pc);
        txt_em = view.findViewById(R.id.em);
        txt_ca = view.findViewById(R.id.ca);
        txt_p = view.findViewById(R.id.p);
        txt_property = view.findViewById(R.id.property);
        txt_factors = view.findViewById(R.id.factors);



        Bundle bundle = this.getArguments();
        String id = "None";
        if (bundle != null) {
            id = bundle.getString("id", "None");
        }


        Query query = db.child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Muestra muestra = snapshot.getValue(Muestra.class);
                    System.out.println(muestra.name);
                    System.out.println(muestra.nrc);
                    System.out.println(muestra.ms);
                    System.out.println(muestra.pc);
                    System.out.println(muestra.em);
                    System.out.println(muestra.ca);
                    System.out.println(muestra.p);
                    System.out.println(muestra.property);
                    System.out.println(muestra.factors);


                    txt_name.setText(muestra.name);
                    txt_nrc.setText(String.valueOf(muestra.nrc));
                    String ms = String.valueOf(muestra.ms) + "%";
                    txt_ms.setText(ms);
                    String pc = String.valueOf(muestra.pc) + "%";
                    txt_pc.setText(pc);
                    String em = String.valueOf(muestra.em) + "MCal";
                    txt_em.setText(em);
                    String ca = String.valueOf(muestra.ca) + "%";
                    txt_ca.setText(ca);
                    String p = String.valueOf(muestra.p) + "%";
                    txt_p.setText(p);
                    txt_property.setText(muestra.property);
                    txt_factors.setText(muestra.factors);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}