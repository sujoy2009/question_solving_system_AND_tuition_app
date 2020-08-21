package com.education.problemsolve;

import com.google.firebase.database.Exclude;

public class upload {
    private  String imagename;
    private  String imageurl;
    private  String key;
    private  String ans;
    private  String anspicurl;
    private  String numberis;
    private  String cat;
    private  String solver;



    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }
    public upload()
    {

    }

    public upload(String imagename, String imageurl, String numberis, String ans, String anspicurl, String cat,String solver) {
        this.imagename = imagename;
        this.imageurl = imageurl;
        this.ans = ans;
        this.anspicurl = anspicurl;
        this.numberis = numberis;
        this.cat = cat;
        this.solver=solver;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getAnspicurl() {
        return anspicurl;
    }

    public void setAnspicurl(String anspicurl) {
        this.anspicurl = anspicurl;
    }

    public String getNumberis() {
        return numberis;
    }

    public void setNumberis(String numberis) {
        this.numberis = numberis;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getSolver() {
        return solver;
    }

    public void setSolver(String solver) {
        this.solver = solver;
    }
}

