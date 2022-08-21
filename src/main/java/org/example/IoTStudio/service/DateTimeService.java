package org.example.IoTStudio.service;

import java.lang.Exception;
import java.lang.String;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.IoTStudio.constants.ContractConstants;
import org.example.IoTStudio.model.bo.DateTimeGetDayInputBO;
import org.example.IoTStudio.model.bo.DateTimeGetDaysInMonthInputBO;
import org.example.IoTStudio.model.bo.DateTimeGetHourInputBO;
import org.example.IoTStudio.model.bo.DateTimeGetMinuteInputBO;
import org.example.IoTStudio.model.bo.DateTimeGetMonthInputBO;
import org.example.IoTStudio.model.bo.DateTimeGetSecondInputBO;
import org.example.IoTStudio.model.bo.DateTimeGetWeekdayInputBO;
import org.example.IoTStudio.model.bo.DateTimeGetYearInputBO;
import org.example.IoTStudio.model.bo.DateTimeIsLeapYearInputBO;
import org.example.IoTStudio.model.bo.DateTimeLeapYearsBeforeInputBO;
import org.example.IoTStudio.model.bo.DateTimeToTimestamp1InputBO;
import org.example.IoTStudio.model.bo.DateTimeToTimestamp2InputBO;
import org.example.IoTStudio.model.bo.DateTimeToTimestamp3InputBO;
import org.example.IoTStudio.model.bo.DateTimeToTimestampInputBO;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Data
public class DateTimeService {
  @Value("${contract.dateTimeAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public TransactionResponse getDay(DateTimeGetDayInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.DateTimeAbi, "getDay", input.toArgs());
  }

  public TransactionResponse getMonth(DateTimeGetMonthInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.DateTimeAbi, "getMonth", input.toArgs());
  }

  public TransactionResponse getMinute(DateTimeGetMinuteInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.DateTimeAbi, "getMinute", input.toArgs());
  }

  public TransactionResponse getWeekday(DateTimeGetWeekdayInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.DateTimeAbi, "getWeekday", input.toArgs());
  }

  public TransactionResponse toTimestamp(DateTimeToTimestamp1InputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.DateTimeAbi, "toTimestamp", input.toArgs());
  }

  public TransactionResponse toTimestamp(DateTimeToTimestampInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.DateTimeAbi, "toTimestamp", input.toArgs());
  }

  public TransactionResponse isLeapYear(DateTimeIsLeapYearInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.DateTimeAbi, "isLeapYear", input.toArgs());
  }

  public TransactionResponse getYear(DateTimeGetYearInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.DateTimeAbi, "getYear", input.toArgs());
  }

  public TransactionResponse getDaysInMonth(DateTimeGetDaysInMonthInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.DateTimeAbi, "getDaysInMonth", input.toArgs());
  }

  public TransactionResponse getSecond(DateTimeGetSecondInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.DateTimeAbi, "getSecond", input.toArgs());
  }

  public TransactionResponse leapYearsBefore(DateTimeLeapYearsBeforeInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.DateTimeAbi, "leapYearsBefore", input.toArgs());
  }

  public TransactionResponse getHour(DateTimeGetHourInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.DateTimeAbi, "getHour", input.toArgs());
  }

  public TransactionResponse toTimestamp(DateTimeToTimestamp2InputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.DateTimeAbi, "toTimestamp", input.toArgs());
  }

  public TransactionResponse toTimestamp(DateTimeToTimestamp3InputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.DateTimeAbi, "toTimestamp", input.toArgs());
  }
}
