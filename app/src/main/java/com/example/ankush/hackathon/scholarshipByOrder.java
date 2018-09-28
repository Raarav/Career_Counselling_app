package com.example.ankush.hackathon;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class scholarshipByOrder extends Fragment  {

    private Button button1,button2,button3,button4;
    private LinearLayout linearLayout;
    private String container=null;
    private String url="https://scholarships.gov.in/";
    private WebView webView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.scholarship_by_order,container,false);

        webView=view.findViewById(R.id.webview);

/*
        button1=(Button) view.findViewById(R.id.central_scheme);
        button1.setOnClickListener(this);
        button2=(Button) view.findViewById(R.id.ugc_scheme);
        button2.setOnClickListener(this);
        button3=(Button) view.findViewById(R.id.aicte_scheme);
        button3.setOnClickListener(this);
        button4=(Button) view.findViewById(R.id.state_scheme);
        button4.setOnClickListener(this);
*/

    //    webView.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = getActivity();

        webView.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });



        webView.loadUrl("https://scholarships.gov.in/");
        return view;
    }


    /**
/*
    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.central_scheme:

                container="div#CentralSchemes.w3-container.w3-border.scheme tr";
                listAsyncTask.cancel(true);
                listAsyncTask.execute(container);
                // code for button when user clicks buttonOne.
                break;

            case R.id.ugc_scheme:

                container="div#CentralSchemes.w3-container.w3-border.scheme tr";
                listAsyncTask.cancel(true);

                listAsyncTask.execute(container);
                // do your code
                break;

            case R.id.aicte_scheme:
                listAsyncTask.cancel(true);

                container="div#CentralSchemes.w3-container.w3-border.scheme tr";
                listAsyncTask.execute(container);
                // do your code
                break;

            case R.id.state_scheme:
                listAsyncTask.cancel(true);

                container="div#CentralSchemes.w3-container.w3-border.scheme tr";
                listAsyncTask.execute(container);
                // do your code
                break;
            default:
                break;
        }

    }


    @SuppressLint("StaticFieldLeak")
    private  class ListAsyncTask extends AsyncTask<String, Void, ArrayList<data_with_link>> {




        @Override
        protected ArrayList<data_with_link> doInBackground(String... container) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (container.length < 1 || container[0] == null) {
                return null;
            }

            ArrayList<data_with_link> temp = new ArrayList<>();
            //String initialUrl = "https://career.webindia123.com";

            try {


                Document doc = Jsoup.connect(url).get();
                // String title = doc.title();

                Log.i("no error","jsoup extracting");
                // selecting all td rows of the container
                Elements subcontainer = doc.select(container[0]);
                Elements listCareer = doc.select("div.span_30 td");
                Elements linkss = listCareer.select("a[href]");
                Elements header=subcontainer.select("header.type-2");
                int i=0;
                for (Element link : subcontainer) {
                    //copiyng 4 url options available

                   // temp.add(new data_with_link(link.text().charAt(0),link.text(), initialUrl + link.attr("href")));
                    Log.i("lol; ",link.attr("href"));

                }

            } catch (IOException e) {
                Log.i("a","aa");

            }



            return temp;


        }
        /*
                 void publishProgress(Bitmap bitmap) {

                    imageView.setImageBitmap(bitmap);
                }

        @Override
        protected void onPostExecute(ArrayList<data_with_link> data) {
            // Clear the adapter of previous data







        }



    }
**/

}
