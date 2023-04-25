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

### JPA vs JDBC

|___JPA___|___JDBC___|   
|장점| 단점 |
