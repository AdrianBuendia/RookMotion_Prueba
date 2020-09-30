package com.example.sdkfacebook.Model.POJOS;

public class Cancion {
    private int id;
    private boolean readable;
    private String title;
    private String title_short;
    private String title_version;
    private String link;
    private int duration;
    private long rank;
    private boolean explicit_lyrics;
    private int explicit_content_lyrics;
    private int explicit_content_cover;

    //preview
    private String preview;

    private String md5_image;

    //artist
    private Artist artist;

    //album
    private Album album;

    private String type;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_short() {
        return title_short;
    }

    public void setTitle_short(String title_short) {
        this.title_short = title_short;
    }

    public String getTitle_version() {
        return title_version;
    }

    public void setTitle_version(String title_version) {
        this.title_version = title_version;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public boolean isExplicit_lyrics() {
        return explicit_lyrics;
    }

    public void setExplicit_lyrics(boolean explicit_lyrics) {
        this.explicit_lyrics = explicit_lyrics;
    }

    public int getExplicit_content_lyrics() {
        return explicit_content_lyrics;
    }

    public void setExplicit_content_lyrics(int explicit_content_lyrics) {
        this.explicit_content_lyrics = explicit_content_lyrics;
    }

    public int getExplicit_content_cover() {
        return explicit_content_cover;
    }

    public void setExplicit_content_cover(int explicit_content_cover) {
        this.explicit_content_cover = explicit_content_cover;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getMd5_image() {
        return md5_image;
    }

    public void setMd5_image(String md5_image) {
        this.md5_image = md5_image;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
