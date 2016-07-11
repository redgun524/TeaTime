package org.sopt.teatime.b_model.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by JH on 2016-07-06.
// */

public class Comment implements Parcelable {
    public Comment() {

    }

    public int id;
    public String content;
    public String nick;
    public Date timestamp;
    public String url;

    protected Comment(Parcel in) {
        id = in.readInt();
        content = in.readString();
        nick = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(content);
        dest.writeString(nick);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getNick() {
        return nick;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getUrl() {
        return url;
    }
}



//public class Comment implements Parcelable {
//
//    public String id;
//    public String content;
//    public String nick;
//    public Date timestamp;
//    public String url;
//}
//    public String getId() {
//        return id;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public String getNick() {
//        return nick;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    protected Comment(Parcel in) {
//        id = in.readString();
//        content = in.readString();
//        nick = in.readString();
//        url = in.readString();
//    }
//
//    public static final Parcelable.Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() {
//        @Override
//        public Comment createFromParcel(Parcel in) {
//            return new Comment(in);
//        }
//
//        @Override
//        public Comment[] newArray(int size) {
//            return new Comment[size];
//        }
//    };
//
//    public Comment(String id, String content, String nick, String url) {
//        this.id = id;
//        this.content = content;
//        this.nick = nick;
//        this.url = url;
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//
//        parcel.writeString(id);
//        parcel.writeString(content);
//        parcel.writeString(nick);
//        parcel.writeString(url);
//
//    }
//}
//public class Comment implements Serializable{
//
//    public String id;
//    public String content;
//    public String nick;
//    public Date timestamp;
//    public String url;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getNick() {
//        return nick;
//    }
//
//    public void setNick(String nick) {
//        this.nick = nick;
//    }
//
//    public Date getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(Date timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//}
