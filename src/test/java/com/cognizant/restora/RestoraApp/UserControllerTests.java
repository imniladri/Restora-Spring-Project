package com.cognizant.restora.RestoraApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.niladrimondal.RestoraApp.bean.UserBean;
import com.niladrimondal.RestoraApp.controller.UserController;
import com.niladrimondal.RestoraApp.entity.User;
import com.niladrimondal.RestoraApp.service.UserService;
import com.niladrimondal.RestoraApp.util.LoginUser;

public class UserControllerTests {

	@Mock
	private Model model;

	@Mock
	private ModelMapper modelMapper;

	@Mock
	private MockHttpSession session;

	@Mock
	private BindingResult result;

	@Mock
	private UserService userServiceMock;

	@InjectMocks
	private UserController userControllerMock;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testUserLogin() {

		String username = "newuser";
		String password = "newuser";

		LoginUser loginMock = new LoginUser();
		loginMock.setUsername(username);
		loginMock.setUsername(password);

		when(userServiceMock.validateLogin(loginMock)).thenReturn("Username Doesn't Exists!");

		String viewname = userControllerMock.getUserLogin(loginMock, result, model, session);

		assertEquals("login", viewname);

		verify(userServiceMock).validateLogin(loginMock);

		verify(model).addAttribute("errorMsg", "Username Doesn't Exists!");

	}

	@Test
	public void testUserRegister() {

		UserBean userBean = new UserBean();

		User user = modelMapper.map(userBean, User.class);

		when(userServiceMock.validateUser(user)).thenReturn(true);
		when(userServiceMock.validateEmail(user)).thenReturn(true);
		when(userServiceMock.registerUser(user)).thenReturn("success");

		String viewname = userControllerMock.getUserRegister(userBean, result, model);

		assertEquals("success", viewname);

		verify(userServiceMock).validateUser(user);
		verify(userServiceMock).validateEmail(user);
		verify(userServiceMock).registerUser(user);

	}

	@Test
	public void testUserDashboard() {

		String username = "newuser";

		User mockUser = new User();

		when(userServiceMock.getUser(username)).thenReturn(mockUser);

		String viewname = userControllerMock.showUserDashboardPage(session, model, username);

		assertEquals("redirect:/login", viewname);

	}

}
