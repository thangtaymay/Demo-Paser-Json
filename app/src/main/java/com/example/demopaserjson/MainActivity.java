package com.example.demopaserjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.VideoView;

import com.example.demopaserjson.adapterdemo.ItemAdapter;
import com.example.demopaserjson.objectdemo.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {


    GridView gridViewDanhSach;

    ArrayList<Item> itemArrayList;

    ItemAdapter adapter;

    private EditText editTextNhapTuKhoa;

    private Button buttonSearch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gridViewDanhSach = (GridView) findViewById(R.id.gridview_danhsach);

        editTextNhapTuKhoa = (EditText) findViewById(R.id.edittext_tukhoa);

        buttonSearch = (Button) findViewById(R.id.btn_timkiem);

        itemArrayList = new ArrayList<>();

        adapter = new ItemAdapter(this, itemArrayList);


        gridViewDanhSach.setAdapter(adapter);





        buttonSearch.setOnClickListener(v -> {
            String tukhoa = editTextNhapTuKhoa.getText().toString();
            Log.e("eee", tukhoa.toString());
            Log.e("eee", "vao onClick");
            new ReadISON().execute(tukhoa);
        });


        gridViewDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent(MainActivity.this, PlayerVideoActivity2.class );
                startActivity(in);
            }
        });






    }







    private class ReadISON extends AsyncTask<String, Void, ArrayList<Item>> {


        @Override
        protected ArrayList<Item> doInBackground(String... tukhoa) {
            ArrayList<Item> itemDauRa = new ArrayList<>();

            try {
                Log.e("eee", "vao doInBackground");


                int page = 1;

                int totalPage = 0;


                String link = "https://kaiber.ai/api/get_gallery_videos?page=" + page + "&q=" + tukhoa[0];

//                "https://kaiber.ai/api/get_gallery_videos?page=1&q=cat";


                String jsonItems = GetJsonFromLink(link);// page 1


                totalPage = new JSONObject(jsonItems).getInt("totalPages");


                Log.e("totalPage", totalPage + "");


                for (int i = 0; i < totalPage; i++) {
                    Log.e("page", (i + 1) + "");

                    link = "https://kaiber.ai/api/get_gallery_videos?page=" + (i + 1) + "&q=" + tukhoa[0];

                    String jsonPages = GetJsonFromLink(link);

                    ArrayList<Item> itemParser = ParseItems(jsonPages);


                    Log.e("itemPare", itemParser.size() + "");

                    itemDauRa.addAll(itemParser);
                    Log.e("totaitems", itemDauRa.size() + "");
                }


            } catch (Exception e) {
            }


            return itemDauRa;
        }


        @Override
        protected void onPostExecute(ArrayList<Item> items) {

            Log.e("onPostExecute", items.size() + "");
            itemArrayList.clear();
            itemArrayList.addAll(items);
            adapter.notifyDataSetChanged();


        }
    }

    private ArrayList<Item> ParseItems(String jsonPages) {
        ArrayList<Item> pageItems = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonPages);
            JSONArray items = (JSONArray) jsonObject.get("items");
            Log.e("onPostExecute", "items " + items.length());

            for (int i = 0; i < items.length(); i++) {
                JSONObject object_item = (JSONObject) items.get(i);


                JSONObject settingsUsed = (JSONObject) object_item.get("settingsUsed");
                String thum = "";

                try {
                    JSONObject scenes = (JSONObject) settingsUsed.get("scenes");
                    JSONObject object_0 = (JSONObject) scenes.get("0");
                    JSONObject previewFrames = (JSONObject) object_0.get("previewFrames");
                    thum = previewFrames.getString(previewFrames.names().get(0).toString());
                    Log.e("aaa", thum);
                } catch (Exception e) {
                    Log.e("aaa", "thum rong");
                }

                Item item = new Item(object_item.getString("_id"), object_item.getString("url"), object_item.getString("prompt"), thum);
                pageItems.add(item);

            }
            Log.e("eee", pageItems.size() + "");
        } catch (Exception e) {

            Log.e("loi catch onPost --->", e.getMessage());

        }
        return pageItems;
    }

    private String GetJsonFromLink(String link) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(link);
            InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }
        return content.toString();
    }


}