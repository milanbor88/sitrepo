package com.example.mapper;


import com.example.dto.VozacSvedokDTO;
import com.example.model.VozacSvedok;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VozacSvedokMapper {


    @Autowired
    private ModelMapper modelMapper;


    public List<VozacSvedokDTO> convertListToDto(List<VozacSvedok> vozacSvedoks) {
        List<VozacSvedokDTO> vozacSvedokDTOS = new ArrayList<VozacSvedokDTO>();
        vozacSvedokDTOS = vozacSvedoks.stream().map(v -> modelMapper.map(v, VozacSvedokDTO.class))
                .collect(Collectors.toList());
        return vozacSvedokDTOS;
    }

    public List<VozacSvedok> convertListToEntity(List<VozacSvedokDTO> vozacSvedokDTOS) throws ParseException {
        List<VozacSvedok> vozacSvedoks = new ArrayList<VozacSvedok>();
        vozacSvedoks = vozacSvedokDTOS.stream().map(v -> modelMapper.map(v, VozacSvedok.class)).collect(Collectors.toList());
        return vozacSvedoks;
    }

    public VozacSvedokDTO convertToDto(VozacSvedok vozacSvedok) {
        VozacSvedokDTO vozacSvedokDTO = modelMapper.map(vozacSvedok, VozacSvedokDTO.class);
        return vozacSvedokDTO;
    }

    public VozacSvedok convertToEntity(VozacSvedokDTO vozacSvedokDTO) throws ParseException {
        VozacSvedok vozacSvedok = modelMapper.map(vozacSvedokDTO, VozacSvedok.class);
        return vozacSvedok;
    }

}
