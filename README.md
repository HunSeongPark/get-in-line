# get-in-line
장소 입장인원 카운팅 API 서비스                                     
[Uno Kim님의 get-in-line 프로젝트](https://github.com/djkeh/get-in-line)를 참고하여 API 형태의 서비스로 변환 및 공부한 내용 추가해보기                 

## Project Goal
- API CRUD
- ERD Model 기반 Entity 구성
- Validation
- Exception Handling (@ExecptionHandler, ErrorCode, ErrorResponse Type, etc ..)
- Spring Security를 활용한 Authentication (로그인 화면만 view로 구성)
- 공부했던 내용 (Spring Boot, Spring Data JPA, QueryDSL, Spring MVC) 최대한 활용해보기
- *** 수행되는 query log 확인하며 query 수행횟수 최적화에 집중해보기 ***

## Project Setting              
* Spring boot version : 2.6.7                   
* Dependencies
  - Spring Web
  - Spring Data JPA
  - Validation
  - Lombok
  - Spring Security
  - QueryDSL
  - ThymeLeaf (로그인 화면 구성에만 사용)
  - H2 Database   

## 정리할 내용(발생한 문제)
- 무결성 참조 문제 오류 발생 (cascade=Cascade.REMOVE, orphanRemoval=true)
```
@OneToMany(mappedBy = "place")
private final Set<Event> events = new LinkedHashSet<>();

*log*
org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException: Referential integrity constraint violation: "FKPUVIX4LEXRAKGDLT8SI1TBTXV: PUBLIC.EVENT FOREIGN KEY(PLACE_ID) REFERENCES PUBLIC.PLACE(PLACE_ID) (CAST(3 AS BIGINT))"; SQL statement:
delete from place where place_id=? [23503-200]
```
