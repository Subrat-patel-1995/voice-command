package com.example.voice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText ed;
   //TextView tv;
    private static final int REQUEST_CODE = 1234;
    ImageView speak;
    ListView mylist;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> list;
    private static final String url="https://subrata1995.000webhostapp.com/Garage/list.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ed = (EditText) this.findViewById(R.id.editText1);
        //tv = (TextView) this.findViewById(R.id.textView1);
        mylist=findViewById(R.id.mylist);




        list=new ArrayList<String>();
        arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,list);

        // Disable button if no recognition service is present
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() == 0)
        {
            speak.setEnabled(false);
           // speak.setText("Recognizer not present");
        }
        ed.addTextChangedListener(new TextWatcher()
        {
                @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
            // TODO Auto-generated method stub
        }@
                Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            // TODO Auto-generated method stub
        }@
                Override
        public void afterTextChanged(Editable s)
        {
            // TODO Auto-generated method stub
            //speak.setEnabled(false);
        }
        });
        mylist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               // final int which_postn =position;

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete ?");
                builder.setMessage("Do you want to Delete ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        list.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.create().show();
                return true;
            }
        });

                        //Toast.makeText(MainActivity.this, "gggg", Toast.LENGTH_SHORT).show();
        ed.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    String s=ed.getText().toString();
                    if(!s.isEmpty()) {
                        list.add(s);
                        mylist.setAdapter(arrayAdapter);
                        arrayAdapter.notifyDataSetChanged();
                        ed.getText().clear();
                    //Toast.makeText(MainActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                        //ed.setText("");
                    }else{
                        Toast.makeText(MainActivity.this, "Please Enter or Speak Something", Toast.LENGTH_SHORT).show();
                    }


                    handled = true;
                }
                return handled;
            }
        });

    }

    /*private void databaseentry() {


    }*/

    /**
     * Handle the action of the button being clicked
     */
    public void speakButtonClicked(View v)
    {
        startVoiceRecognitionActivity();
    }
    /**
     * Fire an intent to start the voice recognition activity.
     */
    private void startVoiceRecognitionActivity()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Voice searching...");
        startActivityForResult(intent, REQUEST_CODE);
    }
    /**
     * Handle the results from the voice recognition activity.
     */
    @
            Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {


        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK)
        {
            // Populate the wordsList with the String values the recognition engine thought it heard
            final ArrayList< String > matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (!matches.isEmpty())
            {
                String Query = matches.get(0);

                //speak.setEnabled(false);
                if(Query.matches("go")){
                    registration();
                    arrayAdapter.clear();
                    ed.getText().clear();
                    /*databaseentry();
*/
                    /*Intent intent=new Intent(MainActivity.this,Dashboard.class);
                    startActivity(intent);*/



                }else if(Query.matches("call")){
                    Toast.makeText(this, "hhh", Toast.LENGTH_SHORT).show();
                    Uri number = Uri.parse("tel:8328806560");
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

                    startActivity(callIntent );

                }
                //else if(Query.matches("and")){}
                else{
                    int i,j;
                        for (i = 0; i <= Query.length(); i++){
                        if(Query.regionMatches(i, " "+"and"+" ", 0, 3))  {
                                String queryArr[] = Query.split("and");

                            for (j = 0; j < queryArr.length; j++) {
                                list.add(" "+queryArr[j]);
                                mylist.setAdapter(arrayAdapter);
                                arrayAdapter.notifyDataSetChanged();
                                ed.setText(Query.replaceAll("and",","));
                                




                               // ed.setText(Query);

                               // Toast.makeText(this, queryArr[j], Toast.LENGTH_SHORT).show();
                            }
                            return;

                            }

                    }

                    list.add(Query);
                    mylist.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();
                    ed.setText(Query);


                    //Toast.makeText(this, "sss", Toast.LENGTH_SHORT).show();

                    //if(Query.contains("and"))
                        /*int i;
                        for (i = 0; i <= Query.length(); i++) {
                            if (Query.regionMatches(i, "and", 0, 3)) {
                               // String x = Query.replaceAll("and", "\n");
                                String x = Query.replaceAll("and", "\n");

                                list.add(" "+x);
                                mylist.setAdapter(arrayAdapter);
                                arrayAdapter.notifyDataSetChanged();
                                ed.setText(Query);
                                Toast.makeText(this, "found", Toast.LENGTH_SHORT).show();
                                return ;
                            }

                    }
                    list.add(Query);
                    mylist.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();
                    ed.setText(Query);*/

                }
                   /* {
                    int i;
                    for(i=0;i<=Query.length();i++) {
                        if (Query.regionMatches(i, "and", 0, 3)) {
//                            while (i==Query.length())
//                            {
//                                list.add(i.trim());
//                                mylist.setAdapter(arrayAdapter);
//                                arrayAdapter.notifyDataSetChanged();
//                                ed.setText(Query);
//                            }
                            //String x= String.valueOf(i);
                            //String x= String.valueOf(i);


                            char x= Query.charAt(i );
                            String y=Character.toString(x);


                            list.add(y);
                            mylist.setAdapter(arrayAdapter);
                            arrayAdapter.notifyDataSetChanged();
                            ed.setText(Query);
                            Toast.makeText(this,y, Toast.LENGTH_SHORT).show();
                        }
                    }
                    list.add(Query);
                    mylist.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();
                    ed.setText(Query);
                }*/


            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
        //Insert list data into data base
    private void registration() {
        //Toast.makeText(this, "hhhhhh", Toast.LENGTH_SHORT).show();
        String param= "?name="+list;
        class checkaccount extends AsyncTask<String,Void,String>

        {
            @Override
            protected void onPostExecute(String s) {

                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url=new URL(strings[0]);
                    HttpURLConnection conn= (HttpURLConnection)url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    return br.readLine();

                }
                catch (Exception e) {
                    return e.getMessage();
                }
            }
        }
        checkaccount obj = new checkaccount();
        obj.execute(url+param);


    }

    /*@Override
    public void startActivity(Intent intent) {
        Uri number = Uri.parse("tel:8328806560");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

// Verify it resolves
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(callIntent, 0);
        boolean isIntentSafe = activities.size() > 0;
    }*/


}
