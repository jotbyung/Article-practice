package com.KoreaIT.java.AM.controller;

import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

import java.util.List;
import java.util.Scanner;

public class MemberController {
  private List<Member> members;
  private Scanner sc;
  public MemberController(List<Member> members, Scanner sc) {
    this.members = members;
    this.sc = sc;
  }
  public void doJoin(Scanner sc) {
    int id = members.size() + 1;

    String regDate = Util.getNowDateStr();
    String loginId = null;

    while (true) {
      System.out.printf("로그인 아이디 : ");
      loginId = sc.nextLine();

      if (isJoinableLoginId(loginId) == false) {
        System.out.printf("%s은(는) 이미 사용중인 아이디입니다.\n", loginId);
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
}