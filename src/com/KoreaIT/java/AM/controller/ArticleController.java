package com.KoreaIT.java.AM.controller;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleController {
  private List<Article> articles;
  private Scanner sc;
  public  ArticleController(List<Article> articles, Scanner sc) {
    this.articles = articles;
    this.sc = sc;
  }
  public void doWrite(Scanner sc) {
    int id = articles.size() + 1;

    String regDate = Util.getNowDateStr();
    System.out.printf("제목 : ");
    String title = sc.nextLine();
    System.out.printf("내용 : ");
    String content = sc.nextLine();

    Article article = new Article(id, regDate, title, content);
    articles.add(article);

    System.out.printf("%d번 글이 생성되었습니다.\n", id);
  }

  public void doList(String cmd) {
    if (articles.size() == 0) {
      System.out.println("게시글이 없습니다.");
    }
    else {
      String searchKeyword = cmd.substring("article list".length()).trim();

      List<Article> forPrintArticles = articles;
      if (searchKeyword.length() > 0) {
        forPrintArticles = new ArrayList<>();

        for (Article article : articles) {
          if (article.title.contains(searchKeyword)) {
            forPrintArticles.add(article);
          }
        }
        if (forPrintArticles.size() == 0) {
          System.out.println("검색결과가 없습니다.");
        }
      }
      System.out.println("번호    |    제목    |    조회수");
      for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
        Article article = forPrintArticles.get(i);
        System.out.printf("%4d    |  %2s    |    %3d\n", article.id, article.title, article.viewCnt);
      }
    }
  }

  public void doDetail(String cmd) {
    String[] cmdBits = cmd.split(" ");
    int id = Integer.parseInt(cmdBits[2]);

    Article foundArticle = getArticleById(id);

    if (foundArticle == null) {
      System.out.printf("%d번 글이 없습니다.\n", id);
    }
    foundArticle.increaseViewCnt();
    System.out.printf("번호 : %d\n", id);
    System.out.printf("작성날짜 : %s\n", foundArticle.regDate);
    System.out.printf("제목 : %s\n", foundArticle.title);
    System.out.printf("내용 : %s\n", foundArticle.content);
    System.out.printf("조회수 : %d", foundArticle.viewCnt);
  }

  public void doModify(String cmd) {
    String[] cmdBits = cmd.split(" ");
    int id = Integer.parseInt(cmdBits[2]);

    Article foundArticle = getArticleById(id);

    if (foundArticle == null) {
      System.out.printf("%d번 글이 없습니다.\n", id);
    }
    System.out.printf("제목 : ");
    String title = sc.nextLine();
    System.out.printf("내용 : ");
    String content = sc.nextLine();

    foundArticle.title = title;
    foundArticle.content = content;
    System.out.printf("%d번 글이 수정되었습니다.\n", id);
  }

  public void doDelete(String cmd) {
    String[] cmdBits = cmd.split(" ");
    int id = Integer.parseInt(cmdBits[2]);
    int foundIdx = getArticleIndexById(id);

    if (foundIdx == -1) {
      System.out.printf("%d번 글이 없습니다.\n", id);
    }
    articles.remove(foundIdx);
    System.out.printf("%d번 글이 삭제되었습니다.\n", id);
  }
  private Article getArticleById ( int id){
//    for (int i =0; i < articles.size(); i++) {
//      Article article = articles.get(i);
//
//      if (article.id == id) {
//        return article;
//      }
//    }
//    return null;

//    for (Article article : articles) {
//      if (article.id ==id) {
//        return article;
//      }
//    }
    int index = getArticleIndexById(id);
    if (index != -1) {
      return articles.get(index);
    }
    return null;
  }

  private int getArticleIndexById(int id){
    int i = 0;

    for (Article article : articles) {
      if (article.id == id) {
        return i;
      }
      i++;
    }
    return -1;
  }
}


