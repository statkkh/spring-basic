package com.khkim.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.khkim.basic.entity.UserEntity;

//  description : @Repository  - 데이터 접근 계층으로 데이터베이스에 접근하여  데이터베이스 작업을 하고  그 결과를 반환하는 영역 //

//  description : @Repository  - @Component 의 역할을 하며, Repository 임을 명시하기 위해 이름만 @Repository 임 
@Repository
//  description : JpaRepository  인터페이스 - Jpa 기반의  Repository 인터페이스를 구현하는 데 사용함 //
//  description : JpaRepository < T,  K> - T 해당 레포지토리에서 사용될 엔터티클래스, ID:해당 클래스에  지정한 기본키 필드의 타입//
public interface UserRepository extends JpaRepository<UserEntity, String>{

    // description  select * from user where email = ""; UserEntity  findByEmail = select * from user where
    UserEntity findByEmail(String email);
    // select * from user where email ="??" and nickname ="";
    UserEntity findByEmailAndNickname(String email, String nickname);
    // SELECT * FROM user where address_detail = "??" order by address desc ;0~n
    List<UserEntity>findByAddressDetailOrderByAddressDesc(String addressDetail);

    boolean existsByEmail(String email);
    // select * from user where email = '?' or nickname = '?' or tel_number = '?'; - 레코드 존재여부
    boolean existsByEmailOrNicknameOrTelNumber(String email,String nickname, String telNumber );
   
    long countByAddress(String address);

    // description :  @Query 쿼리메서드만으로 데이터베이스 작업을 수행할 수 없을 떄 사용하는 어노테이션 
    // description :  JPQL -SQL와 문법 유사 BUT 데이터베이스 테이블이 아닌 엔터티 기준으로  쿼리를 작성 // 
    // description :  @Native Query - SQL 동일 //

    // description :JPQL 
    //select * from user where email = '?' 
    @Query(value="SELECT  u FROM user u WHERE u.email = ?1")
    UserEntity findByEmailJPQL(String email);

    // description : SQL
    @Query(value =  "select * from  user where email = ?1", nativeQuery =true)
    UserEntity findByEmailSQL(String email);

    // SELECT * FROM user where address_detail = "" order by address desc;0~n
    // JPQL
    @Query(value = "select u from  user u WHERE u.email  = ?1 and u.nickname = ?2")
    UserEntity findByEmailAndNicknameJPQL(String email ,String nickname);
    
    //띄어쓰기 조심
    @Query(value=
        "SELECT * " + 
        "FROM user " +
        "WHERE email IN ( " +
        "   SELECT DISTINCT writer_email " +
        "   FROM board " +
        ")"    
    , nativeQuery=true)
    List<UserEntity> getBoardWriterUserList();

}
