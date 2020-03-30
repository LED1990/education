package app.restmvc.mappers;

import app.restmvc.dto.AccountDto;
import model.restmvc.Account;
import org.junit.Assert;
import org.junit.Test;

public class AccountMapperTest {

    private static final String PROVIDER = "provider";
    private AccountMapper accountMapper = AccountMapper.INSTANCE;

    @Test
    public void accountDto() throws Exception {
        //given
        Account accaunt = new Account(PROVIDER, 2, null);

        //when
        AccountDto accauntDto = accountMapper.accountDto(accaunt);

        //then
        Assert.assertEquals(accauntDto.getProvider(), PROVIDER);
        Assert.assertEquals(accauntDto.getAccNumber(), Integer.valueOf(2));
    }

}