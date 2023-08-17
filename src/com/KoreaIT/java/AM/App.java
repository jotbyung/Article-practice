package com.KoreaIT.java.AM;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
  private static List<Article> articles;
  App()  {
    articles = new ArrayList<>();
  }
  public void start() {
    System.out.println("== 프로그램 시작 ==");
    makeTestData();
    Scanner sc = new Scanner(System.in);



    while (true) {
      System.out.printf("명령어 )");
      String cmd = sc.nextLine().trim();


      if (cmd.length() == 0) {
        System.out.println("명령어를 입력하세요");
        continue;
      }

      if (cmd.equals("system exit")) {
        break;
      }
      if (cmd.equals("article write")) {
        int id = articles.size() + 1;

        String regDate = Util.getNowDateStr();
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String content = sc.nextLine();


        Article article = new com.KoreaIT.java.AM.dto.Article(id, regDate, title, content);
        articles.add(article);


        System.out.printf("%d번 글이 생성되었습니다.\n", id);
      }

      else if (cmd.equals("article list")) {
        if (articles.size() == 0) {
          System.out.println("게시글이 없습니다.");
        } else {
          System.out.println("번호    |    제목    |    조회수");
          for (int i = articles.size() - 1; i >= 0; i--) {
            Article article = articles.get(i);
            System.out.printf("%4d    |  %2s    |    %3d\n", article.id, article.title,article.viewCnt);
          }
        }
      } else if (cmd.startsWith("article detail")) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        com.KoreaIT.java.AM.dto.Article foundArticle = null;

        for (int i = 0; i < articles.size(); i++) {
          Article article = articles.get(i);
          if (article.id == id) {
            foundArticle = article;
            break;
          }
        }
        if (foundArticle == null) {
          System.out.printf("%d번 글이 없습니다.\n", id);
          continue;
        }
        foundArticle.increaseViewCnt();
        System.out.printf("번호 : %d\n", id);
        System.out.printf("작성날짜 : %s\n", foundArticle.regDate);
        System.out.printf("제목 : %s\n", foundArticle.title);
        System.out.printf("내용 : %s\n", foundArticle.content);
        System.out.printf("조회수 : %d", foundArticle.viewCnt);

      }
      else if (cmd.startsWith("article modify")) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        Article foundArticle = null;

        for (int i = 0; i < articles.size(); i++) {
          Article article = articles.get(i);
          if (article.id == id) {
            foundArticle = article;
            break;
          }
        }
        if (foundArticle == null) {
          System.out.printf("%d번 글이 없습니다.\n", id);
          continue;
        }
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String content = sc.nextLine();

        foundArticle.title = title;
        foundArticle.content = content;
        System.out.printf("%d번 글이 수정되었습니다.\n", id);
      }
      else if (cmd.startsWith("article delete")) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);
        int foundIdx = -1;

        for (int i = 0; i < articles.size(); i++) {
          Article article = articles.get(i);
          if (article.id == id) {
            foundIdx = i;
            break;
          }
        }
        if (foundIdx == -1) {
          System.out.printf("%d번 글이 없습니다.\n", id);
          continue;
        }

        articles.remove(foundIdx);
        System.out.printf("%d번 글이 삭제되었습니다.\n", id);
      }


      else {
        System.out.println("존재하지 않는 명령어입니다.");
      }
    }

    sc.close();
    System.out.println("== 프로그램 종료 ==");

  }

  private void makeTestData() {
    System.out.println("테스트데이터 생성");
    articles.add(new Article(1, Util.getNowDateStr(), "title1","content1",11));
    articles.add(new Article(2, Util.getNowDateStr(), "title2","content2",22));
    articles.add(new Article(3, Util.getNowDateStr(), "title3","content3",33));
  }
}