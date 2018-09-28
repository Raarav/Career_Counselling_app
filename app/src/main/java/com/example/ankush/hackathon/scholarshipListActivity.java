package com.example.ankush.hackathon;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class scholarshipListActivity extends Fragment {

    private alphabetAdapter mAdapter;
    private ProgressDialog mProgress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_list_view, container, false);

        ListView dataListView = (ListView) view.findViewById(R.id.list);
        final ArrayList<data_with_link> temp = new ArrayList<>();
        // Create a new adapter that takes an empty list os input
        mAdapter = new alphabetAdapter(getContext(), new ArrayList<data_with_link>(), "ScholarshipListActivity");
        mProgress=new ProgressDialog(getContext());

        // so the list can be populated in the user interface
        ListAsyncTask task = new ListAsyncTask();

        task.execute("https://career.webindia123.com/career/options/asp/list_more.asp?cat_id=142&o_id=4&option=Scholarships");

        //   ArrayList<data_with_link> a= AlphabetOrderDataExtraction.fetchData(("https://career.webindia123.com/career/options/asp/alpha_listing.asp"));
        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected e.
        mAdapter = new alphabetAdapter(getContext(), temp, "ScholarshipListActivity");


        dataListView.setAdapter(mAdapter);

        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current  that was clicked on
                data_with_link currentdata = mAdapter.getItem(position);

                // Create a new intent to view the e URI
                //  Intent websiteIntent = new Intent(Intent.ACTION_VIEW, careerListUri);

                // Send the intent to launch a new activity
                // startActivity(websiteIntent);

                Intent intent = new Intent(getContext(), selectedCareerDetails.class);
                intent.putExtra("url", currentdata.getUrl());
                startActivity(intent);

            }
        });
        return view;
    }





        //assync task

    @SuppressLint("StaticFieldLeak")
    private  class ListAsyncTask extends AsyncTask<String, Void, ArrayList<data_with_link>> {

        @Override
        protected ArrayList<data_with_link> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            return AlphabetOrderDataExtraction.fetchData(urls[0],"ScholarshipListActivity");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgress.setMessage("Loading...");
            mProgress.show();
        }

        @Override
        protected void onPostExecute(ArrayList<data_with_link> data) {

            mProgress.dismiss();

            // Clear the adapter of previous e data
            mAdapter.clear();

            // If there is a valid list of , then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }



}
