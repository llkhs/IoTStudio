package org.example.IoTStudio.service;

import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.IoTStudio.constants.ContractConstants;
import org.example.IoTStudio.model.bo.MainCheckInInputBO;
import org.example.IoTStudio.model.bo.MainCheckTotal1InputBO;
import org.example.IoTStudio.model.bo.MainCheckTotalInputBO;
import org.example.IoTStudio.model.bo.MainGetMonthCheckUsersInputBO;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Data
@EnableAutoConfiguration
@ConfigurationProperties("contract.mainaddress")

public class MainService {
  //@Value("${contract.mainAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public CallResponse owner() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.MainAbi, "owner", Arrays.asList());
  }

  public CallResponse getMonthCheckUsers(MainGetMonthCheckUsersInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.MainAbi, "getMonthCheckUsers", input.toArgs());
  }

  public CallResponse checkTotal(MainCheckTotal1InputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.MainAbi, "checkTotal", input.toArgs());
  }

  public TransactionResponse checkIn(MainCheckInInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.MainAbi, "checkIn", input.toArgs());
  }

  public CallResponse checkTotal(MainCheckTotalInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.MainAbi, "checkTotal", input.toArgs());
  }

  public CallResponse getNow() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.MainAbi, "getNow", Arrays.asList());
  }
}
