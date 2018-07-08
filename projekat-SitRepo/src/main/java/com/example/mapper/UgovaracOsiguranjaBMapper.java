package com.example.mapper;

import com.example.dto.UgovaracOsiguranjaBDTO;
import com.example.dto.UgovaracOsiguranjaDTO;
import com.example.model.UgovaracOsiguranja;
import com.example.model.UgovaracOsiguranjaB;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UgovaracOsiguranjaBMapper {


    @Autowired
    private ModelMapper modelMapper;


    public List<UgovaracOsiguranjaBDTO> convertListToDto(List<UgovaracOsiguranjaB> ugovaracOsiguranjaBS) {
        List<UgovaracOsiguranjaBDTO> ugovaracOsiguranjaBDTOS = new ArrayList<UgovaracOsiguranjaBDTO>();
        ugovaracOsiguranjaBDTOS = ugovaracOsiguranjaBS.stream().map(p -> modelMapper.map(p, UgovaracOsiguranjaBDTO.class))
                .collect(Collectors.toList());
        return ugovaracOsiguranjaBDTOS;
    }

    public List<UgovaracOsiguranjaB> convertListToEntity(List<UgovaracOsiguranjaBDTO> ugovaracOsiguranjaBDTOS) throws ParseException {
        List<UgovaracOsiguranjaB> ugovaracOsiguranjaBS = new ArrayList<UgovaracOsiguranjaB>();
        ugovaracOsiguranjaBS = ugovaracOsiguranjaBDTOS.stream().map(p -> modelMapper.map(p, UgovaracOsiguranjaB.class)).collect(Collectors.toList());
        return ugovaracOsiguranjaBS;
    }

    public UgovaracOsiguranjaBDTO convertToDto(UgovaracOsiguranjaB ugovaracOsiguranjaB) {
        UgovaracOsiguranjaBDTO ugovaracOsiguranjaBDTO = modelMapper.map(ugovaracOsiguranjaB, UgovaracOsiguranjaBDTO.class);
        return ugovaracOsiguranjaBDTO;
    }

    public UgovaracOsiguranjaB convertToEntity(UgovaracOsiguranjaBDTO ugovaracOsiguranjaBDTO) throws ParseException {
        UgovaracOsiguranjaB ugovaracOsiguranjaB = modelMapper.map(ugovaracOsiguranjaBDTO, UgovaracOsiguranjaB.class);
        return ugovaracOsiguranjaB;
    }
}
