package com.phanthasm.currencyConverter;

import com.phanthasm.currencyConverter.dto.CurrencyDTO;
import com.phanthasm.currencyConverter.dto.TransactionDTO;
import com.phanthasm.currencyConverter.dto.TransactionSuccessDTO;
import com.phanthasm.currencyConverter.dto.UserDTO;
import com.phanthasm.currencyConverter.entities.Currency;
import com.phanthasm.currencyConverter.entities.Transaction;
import com.phanthasm.currencyConverter.entities.User;
import com.phanthasm.currencyConverter.repositories.CurrencyRepository;
import com.phanthasm.currencyConverter.repositories.TransactionRepository;
import com.phanthasm.currencyConverter.services.CurrencyService;
import com.phanthasm.currencyConverter.services.TransactionService;
import com.phanthasm.currencyConverter.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {
    @Mock
    TransactionRepository repository;

    @Mock
    CurrencyRepository repositoryCurrency;

    private TransactionService subject;

    @Before
    public void setup() {
        subject = new TransactionService(repository);
    }

    @Test
    public void findAllIsEmpty() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList());

        List<TransactionDTO> transactions = subject.findAll();
        Assert.assertTrue(transactions.isEmpty());
    }

    @Test
    public void findAllHasElements() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dt1 = LocalDateTime.parse("2021-05-19 10:50:37", formatter);
        LocalDateTime dt2 = LocalDateTime.parse("2021-05-15 11:05:28", formatter);

        Transaction t1 = new Transaction(1L, "USD", "BRL", 50.0, 5.3, dt1, new User(1L, "Christine Daae"));
        Transaction t2 = new Transaction(2L, "JPY", "BRL", 63.37, 0.049, dt2, new User(2L, "Lisbeth Salander"));
        List<Transaction> mockTransactions = Arrays.asList(t1, t2);

        Mockito.when(repository.findAll()).thenReturn(mockTransactions);

        List<TransactionDTO> transactions = subject.findAll();
        Assert.assertTrue(!transactions.isEmpty());

        for(int i=0; i < mockTransactions.size(); ++i) {
            Assert.assertEquals(transactions.get(i).getId(), mockTransactions.get(i).getId());
            Assert.assertEquals(transactions.get(i).getCurrencyOrigin(), mockTransactions.get(i).getCurrencyOrigin());
            Assert.assertEquals(transactions.get(i).getCurrencyTarget(), mockTransactions.get(i).getCurrencyTarget());
            Assert.assertEquals(transactions.get(i).getValue(), mockTransactions.get(i).getValue());
            Assert.assertEquals(transactions.get(i).getExchangeRate(), mockTransactions.get(i).getExchangeRate());
            Assert.assertEquals(transactions.get(i).getUser().getId(), mockTransactions.get(i).getUser().getId());
        }
    }

    @Test
    public void saveExpectedElement() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dt1 = LocalDateTime.parse("2021-05-15 11:05:28", formatter);
        Transaction t1 = new Transaction(1L, "USD", "BRL", 50.0, 5.3, dt1, new User(1L, "Christine Daae"));

        Mockito.when(repository.save(t1)).thenReturn(t1);

        TransactionSuccessDTO ts = subject.save(t1);
        Assert.assertEquals(ts.getIdTransaction(), t1.getId());
        Assert.assertEquals(ts.getCurrencyOrigin(), t1.getCurrencyOrigin());
        Assert.assertEquals(ts.getCurrencyTarget(), t1.getCurrencyTarget());
        Assert.assertEquals(ts.getValueOrigin(), t1.getValue());
        Assert.assertEquals(ts.getExchangeRate(), t1.getExchangeRate());
        Assert.assertEquals(ts.getDateTime(), t1.getDateTime());
        Assert.assertEquals(ts.getIdUser(), t1.getUser().getId());

        Double valueTarget = t1.getExchangeRate() * t1.getValue();
        Assert.assertEquals(ts.getValueTarget(), valueTarget);
    }

//    @Test
//    public void convertExpectedElement() {
//        Currency c1 = new Currency("USD", 1.22);
//        Currency c2 = new Currency("BRL", 6.48);
//        Mockito.when(subject.getServiceCurrency().save(c1)).thenReturn(new CurrencyDTO(c1));
//        Mockito.when(subject.getServiceCurrency().save(c2)).thenReturn(new CurrencyDTO(c2));
//
//        User u1 = new User(1L, "Christine Daae");
//        Mockito.when(subject.getServiceUser().save(u1)).thenReturn(new UserDTO(u1));
//
//        Transaction t1 = new Transaction();
//        t1.setCurrencyOrigin("USD");
//        t1.setCurrencyTarget("BRL");
//        t1.setValue(50.0);
//        t1.setUser(u1);
//
//        Optional<TransactionSuccessDTO> success = subject.convert(t1);
//        Assert.assertTrue(success.isPresent());
//
//        TransactionSuccessDTO ts = success.get();
//        Assert.assertTrue(ts.getIdTransaction() > 0);
//        Assert.assertEquals(ts.getCurrencyOrigin(), t1.getCurrencyOrigin());
//        Assert.assertEquals(ts.getCurrencyTarget(), t1.getCurrencyTarget());
//        Assert.assertEquals(ts.getValueOrigin(), t1.getValue());
//        Assert.assertEquals(ts.getExchangeRate(), t1.getExchangeRate());
//        Assert.assertEquals(ts.getDateTime(), t1.getDateTime());
//        Assert.assertEquals(ts.getIdUser(), t1.getUser().getId());
//
//        Double valueTarget = t1.getExchangeRate() * t1.getValue();
//        Assert.assertEquals(ts.getValueTarget(), valueTarget);
//    }
}
