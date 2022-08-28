package org.example.IoTStudio.controller;

import cn.hutool.core.lang.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.IoTStudio.model.bo.MainCheckInInputBO;
import org.example.IoTStudio.model.bo.MainCheckTotalInputBO;
import org.example.IoTStudio.service.MainService;
import org.fisco.bcos.sdk.abi.datatypes.Int;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@Api(value = "打卡 Controller", tags = {"打卡 Api"})
@RestController
@RequestMapping("main")
public class MainController {

    MainService mainService;

    @ApiOperation(value = "打卡", notes = "打卡函数")
    @RequestMapping(value = "checkIn", method = RequestMethod.GET)
    public TransactionResponse checkIn(@RequestParam("date") Integer date, @RequestParam("address")String address, @RequestParam("count") Integer count) throws Exception {
        MainCheckInInputBO funcParam = new MainCheckInInputBO();
        funcParam.set_date(BigInteger.valueOf(date));;
        funcParam.set_address(address);
        funcParam.set_count(BigInteger.valueOf(count));
        TransactionResponse response =  mainService.checkIn(funcParam);
        return response;
    }

    @ApiOperation(value = "查询", notes = "查询函数")
    @RequestMapping(value = "getTotal", method = RequestMethod.GET)
    public CallResponse checkTotal(@RequestParam("address")String address) throws Exception {
        MainCheckTotalInputBO param = new MainCheckTotalInputBO();
        param.set_address(address);
        CallResponse response = mainService.checkTotal(param);
        return response;
    }

    @ApiOperation(value = "时间", notes = "时间函数")
    @RequestMapping(value = "getNow", method = RequestMethod.GET)
    public CallResponse getNow() throws Exception {
        CallResponse response = mainService.getNow();
        return response;
    }
}
