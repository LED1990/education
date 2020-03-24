package app.reactive.services;

import db.mongo.ComputerReactiveDao;
import model.mongo.Computer;
import mongo.ComputerMock;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

import java.util.List;

import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class ComputersReactiveServiceImplTest {

    @InjectMocks
    private ComputersReactiveServiceImpl computersReactiveService;

    @Mock
    private ComputerReactiveDao computerReactiveDao;


    @Test
    void getAllComputers() {
        //given
        List<Computer> computers = ComputerMock.mockComputers();
        when(computerReactiveDao.findAll()).thenReturn(Flux.just(computers));

        //when
        List<Computer> result = computersReactiveService.getAllComputers().collectList().block();

        //then
        Assert.assertEquals(8, result.size());
    }

}