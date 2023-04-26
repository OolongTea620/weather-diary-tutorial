## 실습 영상 메모

1. Persistence (영속성)

프로그램은 종료 되더라도 데이터는 없어지지 않는다.

**Persistence Framework** : 

DB와 연동된는 시스템을 빠르게 개발하고
안정적인 구동을 보장해주는 프레임 워크

**장점** :    
재사용, 유지보수 용이

**종류**

1) SQL Mapper
   
    - SQL을 개발자가 직접 작성 (ex: Select name From students;)
    - 매핑 : 쿼리 수행 결과 <-> 객체 (Object)
    - 단점 : DB 종류 변경시 쿼리 수정 필요, 비슷한 쿼리를 반복적으로 작성해야 함

2) ORM : Object Relation Mapping
    
   - Object와 DB테이블을 매핑
   - java 함수를 사용하면 자동으로 SQL이 만들어짐
   - 매칭 : DB테이블 <-> 객체
   - 단점 : 복잡한 쿼리를 자바 메서드만으로 해결하는 것이 불편함

### JPA 

JpaMemoRepository 구현

#### @GerneratedValue 옵션 종류

- GeneratedType.AUTO

   상황에 맞춰서 알아서 자동적으로 해줘라?

- GeneratedType.IDENTITY

   SpringBoot에서 키 생성을 하지 않고 데이터 베이스에 넣어본 뒤, mysql이 만들어준 키 값이 있으면 그걸 가져옴.

- GeneratedType.SEQUENCE

   데이터베이스 오브젝트를 생성해서 키 생성

- GeneratedType.TABLE
   
   키 생성 만을 위한 테이블을 만들어서 키 생성 관리


## 캐싱 (Caching) 
서버에서 미리 데이터를 저장해 놓는 행위

## 로깅 (Logging)
### loggin library

- log4j -> log4h2 (보안 이슈 있었음)
- logback

### log level(로그레벨)

1. Error :즉시 조치 필요 - DB 연결이 안되는 경우
2. Warn :로직상 유효성 확인 시
3. Info :참고할만한 중요 작업이 끝나는 경우
4. Debug ()
5. Trace ()