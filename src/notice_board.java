// 파일이름 : notice_board
// 작성자 : 박지인
// 작성일 : 2022년 11월 16일
// 목 적 :	등록, 삭제, 읽기 기능을 넣으시오.

import java.util.Scanner;

public class notice_board {
  
	private static Scanner s = new Scanner(System.in);
	
	private static String[][] bbses = new String[9][];
	private static int seq = 1;
	private static int gindex = 0;
	
	public static void readme() {
		System.out.println("1. 등록  2. 상세보기(읽기) 3. 수정  4. 삭제   5. 목록   x. 종료");
	}

	public static void exit() {
		System.out.println("프로그램을 종료 합니다.");
	}

	public static String getInput() {
		return s.next();
	}
	
	public static void create() {
		if (gindex == bbses.length) {
			System.out.println("게시글이 꽉 찼습니다.\n삭제 후 이용해 주세요.");
			return;
		}

		String no = String.valueOf(seq);
		
		System.out.println("input title...");
		String title = getInput();

		System.out.println("input content...");
		String content = getInput();

		String[] bbs = new String[3];
		bbs[0] = no;
		bbs[1] = title;
		bbs[2] = content;

		bbses[gindex++] = bbs;
		seq++;

		System.out.println("등록이 완료 되었습니다.");
	}
	
	private static String[] getSearch(String no) {
		for (String[] bbs : bbses) {
			if (bbs != null && bbs[0].equals(no)) {
				return bbs;			
			}
		}

		return null;
	}

	private static int getIndex(String no) {
		for (int i=0; i<bbses.length; i++) {
			String[] bbs = bbses[i];
			
			if (bbs != null && bbs[0].equals(no)) {
				return i;
			}
		}
		
		return -1;
	}

	public static void read(String no) {
		if (no == null || no == "") {
			System.out.println("잘못 입력 하셨습니다.");
			return;
		}
	
		String[] bbs = getSearch(no);
		
		if (bbs == null) {
			System.out.println("게시글이 없습니다.");
			return;
		}
		
		System.out.println("no: " + bbs[0]);
		System.out.println("title: " + bbs[1]);
		System.out.println("content: " + bbs[2]);
	}

	public static void update(String no) {
		if (no == null || no == "") {
			System.out.println("잘못 입력 하셨습니다.");
			return;
		}
	
		String[] bbs = getSearch(no);
		
		if (bbs == null) {
			System.out.println("게시글이 없습니다.");
			return;
		}

		System.out.println("input title...");
		String title = getInput();

		System.out.println("input content...");
		String content = getInput();

		bbs[1] = title;
		bbs[2] = content;

		System.out.println("수정이 완료 되었습니다.");
	}
	
	public static void delete(String no) {
		if (no == null || no == "") {
			System.out.println("잘못 입력 하셨습니다.");
			return;
		}

		int index = getIndex(no);

		if (index == -1) {
			System.out.println("게시글이 없습니다.");
			return;
		}

		bbses[index] = null;
		gindex--;

		int length = bbses.length;				

		for (int i=0; i<length; i++) {
			
			if (index == 0) {
				break;
			}

			if (index == length-1) {
				break;
			}					

			if (i > index) {
				if (i < length) {
					bbses[i-1] = bbses[i];
					bbses[i] = null;
				}
			}
		}

		System.out.println("삭제가 완료 되었습니다.");
	}

	public static void getList() {
		for (int i=0; i<bbses.length; i++) {
			if (bbses[i] == null) continue;				

			System.out.println("index: " + i);
			System.out.println("no: " + bbses[i][0]);
			System.out.println("title: " + bbses[i][1]);
			System.out.println("content: " + bbses[i][2]);
		}			
	}

  public static void main(String[] args) {
	while(true) {
		readme();		

		String choice = getInput();

		if (choice.equals("1")) {
			create();
		} else if (choice.equals("2")) {
			System.out.println("번호를 입력해 주십시오.");
			read(getInput());
		} else if (choice.equals("3")) {
			System.out.println("번호를 입력해 주십시오.");
			update(getInput());
		} else if (choice.equals("4")) {
			System.out.println("번호를 입력해 주십시오.");
			delete(getInput());
		} else if (choice.equals("5")) {
			getList();
		} else if (choice.equals("x")) {
			exit();
			break;
		} else {
			System.out.println("잘못 입력 하셨습니다.\n다시 입력해 주십시오.");
		}
     }	
  }
}
