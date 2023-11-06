# newsRepositoryLab
Spring JPA 실습

Spring Boot MVC 와 Spring Data JPA 로 구현하는 뉴스 게시판 프로그램을 구현한다.

(1) 다음에 제시된 테이블의 사양을 보고 뉴스 게시판 테이블에 대한 엔티티 클래스 News 를
model 패키지에 생성한다. 

id – int -> pk 이며 auto increment
 writer – String
title – String
content – String
writedate – LocalDateTime -> 글 작성 시간으로 자동 설정되도록 애노테이션 설정
cnt – int

(2) repository 패키지에 NewsRepository.java 를 구현한다.

 뉴스글 작성, 뉴스글 전체 읽기, id 로 글 한 개 읽기, 뉴스글 삭제와 변경 등과 관련된 메서드
뉴스 글 내용에서 검색 기능이 필요하며 그리고 글 한 개를 읽은 경우에는 cnt 변경도
필요하다. 이외에도 필요하다고 판단되는 쿼리 메서드 구현은 자율이다.

(3) controller 패키지에 NewController.java 를 구현한다.

GET 방식
URL을 입력하여 요청 -> 전체 뉴스 출력 (매핑명 : /newsmain)
뉴스 제목을 선택하여 요청 -> 뉴스 id 로 해당 뉴스 내용 출력 (매핑명 : /one)
삭제 버튼을 클릭하여 요청 -> 뉴스 id 로 뉴스 삭제 (매핑명 : /delete)
 검색 요청 -> 전달된 검색어로 뉴스글 내용 에서 검색하여 결과 출력 (매핑명 : /search)
리스트에 출력된 작성자 이름을 클릭하여 요청 -> 작성자가 작성한 뉴스 글만 출력
(매핑명 : /writer)

POST 방식
뉴스 작성 (매핑명 : /insert)
뉴스 수정 (매핑명 : /update)

(4) static html 은 만들지 않는다. 모든 응답과 요청은 Thymeleaf 로 구현한다. 템플릿을 여러
파일로 나눠서 만들어도 되지만 하나의 파일로도 가능하다.
제시된 기능의 구현은 모두 필수이며 페이징은 선택적으로 처리한다.
