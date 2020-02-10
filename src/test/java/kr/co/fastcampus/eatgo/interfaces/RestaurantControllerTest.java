package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.domain.MenuItemRepositoryImpl;
import kr.co.fastcampus.eatgo.domain.MyMenuItemRepository;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;
import kr.co.fastcampus.eatgo.domain.RestaurantRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//스프링을 이용해서 이 테스트를 실행할 수 있게 한다.
@RunWith(SpringRunner.class)
//레스토랑 컨트롤러를 테스트 한다를 것을 명시한다.
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    //MockMvc를 구현하지 않아도 스프링에서 자동으로 사용할 수 있도록
    //@Autowired를 사용한다.
    @Autowired
    private MockMvc mvc;

    @SpyBean(RestaurantRepositoryImpl.class)
    private RestaurantRepository restaurantRepository;

    @SpyBean(MenuItemRepositoryImpl.class)
    private MyMenuItemRepository menuItemRepository;


    @Test
    public void list() throws Exception {
        //mvc라는 객체를 이용해서 perform하고
        //get을 처리한다.
        //요청하면 성공적인 결과가 나온지 확인하기 위해 andExpec
        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")
                ));

    }

    @Test
    public void detail() throws Exception {
        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")
                ))
                .andExpect(content().string(
                        containsString("Kimchi")
                ));

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":2020")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber Food\"")
                ));
    }

}