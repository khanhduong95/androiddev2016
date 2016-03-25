package feedr.usth.tanu.feedrtest1;

import org.xmlpull.v1.XmlPullParserFactory;

/**
 * Created by tanu on 3/25/16.
 */
public class News {

    String Title = "";
    String Desc = "";
    String Pubdate = "";

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    String Link = "";
    String Imgurl;

    public News (String title, String desc, String pubdate){
        Title = title;
        Desc = desc;
        Pubdate = pubdate;
    }
    public News() {

    }

    public String getTitle(){
        return Title;
    }

    public void setTitle(String title){
        this.Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        this.Desc = desc;
    }

    public String getPubdate() {
        return Pubdate;
    }

    public void setPubdate(String pubdate) {
        this.Pubdate = pubdate;
    }

    public String getImgurl() {
        return Imgurl;
    }

    public void setImgurl(String imgurl) {
        this.Imgurl = imgurl;
    }
}
