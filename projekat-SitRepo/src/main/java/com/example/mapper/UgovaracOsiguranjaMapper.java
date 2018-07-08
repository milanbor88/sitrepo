package com.example.mapper;

import com.example.dto.UgovaracOsiguranjaDTO;
import com.example.model.UgovaracOsiguranja;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UgovaracOsiguranjaMapper {


    @Autowired
    private ModelMapper modelMapper;


    public List<UgovaracOsiguranjaDTO> convertListToDto(List<UgovaracOsiguranja> ugovaracOsiguranjas) {
        List<UgovaracOsiguranjaDTO> ugovaracOsiguranjaDTOS = new ArrayList<UgovaracOsiguranjaDTO>();
        ugovaracOsiguranjaDTOS = ugovaracOsiguranjas.stream().map(p -> modelMapper.map(p, UgovaracOsiguranjaDTO.class))
                .collect(Collectors.toList());
        return ugovaracOsiguranjaDTOS;
    }

    public List<UgovaracOsiguranja> convertListToEntity(List<UgovaracOsiguranjaDTO> ugovaracOsiguranjaDTOS) throws ParseException {
        List<UgovaracOsiguranja> ugovaracOsiguranjas = new ArrayList<UgovaracOsiguranja>();
        ugovaracOsiguranjas = ugovaracOsiguranjaDTOS.stream().map(p -> modelMapper.map(p, UgovaracOsiguranja.class)).collect(Collectors.toList());
        return ugovaracOsiguranjas;
    }

    public UgovaracOsiguranjaDTO convertToDto(UgovaracOsiguranja ugovaracOsiguranja) {
        UgovaracOsiguranjaDTO ugovaracOsiguranjaDTO = modelMapper.map(ugovaracOsiguranja, UgovaracOsiguranjaDTO.class);
        return ugovaracOsiguranjaDTO;
    }

    public UgovaracOsiguranja convertToEntity(UgovaracOsiguranjaDTO ugovaracOsiguranjaDTO) throws ParseException {
        UgovaracOsiguranja ugovaracOsiguranja = modelMapper.map(ugovaracOsiguranjaDTO, UgovaracOsiguranja.class);
        return ugovaracOsiguranja;
    }
}
