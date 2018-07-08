package com.example.mapper;

import com.example.dto.VozacNezgodaDTO;
import com.example.model.VozacNezgoda;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VozacNezgodaMapper {


    @Autowired
    private ModelMapper modelMapper;


    public List<VozacNezgodaDTO> convertListToDto(List<VozacNezgoda> vozacNezgodas) {
        List<VozacNezgodaDTO> vozacNezgodaDTOS = new ArrayList<VozacNezgodaDTO>();
        vozacNezgodaDTOS = vozacNezgodas.stream().map(p -> modelMapper.map(p, VozacNezgodaDTO.class))
                .collect(Collectors.toList());
        return vozacNezgodaDTOS;
    }

    public List<VozacNezgoda> convertListToEntity(List<VozacNezgodaDTO> vozacNezgodaDTOS) throws ParseException {
        List<VozacNezgoda> vozacNezgodas = new ArrayList<VozacNezgoda>();
        vozacNezgodas = vozacNezgodaDTOS.stream().map(v -> modelMapper.map(v, VozacNezgoda.class)).collect(Collectors.toList());
        return vozacNezgodas;
    }

    public VozacNezgodaDTO convertToDto(VozacNezgoda vozacNezgoda) {
        VozacNezgodaDTO vozacNezgodaDTO = modelMapper.map(vozacNezgoda, VozacNezgodaDTO.class);
        return vozacNezgodaDTO;
    }

    public VozacNezgoda convertToEntity(VozacNezgodaDTO vozacNezgodaDTO) throws ParseException {
        VozacNezgoda vozacNezgoda = modelMapper.map(vozacNezgodaDTO, VozacNezgoda.class);
        return vozacNezgoda;
    }
}
