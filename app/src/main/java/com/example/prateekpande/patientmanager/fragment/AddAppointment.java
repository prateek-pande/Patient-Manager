package com.example.prateekpande.patientmanager.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.prateekpande.patientmanager.R;
import com.example.prateekpande.patientmanager.model.Appointment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddAppointment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddAppointment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddAppointment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button bookAppointmentBtn;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddAppointment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddAppointment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddAppointment newInstance(String param1, String param2) {
        AddAppointment fragment = new AddAppointment();
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
        return inflater.inflate(R.layout.fragment_add_apointment, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //initialize form data
//        initializeFormData();
        bookAppointment();
        initializePicker();
    }

    /**
     * This method is responsible for
     * saving appointment data.
     */
    public void bookAppointment(){

        (getActivity().findViewById(R.id.btnBookAppointment)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Appointment appointment = getPatientAppointment();
                Log.d("appointment",appointment.getPatientName());
                Log.d("appointment",appointment.getPatientContact().toString());
                Log.d("appointment",appointment.getPatientGender());
                Log.d("appointment",appointment.getPatientAge()+"");
                Log.d("appointment",appointment.getClinicLocation());
                Log.d("appointment",appointment.getRegarding());
                Log.d("appointment",appointment.getAppointmentDateTime().toString());
            }
        });
    }

    /**
     * This method sets date and time picker
     */
    public void initializePicker(){

        //date picker
        final EditText dateEditText = (EditText)getActivity().findViewById(R.id.editTextDate);

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateEditText.setText(dayOfMonth+"-"+(monthOfYear+1)+"-"+year);
                    }
                },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }

        });

        //time picker
        final EditText timeEditText = (EditText)getActivity().findViewById(R.id.editTextTime);

        timeEditText.setOnClickListener(new View.OnClickListener() {

            Calendar c = Calendar.getInstance();
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeEditText.setText(hourOfDay+":"+minute);
                    }
                },c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),false);

                timePickerDialog.show();
            }
        });
    }

    /**
     * This method is responsible for
     * retrieving data from form and
     * returning an object
     * @return
     */
    public Appointment getPatientAppointment(){

        Appointment appointment = new Appointment();
        appointment.setPatientName(((EditText)(getView().findViewById(R.id.editTextPatientName))).getText().toString());
        appointment.setPatientAge(Integer.parseInt(((EditText)(getView().findViewById(R.id.editTextPatientAge))).getText().toString()));
        appointment.setPatientContact(Long.parseLong(((EditText)(getView().findViewById(R.id.editTextPatientContact))).getText().toString()));

        int genderRadioId = ((RadioGroup)getView().findViewById(R.id.radioGroupGender)).getCheckedRadioButtonId();
        appointment.setPatientGender(((RadioButton)(getView().findViewById(genderRadioId))).getText().toString());

        appointment.setClinicLocation(((Spinner)(getView().findViewById(R.id.spinnerClinic))).getSelectedItem().toString());
        appointment.setRegarding(((EditText)(getView().findViewById(R.id.editTextRegarding))).getText().toString());
        String[] dateEditText = (((EditText)getView().findViewById(R.id.editTextDate)).getText().toString()).split("-");
        String[] timeEditText = (((EditText)getView().findViewById(R.id.editTextTime)).getText().toString()).split(":");
        Date date = new Date();
        date.setDate(Integer.parseInt(dateEditText[0]));
        date.setMonth(Integer.parseInt(dateEditText[1])-1);
        date.setYear(Integer.parseInt(dateEditText[2]));
        date.setHours(Integer.parseInt(timeEditText[0]));
        date.setMinutes(Integer.parseInt(timeEditText[1]));
        appointment.setAppointmentDateTime(date);
        return appointment;
    }

    /**
     * This method initializes spinners
     */
    public void initializeFormData(){

        //TODO:code to initialize clinic locations from db
        //clinic location
        String[] clinics ={"A","B"};
        ((Spinner) getView().findViewById(R.id.spinnerClinic)).setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,clinics));
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onAddApointmentFragmentInteraction(Appointment appointment);
    }
}
