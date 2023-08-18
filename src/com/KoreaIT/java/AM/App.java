package com.KoreaIT.java.AM;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.util.Util;
import com.KoreaIT.java.AM.dto.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
  private static List<Article> articles;
  private static List<Member> members;

  App() {
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


      
      if (cmd.equals("member join")) {
        int id = members.size() + 1;

        String regDate = Util.getNowDateStr();
        String loginId = null;

        while (true) {
          System.out.printf("로그인 아이디 : ");
          loginId = sc.nextLine();

          if (isJoinableLoginId(loginId) == false) {
            System.out.printf("%s은(는) 이미 사용중인 아이디입니다.\n",loginId);
            continue;
          }
          break;
        }

        String loginPw = null;
        String loginPwcheck = null;

        while (true) {
          System.out.printf("로그인 비밀번호 : ");
          loginPw = sc.nextLine();
          System.out.printf("로그인 비밀번호 확인 : ");
          loginPwcheck = sc.nextLine();

          if (loginPw.equals(loginPwcheck) == false) {
            System.out.println("비밀번호를 다시 입력하세요");
            continue;
          }
          break;
        }

        System.out.printf("이름 : ");
        String name = sc.nextLine();

        Member member = new Member(id, regDate, loginId, loginPw, name);
        members.add(member);

        System.out.printf("%d번 회원이 가입했습니다.\n", id);
      }


        else if (cmd.equals("article write")) {
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


        else if (cmd.startsWith("article list")) {
          if (articles.size() == 0) {
            System.out.println("게시글이 없습니다.");
            continue;
          } else {
            String searchKeyword = cmd.substring("article list".length()).trim();
//          System.out.printf("검색어 : %s",searchKeyword);

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
                continue;
              }
            }
            System.out.println("번호    |    제목    |    조회수");
            for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
              Article article = forPrintArticles.get(i);
              System.out.printf("%4d    |  %2s    |    %3d\n", article.id, article.title, article.viewCnt);
            }
          }



        } else if (cmd.startsWith("article detail")) {
          String[] cmdBits = cmd.split(" ");
          int id = Integer.parseInt(cmdBits[2]);

          Article foundArticle = getArticleById(id);

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



        } else if (cmd.startsWith("article modify")) {
          String[] cmdBits = cmd.split(" ");
          int id = Integer.parseInt(cmdBits[2]);

          Article foundArticle = getArticleById(id);

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
          int foundIdx = getArticleIndexById(id);

          if (foundIdx == -1) {
            System.out.printf("%d번 글이 없습니다.\n", id);
            continue;
          }
          articles.remove(foundIdx);
          System.out.printf("%d번 글이 삭제되었습니다.\n", id);
        } else {
          System.out.println("존재하지 않는 명령어입니다.");
        }
      }

      sc.close();
      System.out.println("== 프로그램 종료 ==");

    }

  private Article getArticleById(int id) {
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

  private boolean isJoinableLoginId(String loginId) {
    int index = getMemberIndexByloginId(loginId);

    if (index == -1) {
      return true;
    }
    return false;
    }

    private int getMemberIndexByloginId(String loginId) {
      int i = 0;

      for (Member member : members) {
        if (member.loginId.equals(loginId)) {
          return i;
        }
        i++;
      }
      return -1;
    }


    private int getArticleIndexById(int id) {
      int i = 0;

      for (Article article : articles) {
        if (article.id == id) {
          return i;
        }
        i++;
      }
      return -1;
    }
      private void makeTestData () {
        System.out.println("테스트데이터 생성");
        articles.add(new Article(1, Util.getNowDateStr(), "title1", "content1", 11));
        articles.add(new Article(2, Util.getNowDateStr(), "title2", "content2", 22));
        articles.add(new Article(3, Util.getNowDateStr(), "title3", "content3", 33));
      }
}
