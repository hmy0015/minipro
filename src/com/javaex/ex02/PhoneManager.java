package com.javaex.ex02;
import java.io.*;
import java.util.*;

import com.javaex.ex01.Person;

public class PhoneManager{
	private List<Person> pList;
	private Scanner sc;
	
	public PhoneManager() throws IOException {
		sc = new Scanner(System.in);
		pList = new ArrayList<Person>();
	}

	// 시작준비 (시작화면 출력과 리스트 가져온다)
	public void showTitle() throws IOException {
		System.out.println("*************************************************************");
    	System.out.println("*                   전화번호 관리 프로그램                  *");
		System.out.print("*************************************************************");
		
		pList = getList();
	}

	// 메뉴 출력과 입력을 받는다.
	public int showMenu() {
		System.out.println("\n-------------------------------------------------------------");
    	System.out.println("  1. 리스트  |  2. 등록  |  3. 삭제  |  4. 검색  |  5. 종료  ");
    	System.out.println("-------------------------------------------------------------");
    	System.out.print("메뉴 번호 >> ");
    	int menuNum = sc.nextInt();
    	return menuNum;
	}

	// 1.리스트선택시
	public void showList() {
		for(int i = 0; i < pList.size(); i++) {
			System.out.print((i + 1) + ".    ");
        	pList.get(i).showInfo();
        }
	}

	// 2.등록선택시
	public void showAdd() throws IOException {
		// 기존 PhoneDB 파일에 이어 쓰기
		System.out.println("\n-------------------------------------------------------------");
		System.out.println("                      정보를 입력해주세요.                   ");
    	System.out.println("-------------------------------------------------------------");
    	
		System.out.print("이름 >> ");
		String name = sc.next();
		
		System.out.print("전화번호 >> ");
		String hp = sc.next();
		
		System.out.print("회사번호 >> ");
		String company = sc.next();

		// 입력받은 정보 입력
		pList.add(new Person(name, hp, company));
    	saveList();
    	
		System.out.print("\n                   [   등록되었습니다.   ]                   ");
	}

	// 3.삭제선택시
	public void showRemove() throws IOException {
		System.out.println("\n-------------------------------------------------------------");
		System.out.println("                   삭제할 번호를 입력해주세요.               ");
    	System.out.println("-------------------------------------------------------------");
    	System.out.print("번호 >> ");
    	int delNum = sc.nextInt() - 1;
    	
    	// 삭제 할 번호가 유효하면 해당 번호 삭제
    	if(delNum < pList.size() && delNum > 0) {
        	pList.remove(delNum); // 해당 리스트 삭제
        	
			System.out.print("\n                   [   삭제되었습니다.   ]                   ");
    	}
    	// 유효한 번호가 아니면 해당 번호 없음을 알리고 메뉴 나가기
    	else {
    		System.out.println(delNum);
			System.out.print("\n                [   해당 번호가 없습니다.   ]                ");
    	}
    	saveList();
	}

	// 4.검색선택시
	public void showSearch() {
		System.out.println("\n-------------------------------------------------------------");
		System.out.println("                   검색할 글자를 입력해주세요.               ");
    	System.out.println("         (해당 글자가 포함된 모든 목록을 불러옵니다.)        ");
    	System.out.println("-------------------------------------------------------------");
    	System.out.print("입력 >> ");
    	String search = sc.next();
    	
    	for(int i = 0; i < pList.size(); i++) {
        	if(pList.get(i).getName().contains(search)) {
	    		System.out.print((i + 1) + ".    ");
	    		pList.get(i).showInfo();
        	}
        	else if (i == pList.size() - 1) {
    			System.out.print("\n                [   해당 글자가 없습니다.   ]                ");
        	}
    	}
	}

	// 5.종료시
	public void showEnd() {
		System.out.println("\n*************************************************************");
		System.out.println("*                        감사합니다.                        *");
		System.out.println("*************************************************************");
	}
	
	
	// 메뉴번호를 잘못 입력시 안내문구를 출력하는 메소드
	public void showEtc() {
		System.out.print("\n                  [   다시 입력해주세요.   ]                  ");
	}
	
	// 파일을 읽어 리스트에 담아 전달한다.
	private List<Person> getList() throws IOException {
		InputStream in = new FileInputStream("./PhoneDB.txt");
		InputStreamReader ir = new InputStreamReader(in, "UTF-8");
		BufferedReader br = new BufferedReader(ir);
		
		List<Person> pInfoList = new ArrayList<Person>();
		
		while(true) {
    		String str = br.readLine();
			if(str == null) {
				break;
			}
			String[] sArray = str.split(",");
			pInfoList.add(new Person(sArray[0], sArray[1], sArray[2]));
    	}
		br.close();
		return pInfoList;
	}

	// 리스트를 파일에 저장한다.
	private void saveList() throws IOException {
		OutputStream out = new FileOutputStream("./PhoneDB.txt");
		OutputStreamWriter osw = new OutputStreamWriter(out);
		BufferedWriter bw = new BufferedWriter(osw); 

		for(int i = 0; i < pList.size(); i++) {
			bw.write(pList.get(i).toString());
			bw.newLine();
		}
		bw.close();
	}
}
