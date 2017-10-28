package com.cfhero.api.tests;

import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cfhero.api.user.User;
import com.cfhero.api.user.UserController;
import com.cfhero.api.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserController.class)
@TestPropertySource(properties = { "http.security.type=NONE", "security.ignored=/**" })
public class InfoTest {

	@Autowired
	private WebApplicationContext wac;

	@MockBean
	UserService service;

	private MockMvc mvc;

	@Before
	public void setup() {
		// mvc =
		// MockMvcBuilders.webAppContextSetup(wac).apply(SecurityMockMvcConfigurers.springSecurity()).build();
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testGetUser() throws Exception {
		User testUser = new User(1L,"test");
		when(service.getUser("1")).thenReturn(testUser);
        mvc.perform(get("/user").param("id", "1"))
            .andExpect(status().isOk())
			.andDo(print())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("id").value("1"));
	}

	// HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
	// when(search.search(info)).thenReturn(r);
	// .andExpect(jsonPath("header.numFound").value("1"))
	// .andExpect(jsonPath("$.searchResponse[0].values.test1[0]").value("test-value"));
}