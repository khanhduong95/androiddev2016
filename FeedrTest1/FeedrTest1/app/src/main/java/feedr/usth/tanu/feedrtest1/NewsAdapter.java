package feedr.usth.tanu.feedrtest1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tanu on 3/25/16.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    private Context context;
    ArrayList<News> listNews;

    //Initializing adapter
    public NewsAdapter(Context context, int viewResourceId, ArrayList<News> news){
        super(context, viewResourceId, news);
        this.context = context;
        this.listNews = news;
    }

    //Return number of elements that is shown in the list view
    @Override
    public int getCount() {
        return listNews.size();
    }

    //Return position of elements that is shown in the list view
    @Override
    public News getItem(int position) {
        return listNews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.content_main, parent, false);
        }

        //Pull out interested elements in its position
        final News item = listNews.get(position);


        TextView txtTitle, txtDesc, txtPubdate;
        ImageButton button;

        //Bind data into list viewg

        if (item != null) {
            txtTitle = (TextView) convertView.findViewById(R.id.head_title);
            txtDesc = (TextView) convertView.findViewById(R.id.head_desc);
            txtPubdate = (TextView) convertView.findViewById(R.id.head_pubdate);
            button = (ImageButton) convertView.findViewById(R.id.button);

            //Bind data into list viewg
            Log.i("username", item.getTitle());
            txtTitle.setText(item.getTitle());
            txtDesc.setText(item.getDesc());
            txtPubdate.setText(item.getPubdate());

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
                    context.startActivity(browserIntent);
                }
            });
        }

        return convertView;
    }
}
