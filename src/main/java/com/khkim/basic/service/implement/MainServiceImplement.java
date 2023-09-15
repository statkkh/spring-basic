package com.khkim.basic.service.implement;

// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.khkim.basic.service.MainService;
// 모든 결과물을 여기에서 
// description : ! @Component 해당 클래스를 자바 bean에 등록하여  Spring 이 인스턴스 생성을 알아서 하도록 하는 어노테이션
//  description : ! @Service @Component 와 동일한 작업을 수행하지만 가독성을 위해 Serivice 라는 이름을 가짐
@Service
public class MainServiceImplement implements MainService {

    @Override
    public String getMethod() {
        return "This method is Get Method";
    }
    
}
