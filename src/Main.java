import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


class Main {
  public static void main(String[] args) {
    System.out.println("== 프로그램 시작 ==");
    Scanner sc = new Scanner(System.in);
    int lastArticleId = 0;
    List<Article> articles = new ArrayList<>();


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
        int id = lastArticleId + 1;
        lastArticleId = id;

        String regDate = Util.getNowDateStr();
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String content = sc.nextLine();


        Article article = new Article(id, regDate, title, content);
        articles.add(article);


        System.out.printf("%d번 글이 생성되었습니다.\n", id);
      } else if (cmd.equals("article list")) {
        if (articles.size() == 0) {
          System.out.println("게시글이 없습니다.");
        } else {
          for (int i = articles.size() - 1; i >= 0; i--) {
            Article article = articles.get(i);
            System.out.printf("%d  |  %s\n", article.id, article.title);
          }
        }
      } else if (cmd.startsWith("article detail")) {
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
        } else {
          System.out.printf("번호 : %d\n", id);
          System.out.printf("작성날짜 : %s\n", foundArticle.regDate);
          System.out.printf("제목 : %s\n", foundArticle.title);
          System.out.printf("내용 : %s\n", foundArticle.content);
        }
      } else if (cmd.startsWith("article delete")) {
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
}

class Article {
  int id;
  String regDate;
  String title;
  String content;

  Article(int id, String regDate, String title, String content) {
    this.id = id;
    this.regDate = regDate;
    this.title = title;
    this.content = content;
  }
}