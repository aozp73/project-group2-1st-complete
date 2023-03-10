package shop.mtcoding.jobara.employee;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import shop.mtcoding.jobara.common.util.RedisService;
import shop.mtcoding.jobara.employee.dto.EmployeeReq.EmployeeUpdateReqDto;
import shop.mtcoding.jobara.user.vo.UserVo;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class EmployeeControllerTest {

      @Autowired
      private MockMvc mvc;

      @Mock
      private EmployeeUpdateReqDto employeeUpdateReqDto;

      @Autowired
      private RedisService redisService;

      private MockHttpSession mockSession;

      @BeforeEach
      public void setUp() {
            UserVo principal = new UserVo();
            principal.setId(1);
            principal.setUsername("ssar");
            principal.setRole("employee");
            principal.setProfile(null);
            redisService.setValue("principal", principal);
            mockSession = new MockHttpSession();
            mockSession.setAttribute("principal", principal);
      }

      @Test
      public void join_test() throws Exception {
            // given
            String requestBody = "username=asdf&password=1234&email=asdf@nate.com";
            // when
            ResultActions resultActions = mvc.perform(post("/employee/join").content(requestBody)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

            // then
            resultActions.andExpect(status().is3xxRedirection());
      }

      @Test
      public void update_test() throws Exception {
            // given
            int id = 1;
            MockMultipartFile file = new MockMultipartFile(
                        "profile", // ???????????? ????????? ????????????????????? ????????? ?????? "profile"??? ???????????????.
                        "filename.txt", // ?????? ????????? ???????????? ?????? ?????? ????????? ???????????????.
                        "image/jpeg", // ?????? ????????? ????????? ????????? jpeg??? ???????????????.
                        "Test data".getBytes() // ?????? ????????? ???????????? ?????? ?????? ????????? ???????????????.
            );

            // when
            ResultActions resultActions = mvc.perform(multipart("/employee/update/" + id)
                        .file(file) // ????????? ???????????????.
                        .param("password", "1234")
                        .param("email", "ssar@nate.com")
                        .param("address", "?????????")
                        .param("detailAddress", "12???")
                        .param("tel", "01099876554")
                        .param("career", "2")
                        .param("education", "??????")
                        .session(mockSession));

            // then
            resultActions.andExpect(status().is3xxRedirection());
      }
}
