package com.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Data //@Setter， @Getter，@ToString，@EqualsAndHashCode
//@ToString
//@Setter
//@Getter
//@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@SpringBootTest
public class LombokTests {
    private Integer id;
    @Test
    void testLombok(){
        log.info("lombok log test");
        setId(100);
        getId();
        System.out.println(toString());
    }
}
