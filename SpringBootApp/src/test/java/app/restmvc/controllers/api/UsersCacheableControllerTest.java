package app.restmvc.controllers.api;

import app.cache.services.UserCacheService;
import app.restmvc.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UsersCacheableControllerTest {

    public static final long ID = 1L;
    @InjectMocks
    private UsersCacheableController usersCacheableController;

    @Mock
    private UserCacheService userCacheService;

    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usersCacheableController).build();
    }

    @Test
    public void getWithCacheable() throws Exception {
        //when
        when(userCacheService.getUserByIdCacheable(ID)).thenReturn(new UserDto());

        //then
        mockMvc.perform(get("/api/v1/users/cacheable/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getWithCachePut() throws Exception {
        //when
        when(userCacheService.getUserByIdCachePut(ID)).thenReturn(new UserDto());

        //then
        mockMvc.perform(get("/api/v1/users/cacheput/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void saveOrUpdateUser() throws Exception {
        //given
        UserDto userDto = new UserDto();
        userDto.setId(ID);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestBody = objectWriter.writeValueAsString(userDto);

        //when
        when(userCacheService.saveUserAndEvictCache(new UserDto())).thenReturn(ID);

        //then
        mockMvc.perform(post("/api/v1/users/save").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isOk());
    }

}