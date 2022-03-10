package com.quabbly.stock.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class ConfigurationFile {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }

}
