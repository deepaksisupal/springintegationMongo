package com.mongosi.demo.endpoint;

import com.mongosi.demo.entity.PosLog;
import com.mongosi.demo.entity.Customer;
import org.springframework.core.convert.converter.Converter;


public class PosLogToCustomerConverter implements Converter<PosLog, Customer> {

    @Override
    public Customer convert(PosLog posLog) {
        return new Customer(posLog.getCustomerName(), posLog.getAmount());
    }
}
