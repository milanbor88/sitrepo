package com.example.mapper;

import com.example.dto.OsiguranjeDTO;
import com.example.model.Osiguranje;
import com.example.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OsiguranjeMapper {

    @Autowired
    private ModelMapper modelMapper;


    public List<OsiguranjeDTO> convertListToDto(List<Osiguranje> osiguranjes) {
        List<OsiguranjeDTO> osiguranjeDTOS = new ArrayList<OsiguranjeDTO>();
        osiguranjeDTOS = osiguranjes.stream().map(user -> modelMapper.map(user, OsiguranjeDTO.class))
                .collect(Collectors.toList());
        return osiguranjeDTOS;
    }

    public List<Osiguranje> convertListToEntity(List<OsiguranjeDTO> osiguranjeDTOS) throws ParseException {
        List<Osiguranje> osiguranjes = new ArrayList<Osiguranje>();
        osiguranjes = osiguranjeDTOS.stream().map(o -> modelMapper.map(o, Osiguranje.class))
                .collect(Collectors.toList());
        return osiguranjes;
    }

    public OsiguranjeDTO convertToDto(Osiguranje osiguranje) {
        OsiguranjeDTO osiguranjeDTO = modelMapper.map(osiguranje, OsiguranjeDTO.class);

        return osiguranjeDTO;
    }

    public Osiguranje convertToEntity(OsiguranjeDTO osiguranjeDTO) throws ParseException {
        Osiguranje osiguranje = modelMapper.map(osiguranjeDTO, Osiguranje.class);
        return osiguranje;
    }

}
