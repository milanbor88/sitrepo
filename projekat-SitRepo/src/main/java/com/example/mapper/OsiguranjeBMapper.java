package com.example.mapper;

import com.example.dto.OsiguranjeBDTO;
import com.example.dto.OsiguranjeDTO;
import com.example.model.Osiguranje;
import com.example.model.OsiguranjeB;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OsiguranjeBMapper {

    @Autowired
    private ModelMapper modelMapper;


    public List<OsiguranjeBDTO> convertListToDto(List<OsiguranjeB> osiguranjesB) {
        List<OsiguranjeBDTO> osiguranjeBDTOS = new ArrayList<OsiguranjeBDTO>();
        osiguranjeBDTOS = osiguranjesB.stream().map(ob -> modelMapper.map(ob, OsiguranjeBDTO.class))
                .collect(Collectors.toList());
        return osiguranjeBDTOS;
    }

    public List<OsiguranjeB> convertListToEntity(List<OsiguranjeBDTO> osiguranjeBDTOS) throws ParseException {
        List<OsiguranjeB> osiguranjesB = new ArrayList<OsiguranjeB>();
        osiguranjesB = osiguranjeBDTOS.stream().map(ob -> modelMapper.map(ob, OsiguranjeB.class))
                .collect(Collectors.toList());
        return osiguranjesB;
    }

    public OsiguranjeBDTO convertToDto(OsiguranjeB osiguranjeB) {
        OsiguranjeBDTO osiguranjeBDTO = modelMapper.map(osiguranjeB, OsiguranjeBDTO.class);

        return osiguranjeBDTO;
    }

    public OsiguranjeB convertToEntity(OsiguranjeBDTO osiguranjeBDTO) throws ParseException {
        OsiguranjeB osiguranjeB = modelMapper.map(osiguranjeBDTO, OsiguranjeB.class);
        return osiguranjeB;
    }

}
