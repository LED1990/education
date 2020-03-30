package app.restmvc.mappers;

import app.restmvc.dto.AccountDto;
import model.restmvc.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto accountDto(Account account);
}
