package com.javaex.ex01;
import java.util.*;
import java.io.*;

public class PhoneApp {
    public static void main(String[] args) throws IOException {
    	// 인풋스트림
    	InputStream in = new FileInputStream("./PhoneDB.txt");
		InputStreamReader ir = new InputStreamReader(in, "UTF-8");
		BufferedReader br = new BufferedReader(ir);
				
		// 리스트
    	List<Person> pList = new ArrayList<Person>();
    	Scanner sc = new Scanner(System.in);
    	
		System.out.println("*************************************************************");
    	System.out.println("*                   전화번호 관리 프로그램                  *");
		System.out.print("*************************************************************");
    	
    	while(true) {
    		System.out.println("\n-------------------------------------------------------------");
        	System.out.println("  1. 리스트  |  2. 등록  |  3. 삭제  |  4. 검색  |  5. 종료  ");
        	System.out.println("-------------------------------------------------------------");
        	System.out.print("메뉴 번호 >> ");
        	int menuNum = sc.nextInt();
        	
        	// PhoneDB 읽어서 콤마 기준으로 분류 후 리스트에 담기
    		while(true) {
        		String str = br.readLine();
    			if(str == null) {
    				break;
    			}
    			String[] sArray = str.split(",");
    			pList.add(new Person(sArray[0], sArray[1], sArray[2]));	
        	}
    		
    		// 기능별 반복문
        	switch(menuNum) {
        		// 1번 선택 시 리스트 출력
        		case 1 :
        			for(int i = 0; i < pList.size(); i++) {
            			System.out.print((i + 1) + ".    ");
                    	pList.get(i).showInfo();
                    }
        			continue;
        			
    			// 2번 선택 시 정보 등록
        		case 2:
        			// 기존 PhoneDB 파일에 이어 쓰기
        			OutputStream out = new FileOutputStream("./PhoneDB.txt", true);
        			OutputStreamWriter ow = new OutputStreamWriter(out, "UTF-8");
        			BufferedWriter bw = new BufferedWriter(ow);
        			
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
            		bw.write(name + "," + hp + "," + company);
            		bw.newLine();
                	bw.close();
                	
        			System.out.print("\n                   [   등록되었습니다.   ]                   ");
                	continue;
            	
            	// 3번 선택 시 정보 삭제
        		case 3:        			
        			System.out.println("\n-------------------------------------------------------------");
            		System.out.println("                   삭제할 번호를 입력해주세요.               ");
                	System.out.println("-------------------------------------------------------------");
                	System.out.print("번호 >> ");
                	int delNum = sc.nextInt() - 1;
                	
                	// 삭제 할 번호가 유효하면 해당 번호 삭제
                	if(delNum < pList.size() && delNum > 0) {
                		// 특정 데이터 삭제 후 전체 데이터를 새로 업데이트
                		OutputStream newOut = new FileOutputStream("./PhoneDB.txt");
            			OutputStreamWriter newOsw = new OutputStreamWriter(newOut);
            			BufferedWriter newBw = new BufferedWriter(newOsw); 

                    	pList.remove(delNum); // 해당 리스트 삭제
                    	
                    	// 리스트에 들어있는 값 PhoneDB에 새로 쓰기
            			for(int i = 0; i < pList.size(); i++) {
            				newBw.write(pList.get(i).toString());
            				newBw.newLine();
            			}
            			newBw.close();
            			
            			System.out.print("\n                   [   삭제되었습니다.   ]                   ");
                       	continue;
                	}
                	// 유효한 번호가 아니면 해당 번호 없음을 알리고 메뉴 나가기
                	else {
            			System.out.print("\n                [   해당 번호가 없습니다.   ]                ");
                    	continue;
                	}
                	
            	// 5번 선택 시 종료
        		case 5 :
        			System.out.println("\n*************************************************************");
            		System.out.println("*                        감사합니다.                        *");
            		System.out.println("*************************************************************");
                	break;
                	
            	// 메뉴 번호와 무관한 번호 입력 시 재입력 요청
        		default :
        			System.out.print("\n                  [   다시 입력해주세요.   ]                  ");
        			continue;
        	} // case 끝
        	
        	br.close();
        	break;
        	
    	} // while 끝
    }
}