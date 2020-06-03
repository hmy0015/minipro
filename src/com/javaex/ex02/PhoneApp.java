package com.javaex.ex02;
import java.io.IOException;

// 건들지 말 것
public class PhoneApp {

    public static void main(String[] args) throws IOException {

        /*PhoneManager 생성
        화면의 출력, 데이터 입력, 결과출력, 데이터저장, 데이터 가져오기 등의
    	기능을 가지고 있는 클래스
    	*/
        PhoneManager pManager = new PhoneManager();
        
        //타이틀 출력
        pManager.showTitle();

        // while 시작
        boolean run = true;
        while(run){
            // 메뉴 출력, 입력값 리턴
            int menuNum = pManager.showMenu();

            // switch() 시작
            switch(menuNum){
                
	            // 1(리스트)
	            case 1:
	                pManager.showList();
	                break;
	                
	            // 2(등록)
	            case 2:
	                pManager.showAdd();
	                break;
	                
	            // 3(삭제)
	            case 3:
	                pManager.showRemove();
	                break;
	                
	            // 4(검색)
	            case 4:
	                pManager.showSearch();
	                break;
	                
	            // 5(종료)
	            case 5:
	                run = false;
	                break;
	                
	             // 없는 번호일때
	            default :
	            	pManager.showEtc();
	                break;
            
            }// switch() 종료
            
        }// while 종료

        // 종료화면
        pManager.showEnd();
    }

    
    
 
}