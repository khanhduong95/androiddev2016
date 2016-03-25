package feedr.usth.tanu.feedrtest1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    News news;
    ListView listView;
    ArrayList<News> listNews;
    NewsAdapter newsAdapter;
    Boolean isLoading = true;
    Boolean initialLoad = true;

    private String url1 = "http://vnexpress.net/rss/thoi-su.rss";
    String url2 = "http://rss.cnn.com/rss/edition_world.rss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (newsAdapter == null) { return; }
                if (newsAdapter.getCount() == 0) {return; }

                int l = visibleItemCount + firstVisibleItem;
                if (l >= totalItemCount && !isLoading) {
                    isLoading = true;
                    new MyXMLFetcher().execute(url1);
                }
            }
        });
        new MyXMLFetcher().execute(url1);
    }

    public void updateList() {
        if (initialLoad) {
            newsAdapter = new NewsAdapter(MainActivity.this, R.layout.content_main, listNews);
            listView.setAdapter(newsAdapter);
            initialLoad = false;
        } else {
            newsAdapter.addAll(listNews);
            newsAdapter.notifyDataSetChanged();
        }
        isLoading = false;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MyXMLFetcher extends AsyncTask<String, Integer, ArrayList<News>> {
        @Override
        protected ArrayList<News> doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();

                InputStream stream = conn.getInputStream();
                XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
                XmlPullParser myParser = xmlFactoryObject.newPullParser();

                myParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                myParser.setInput(stream, null);

                int event;

                listNews = new ArrayList<>();
                event = myParser.getEventType();

                while (event != XmlPullParser.END_DOCUMENT) {

                    String name = myParser.getName();

                    switch (event) {
                        case XmlPullParser.START_TAG:
                            if (name.equalsIgnoreCase("item") && news == null) {
                                news = new News();
                            } else if (name.equalsIgnoreCase("title") && news != null) {
                                if (myParser.next() == XmlPullParser.TEXT) {
                                    news.setTitle(myParser.getText());
                                    Log.i("title", news.getTitle().replace("\n", ""));
                                }
                            } else if (name.equalsIgnoreCase("description") && news != null) {
                                if (myParser.next() == XmlPullParser.TEXT) {
                                    news.setDesc(myParser.getText());
                                    Log.i("description", news.getDesc());
                                }
                            } else if (name.equalsIgnoreCase("pubdate") && news != null) {
                                if (myParser.next() == XmlPullParser.TEXT) {
                                    news.setPubdate(myParser.getText());
                                }
                            } else if (name.equalsIgnoreCase("link") && news != null) {
                                if (myParser.next() == XmlPullParser.TEXT) {
                                    news.setLink(myParser.getText());
                                    listNews.add(news);
                                    news = null;
                                }
                            }
                        case XmlPullParser.END_TAG:

                            break;
                    }
                    event = myParser.next();
                }
            } catch (XmlPullParserException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<News> news) {
            //int number = news.size() ;
            //Toast.makeText(MainActivity.this, "Fetch xong" + number, Toast.LENGTH_SHORT).show();

            if (listNews != null && !listNews.isEmpty()) {
                updateList();
                int number = listNews.size() ;
                //Toast.makeText(MainActivity.this, "Fetch xong" + number, Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(MainActivity.this, "Nothing to show", Toast.LENGTH_SHORT).show();
                if (initialLoad == true) {
                    Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
