package com.example.robert.midtermexam;

import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DownloadFeedTask extends AsyncTask<String, Void, String> {
    private StoryDataListener listener = null;
    private Exception exception = null;
    private String author, title, summary;
    private Story s;
    private ArrayList<Story> list = null;

    public DownloadFeedTask(StoryDataListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            // parse out the data
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            Log.d("InternetResourcesSample", "url: " + params[0]);
            URL url = new URL(params[0]);
            Document document = docBuilder.parse(url.openStream());
            document.getDocumentElement().normalize();

            // look for <WordDefinition> tags
            NodeList main = document.getElementsByTagName("entry");
            for (int i = 0; i < main.getLength(); i++) {
                author = main[i].childNodes[2].nodeValue;
                title = main[i].childNodes[4].nodeValue;
                summary = main[i].childNodes[5].nodeValue;

                s = new Story()
            }


        } catch (Exception e) {
            e.printStackTrace();
            exception = e;
        }

        return definition;
    }

    private String stripTags(String code) {
        return code; // for now
    }

    @Override
    protected void onPostExecute(String result) {
        if (exception != null) {
            exception.printStackTrace();
            return;
        }

        // Log.d("InternetResourcesSample", "setting definition: " + definition);
       listener.showStories(list);
    }
}

