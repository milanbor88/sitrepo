package com.example.mapper;

import com.example.dto.PovredjeniVanVozilaDTO;
import com.example.model.PovredjeniVanVozila;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PovredjeniVanVozilaMapper {


    @Autowired
    private ModelMapper modelMapper;


    public List<PovredjeniVanVozilaDTO> convertListToDto(List<PovredjeniVanVozila> pvvs) {
        List<PovredjeniVanVozilaDTO> pvvsDTOS = new ArrayList<PovredjeniVanVozilaDTO>();
        pvvsDTOS = pvvs.stream().map(p -> modelMapper.map(p, PovredjeniVanVozilaDTO.class))
                .collect(Collectors.toList());
        return pvvsDTOS;
    }

    public List<PovredjeniVanVozila> convertListToEntity(List<PovredjeniVanVozilaDTO> pvvsDto) throws ParseException {
        List<PovredjeniVanVozila> pvvs = new ArrayList<PovredjeniVanVozila>();
        pvvs = pvvsDto.stream().map(p -> modelMapper.map(p, PovredjeniVanVozila.class)).collect(Collectors.toList());
        return pvvs;
    }

    public PovredjeniVanVozilaDTO convertToDto(PovredjeniVanVozila pvv) {
        PovredjeniVanVozilaDTO pvvDto = modelMapper.map(pvv, PovredjeniVanVozilaDTO.class);
        return pvvDto;
    }

    public PovredjeniVanVozila convertToEntity(PovredjeniVanVozilaDTO pvvDto) throws ParseException {
        PovredjeniVanVozila pvv = modelMapper.map(pvvDto, PovredjeniVanVozila.class);
        return pvv;
    }

}
