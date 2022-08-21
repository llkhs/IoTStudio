package org.example.IoTStudio.service;

import java.lang.Exception;
import java.lang.String;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.IoTStudio.constants.ContractConstants;
import org.example.IoTStudio.model.bo.Uint2strStrConcatInputBO;
import org.example.IoTStudio.model.bo.Uint2strToStrInputBO;
import org.example.IoTStudio.model.bo.Uint2strUintToStringInputBO;
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
public class Uint2strService {
  @Value("${contract.uint2strAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public TransactionResponse strConcat(Uint2strStrConcatInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.Uint2strAbi, "strConcat", input.toArgs());
  }

  public TransactionResponse toStr(Uint2strToStrInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.Uint2strAbi, "toStr", input.toArgs());
  }

  public TransactionResponse uintToString(Uint2strUintToStringInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.Uint2strAbi, "uintToString", input.toArgs());
  }
}
