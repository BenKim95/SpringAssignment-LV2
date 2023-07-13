# SpringAssignment-LV2

# Spring Lv.2

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

<aside>
⚠️ **아래의 요구사항에 맞게 API 명세서를 수정해 보고 프로젝트를 수정 및 기능을 추가해 보세요!**

<aside>
☝ **요구사항에 맞게 추가되어야 하는 Entity를 설계하고 ERD를 만들어보세요!**

- [ERD 개념 및 만드는 법](https://inpa.tistory.com/entry/DB-%F0%9F%93%9A-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EB%AA%A8%EB%8D%B8%EB%A7%81-1N-%EA%B4%80%EA%B3%84-%F0%9F%93%88-ERD-%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8)
</aside>

<aside>
✌️ **입문 주차에서 만든 (과제)프로젝트에 회원가입, 로그인 기능을 추가하고 기존 요구사항의 일부를 변경하세요!
변경된 요구사항은 하이라이트로 표시되었습니다.**

</aside>

</aside>

<aside>
☝ **새로운 요구사항을 구현해 보세요!**

</aside>

1. 회원 가입 API
    - username, password를 Client에서 전달받기
    - username은  `최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 한다.
    - password는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성되어야 한다.
    - DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환하기
    - 참고자료
        1. https://mangkyu.tistory.com/174
        2. [https://ko.wikipedia.org/wiki/정규_표현식](https://ko.wikipedia.org/wiki/%EC%A0%95%EA%B7%9C_%ED%91%9C%ED%98%84%EC%8B%9D)
        3. https://bamdule.tistory.com/35


2. 로그인 API
    - username, password를 Client에서 전달받기
    - DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기
    - 로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고,
      발급한 토큰을 Header에 추가하고 성공했다는 메시지, 상태코드 와 함께 Client에 반환하기

<aside>
✌️ **요구사항에 맞게 수정해 보세요!**

</aside>

1. 전체 게시글 목록 조회 API
    - 제목, 작성자명(username), 작성 내용, 작성 날짜를 조회하기
    - 작성 날짜 기준 내림차순으로 정렬하기
2. 게시글 작성 API
    - 토큰을 검사하여, 유효한 토큰일 경우에만 게시글 작성 가능
    - 제목, 작성 내용을 저장하고
    - 저장된 게시글을 Client 로 반환하기(username은 로그인 된 사용자)
3. 선택한 게시글 조회 API
    - 선택한 게시글의 제목, 작성자명(username), 작성 날짜, 작성 내용을 조회하기
      (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
4. 선택한 게시글 수정 API
    - ~~수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후~~
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 수정 가능
    - 제목, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기
5. 선택한 게시글 삭제 API
    - ~~삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후~~
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 삭제 가능
    - 선택한 게시글을 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기

<aside>
📌 개발한 API 테스트 해보기!

</aside>

- Postman을 이용해 HTTP 메서드 요청을 시도해 보세요!
- API 명세서와 ERD 설계 TIP
    - **ERD 설계 →** https://www.erdcloud.com/
    - **API 명세서 작성 툴 →** [https://learnote-dev.com/java/Spring-A-문서-작성하기/](https://learnote-dev.com/java/Spring-A-%EB%AC%B8%EC%84%9C-%EC%9E%91%EC%84%B1%ED%95%98%EA%B8%B0/)

<aside>
🔥 꼭 직접 API 명세서를  수정해 본 다음에 확인하세요!!

</aside>

- API 명세서 예시

  [API 명세서](https://www.notion.so/aa85dd723d3442b2bdfff677402066ee?pvs=21)


<aside>
❓ **Why: 과제 제출시에는 아래 질문을 고민해보고 답변을 함께 제출해주세요.**

</aside>

1. 처음 설계한 API 명세서에 변경사항이 있었나요?
   변경 되었다면 어떤 점 때문 일까요? 첫 설계의 중요성에 대해 작성해 주세요!
2. ERD를 먼저 설계한 후 Entity를 개발했을 때 어떤 점이 도움이 되셨나요?
3. JWT를 사용하여 인증/인가를 구현 했을 때의 장점은 무엇일까요?
4. 반대로 JWT를 사용한 인증/인가의 한계점은 무엇일까요?
5. 만약 댓글 기능이 있는 블로그에서 댓글이 달려있는 게시글을 삭제하려고 한다면 무슨 문제가 발생할까요? Database 테이블 관점에서 해결방법이 무엇일까요?
6. IoC / DI 에 대해 간략하게 설명해 주세요!

<aside>
⚠️ **Warning : 꼭 지켜야 할 것!**

</aside>

- 프로젝트 Github 링크와 API명세서, ERD 를 제출해주세요.
- 프로젝트를 배포까지 하신 분은 배포한 주소를 프로젝트 Github 링크와 API명세서, ERD 와 함께 제출해주세요.
- 이것은 꼭 지켜주세요(Do's)
    - 과제 요구 사항은 모두 지켜주세요. 특정 기능을 임의로 배제하면 안 됩니다!
    - 배포 시 AWS의 RDS를 사용하셨다면 Github에 절대 RDS에 대한 정보를 올리시면 안 됩니다!


<aside>
⚠️ **Warning : 과제가 어려울 수 있어요.**

</aside>

- 2주차에는 **Lv.2** 과제만 수행 하셔도 충분합니다.
  **Lv.2** 과제를 열심히 수행하시고 따라오시면 다음주 과제와 학습을 통해 Lv.3의 내용도 충분히 따라가실 수 있습니다.
  현재 조금 천천히 걷고 있다고 뒤쳐지는게 아닙니다. 앞으로 나아가기 위한 준비입니다.
  지금 힘들어도 조금만 견뎌낸다면 금방 달리실 수 있습니다!  화이팅!!!! 🔥

### <참고>

<aside>
🎯 Spring Data JPA 사용 시 findById의 리턴 타입이 Optional로 고정되어 있기 때문에 Optional을 한번 짚고 넘어가면 좋습니다! 아래 참고자료가 있으니 한번씩 읽어보세요!

</aside>

- Optional 반환타입 CrudRepository 에서 확인해보기

    ```java
    package org.springframework.data.repository;
    
    import java.util.Optional;
    
    import org.springframework.dao.OptimisticLockingFailureException;
    ...
    @NoRepositoryBean
    public interface CrudRepository<T, ID> extends Repository<T, ID> {
    	...
    	Optional<T> findById(ID id);
    	...
    }
    ```

- 참고자료
    1. https://mangkyu.tistory.com/70
    2. https://mangkyu.tistory.com/203

<aside>
🎯 ResponseEntity 사용해보기

</aside>

- 참고자료
    1. https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html
    2. https://www.baeldung.com/spring-response-entity
    3. https://devlog-wjdrbs96.tistory.com/182