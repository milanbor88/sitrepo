package com.example.mapper;

import com.example.dto.PrikolicaBDTO;
import com.example.dto.PrikolicaDTO;
import com.example.model.Prikolica;
import com.example.model.PrikolicaB;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PrikolicaBMapper {


    @Autowired
    private ModelMapper modelMapper;


    public List<PrikolicaBDTO> convertListToDto(List<PrikolicaB> prikolicasB) {
        List<PrikolicaBDTO> prikolicaBDTOS = new ArrayList<PrikolicaBDTO>();
        prikolicaBDTOS = prikolicasB.stream().map(b -> modelMapper.map(b, PrikolicaBDTO.class))
                .collect(Collectors.toList());
        return prikolicaBDTOS;
    }

    public List<PrikolicaB> convertListToEntity(List<PrikolicaBDTO> prikolicaBDTOS) throws ParseException {
        List<PrikolicaB> prikolicaBS = new ArrayList<PrikolicaB>();
        prikolicaBS = prikolicaBDTOS.stream().map(b -> modelMapper.map(b, PrikolicaB.class)).collect(Collectors.toList());
        return prikolicaBS;
    }

    public PrikolicaBDTO convertToDto(PrikolicaB prikolicaB) {
        PrikolicaBDTO prikolicaBDTO = modelMapper.map(prikolicaB, PrikolicaBDTO.class);
        return prikolicaBDTO;
    }

    public PrikolicaB convertToEntity(PrikolicaBDTO prikolicaBDTO) throws ParseException {
        PrikolicaB prikolicaB = modelMapper.map(prikolicaBDTO, PrikolicaB.class);
        return prikolicaB;
    }

}
