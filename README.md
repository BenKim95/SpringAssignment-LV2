# SpringAssignment-Lv2 & Lv3

# Spring Lv.2 & Lv. 3

<aside>
🏁 **Goal:  "회원가입, 로그인 기능이 추가된 나만의 항해 블로그 백엔드 서버 만들기"**

</aside>

- 학습 과제를 끝내고 나면 할 수 있어요!
    1. 회원가입, 로그인을 구현할 수 있어요.
    2. 인증/인가를 이해하고 JWT를 활용하여 게시글을 처리할 수 있어요.
    3. JPA 연관관계를 이해하고 회원과 게시글을 구현할 수 있어요.

<aside>
🤔 **notification : PostMan에서 JWT 를 추가하여 테스트하는 방법**

</aside>

[PostMan JWT 활용 방법 확인하기](https://www.notion.so/PostMan-JWT-4a978c50b1f74b36a733509d95536365?pvs=21)

- [과제 시작 전 읽어보기](https://www.notion.so/Spring-Lv-2-ea5651cd95254cafb2ac922129a213ec?pvs=21)

<aside>
🚩 **Requirement:  과제에 요구되는 사항이에요**

</aside>


⚠️ **아래의 요구사항에 맞게 API 명세서를 수정해 보고 프로젝트를 수정 및 기능을 추가해 보세요!**


1. 회원 가입 API
    - username, password를 Client에서 전달받기
    - username은  `최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 한다. -> (O) Entity 패키지의 User 클래스에 정규식으로 설정  
    - password는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성되어야 한다. -> (O) Entity 패키지의 User 클래스에 정규식으로 설정
    - DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환하기 -> (O) UserService 클래스의 signUp 매서드에 구현

2. 로그인 API
    - username, password를 Client에서 전달받기 -> (O) -> UserService 클래스의 login 매서드 구현
    - DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기 -> (O) UserService 클래스의 login 매서드 구현
    - 로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고, -> (O) UserService 클래스의 login 매서드 구현
      발급한 토큰을 Header에 추가하고 성공했다는 메시지, 상태코드 와 함께 Client에 반환하기 -> (O) UserService 클래스의 login 매서드 구현

3. 전체 게시글 목록 조회 API
    - 제목, 작성자명(username), 작성 내용, 작성 날짜를 조회하기 -> (O) BlogService 클래스의 getBlogs 매서드 구현 Pathvarible 형식으로 단건 조회도 추가
    - 작성 날짜 기준 내림차순으로 정렬하기 -> (O) BlogRepository에 findAllByOrderByCreatedAtDesc 매서드로 구현
   
4. 게시글 작성 API
    - 토큰을 검사하여, 유효한 토큰일 경우에만 게시글 작성 가능 -> (O) BlogService 클래스의 checkToken 매서드로 구현
    - 제목, 작성 내용을 저장하고
    - 저장된 게시글을 Client 로 반환하기(username은 로그인 된 사용자) -> (O) BlogService 클래스의 createBlog 매서드로 구현
   
5. 선택한 게시글 조회 API
    - 선택한 게시글의 제목, 작성자명(username), 작성 날짜, 작성 내용을 조회하기 -> (O) PathVariable 방식으로 BlogService의 getBlog 매서드로 단건 게시글 조회
      (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
   
6. 선택한 게시글 수정 API
    - ~~수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후~~
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 수정 가능 -> (O) BlogService 클래스의 checkToken 매서드로 구현
    - 제목, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기 -> (O) BlogService 클래스의 updateblog 매서드로 구현

7. 선택한 게시글 삭제 API
    - ~~삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후~~
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 삭제 가능 -> (O) BlogService 클래스의 deleteblog 매서드로 구현 (사용자 or 관리자만 삭제할 수 있음)
    - 선택한 게시글을 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기 -> (O) BlogService 클래스의 deleteblog 매서드로 구현

8. 댓글 작성 API
    - 토큰을 검사하여, 유효한 토큰일 경우에만 댓글 작성 가능 -> (O) CommentService 클래스의 checkToken 매서드로 구현
    - 선택한 게시글의 DB 저장 유무를 확인하기 -> (O) CommentService 클래스의 createComment 매서드로 구현
    - 선택한 게시글이 있다면 댓글을 등록하고 등록된 댓글 반환하기 -> (O) CommentService 클래스의 createComment 매서드로 구현
   
9. 댓글 수정 API
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 댓글만 수정 가능 -> (O) CommentService 클래스의 updateComment 매서드로 구현
    - 선택한 댓글의 DB 저장 유무를 확인하기 -> (O) CommentService 클래스의 updateComment 매서드로 구현
    - 선택한 댓글이 있다면 댓글 수정하고 수정된 댓글 반환하기 -> (O) CommentService 클래스의 updateComment 매서드로 구현

10. 댓글 삭제 API
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 댓글만 삭제 가능 -> (O) CommentService 클래스의 deleteComment 매서드로 구현
    - 선택한 댓글의 DB 저장 유무를 확인하기 -> (O) CommentService 클래스의 deleteComment 매서드로 구현
    - 선택한 댓글이 있다면 댓글 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기 -> (O) CommentService 클래스의 deleteComment 매서드로 구현

11. 예외 처리
    - 토큰이 필요한 API 요청에서 토큰을 전달하지 않았거나 정상 토큰이 아닐 때는 "토큰이 유효하지 않습니다." 라는 에러메시지와 statusCode: 400을 Client에 반환하기 -> (O)
    - 토큰이 있고, 유효한 토큰이지만 해당 사용자가 작성한 게시글/댓글이 아닌 경우에는 “작성자만 삭제/수정할 수 있습니다.”라는 에러메시지와 statusCode: 400을 Client에 반환하기 -> (X) 해당 기능을 구현하려 했으나, ApiResult로 orElseThrow하면 에러 발생, ResponseDto 클래스로 매서드를 정의할 경우 ApiResult 클래스로 리턴할 수 없어서 해결을 못했습니다. 
    - DB에 이미 존재하는 username으로 회원가입을 요청한 경우 "중복된 username 입니다." 라는 에러메시지와 statusCode: 400을 Client에 반환하기 -> (X) 위와 같은 이유로 구현하지 못했습니다. 사용자에게 Response 값으로 전달해야 하나 하지 못했음
    - 로그인 시, 전달된 username과 password 중 맞지 않는 정보가 있다면 "회원을 찾을 수 없습니다."라는 에러메시지와 statusCode: 400을 Client에 반환하기 -> (X) 위와 같은 이유로 구현하지 못했습니다. 사용자에게 Response 값으로 전달해야 하나 하지 못했음



1. 처음 설계한 API 명세서에 변경사항이 있었나요?
   변경 되었다면 어떤 점 때문 일까요? 첫 설계의 중요성에 대해 작성해 주세요!
2. ERD를 먼저 설계한 후 Entity를 개발했을 때 어떤 점이 도움이 되셨나요?
3. JWT를 사용하여 인증/인가를 구현 했을 때의 장점은 무엇일까요?
4. 반대로 JWT를 사용한 인증/인가의 한계점은 무엇일까요?
5. 만약 댓글 기능이 있는 블로그에서 댓글이 달려있는 게시글을 삭제하려고 한다면 무슨 문제가 발생할까요? Database 테이블 관점에서 해결방법이 무엇일까요?
6. IoC / DI 에 대해 간략하게 설명해 주세요!