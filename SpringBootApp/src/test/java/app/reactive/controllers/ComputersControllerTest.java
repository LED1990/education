package app.reactive.controllers;


import app.reactive.services.interfaces.ComputersReactiveService;
import model.mongo.Computer;
import mongo.ComputerMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.List;

import static reactor.core.publisher.Mono.when;

//@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
//@Import(ComputersReactiveController.class)
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
@WebFluxTest()
class ComputersControllerTest {

    @InjectMocks
    private ComputersReactiveController computersReactiveController;

    @Mock
    private ComputersReactiveService computersReactiveService;

    private WebTestClient webTestClient;


    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(computersReactiveController).build();
    }


    @Test
    void getAllComputers() throws Exception {
        //given
        List<Computer> computers = ComputerMock.mockComputers();

        //when
        when(computersReactiveService.getAllComputers()).thenReturn(Flux.just(computers));

        //then
        webTestClient.get().uri("/reactive/computers/v1/all").exchange().expectStatus().isOk();

    }

}