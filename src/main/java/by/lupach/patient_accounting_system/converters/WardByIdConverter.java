package by.lupach.patient_accounting_system.converters;

import by.lupach.patient_accounting_system.entities.Ward;
import by.lupach.patient_accounting_system.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WardByIdConverter implements Converter<String, Ward> {

    @Autowired
    WardService wardService;

    @Override
    public Ward convert(String source){
        return wardService.getById(Integer.parseInt(source)).get();
    }
}
