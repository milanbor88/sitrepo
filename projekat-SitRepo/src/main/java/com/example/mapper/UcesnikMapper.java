package com.example.mapper;

import com.example.dto.UcesnikDTO;
import com.example.model.Ucesnik;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UcesnikMapper {


    @Autowired
    private ModelMapper modelMapper;


    public List<UcesnikDTO> convertListToDto(List<Ucesnik> ucesnik) {
        List<UcesnikDTO> ucesnikDTOS = new ArrayList<UcesnikDTO>();
        ucesnikDTOS = ucesnik.stream().map(p -> modelMapper.map(p, UcesnikDTO.class))
                .collect(Collectors.toList());
        return ucesnikDTOS;
    }

    public List<Ucesnik> convertListToEntity(List<UcesnikDTO> ucesnikDTOS) throws ParseException {
        List<Ucesnik> ucesniks = new ArrayList<Ucesnik>();
        ucesniks = ucesnikDTOS.stream().map(p -> modelMapper.map(p, Ucesnik.class)).collect(Collectors.toList());
        return ucesniks;
    }

    public UcesnikDTO convertToDto(Ucesnik ucesnik) {
        UcesnikDTO ucesnikDTOS = modelMapper.map(ucesnik, UcesnikDTO.class);
        ucesnikDTOS.setVozackaDozvolaVaziDo(ucesnik.getVozackaDozvolaVaziDo());
        return ucesnikDTOS;
    }

    public Ucesnik convertToEntity(UcesnikDTO ucesnikDTO) throws ParseException {
        Ucesnik onv = modelMapper.map(ucesnikDTO, Ucesnik.class);
        onv.setVozackaDozvolaVaziDo(ucesnikDTO.getVozackaDozvolaVaziDo());
        return onv;
    }
}
