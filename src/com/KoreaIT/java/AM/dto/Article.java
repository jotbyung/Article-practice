package com.KoreaIT.java.AM.dto;
public class Article {
  public int id;
  public String regDate;
  public String title;
  public String content;
  public int viewCnt;

    public Article(int id, String regDate, String title, String content) {
      this(id, regDate, title, content, 0);
    }

    public Article(int id, String regDate, String title, String content, int viewCnt) {
      this.id = id;
      this.regDate = regDate;
      this.title = title;
      this.content = content;
      this.viewCnt = viewCnt;
    }
    public void increaseViewCnt() {
      viewCnt++;
    }
  }

