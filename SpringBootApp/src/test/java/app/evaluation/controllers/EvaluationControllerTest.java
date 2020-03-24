package app.evaluation.controllers;

import app.evaluation.services.interfaces.EvaluationSearchService;
import model.evaluation.Evaluation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class EvaluationControllerTest {

    @Mock
    private EvaluationSearchService evaluationSearchService;

    @InjectMocks
    private EvaluationController evaluationController;

    private MockMvc mockMvc;
    private Optional<Page<Evaluation>> pageInfo = prepareDataFromDb();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(evaluationController).build();
    }

    @Test
    void searchDataWithPaginationTest() throws Exception {
        //when
        when(evaluationSearchService.getByCriteria(any(), any())).thenReturn(pageInfo);

        //than
        mockMvc.perform(get("/evaluation/v1/search").param("page", "0"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("evalList", pageInfo.get().getContent()))
                .andExpect(model().attribute("totalElements", 10L))
                .andExpect(model().attribute("pageInfo", pageInfo.get()))
                .andExpect(view().name("evaluation/evaluations"));

    }

    @Test
    void searchDataTest() throws Exception {
        //when
        when(evaluationSearchService.getByCriteria(any(), any())).thenReturn(pageInfo);

        //than
        mockMvc.perform(post("/evaluation/v1/search"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("evalList", pageInfo.get().getContent()))
                .andExpect(model().attribute("totalElements", 10L))
                .andExpect(model().attribute("pageInfo", pageInfo.get()))
                .andExpect(view().name("evaluation/evaluations"));
    }

    private Optional<Page<Evaluation>> prepareDataFromDb() {
        List<Evaluation> resultList = new ArrayList<>();
        resultList.add(new Evaluation());
        Page<Evaluation> page = new PageImpl<>(resultList, PageRequest.of(0, 5), 10);
        return Optional.of(page);
    }
}