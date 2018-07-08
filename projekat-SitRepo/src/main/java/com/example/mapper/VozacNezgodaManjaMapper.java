package com.example.mapper;

import com.example.dto.VozacNezgodaManjaDTO;
import com.example.model.VozacNezgodaManja;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VozacNezgodaManjaMapper {


    @Autowired
    private ModelMapper modelMapper;


    public List<VozacNezgodaManjaDTO> convertListToDto(List<VozacNezgodaManja> izvestajManjaList) {
        List<VozacNezgodaManjaDTO>  izvestajManjaDTOS= new ArrayList<VozacNezgodaManjaDTO>();
        izvestajManjaDTOS= izvestajManjaList.stream().map
                (n -> modelMapper.map(n, VozacNezgodaManjaDTO.class))
                .collect(Collectors.toList());
        return izvestajManjaDTOS;
    }

    public List<VozacNezgodaManja> convertListToEntity(List<VozacNezgodaManjaDTO> izvestajManjaDTOS) throws ParseException {
        List<VozacNezgodaManja> izvestajManjaList = new ArrayList<VozacNezgodaManja>();
        izvestajManjaList = izvestajManjaDTOS.stream().map
                (n -> modelMapper.map(n, VozacNezgodaManja.class)).collect(Collectors.toList());
        return izvestajManjaList;
    }

    public VozacNezgodaManjaDTO convertToDto(VozacNezgodaManja izvestajManja) {
        VozacNezgodaManjaDTO izvestajManjaDTO = modelMapper.map(izvestajManja , VozacNezgodaManjaDTO.class);
        return izvestajManjaDTO;
    }

    public VozacNezgodaManja convertToEntity(VozacNezgodaManjaDTO izvestajManjaDTO) throws ParseException {
        VozacNezgodaManja izvestajManja = modelMapper.map(izvestajManjaDTO, VozacNezgodaManja.class);
        return izvestajManja;
    }

}
