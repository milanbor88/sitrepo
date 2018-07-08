package com.example.mapper;


import com.example.dto.EvropskiIzvestajIDokaziDTO;
import com.example.model.EvropskiIzvestajIDokazi;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EvropskiIzvestajIDokaziMapper {

    @Autowired
    private ModelMapper modelMapper;


    public List<EvropskiIzvestajIDokaziDTO> convertListToDto(List<EvropskiIzvestajIDokazi> evropskiIzvestajIDokazis) {
        List<EvropskiIzvestajIDokaziDTO> evropskiIzvestajIDokaziDTOS = new ArrayList<EvropskiIzvestajIDokaziDTO>();
        evropskiIzvestajIDokaziDTOS = evropskiIzvestajIDokazis.stream().map(eid -> modelMapper.map(eid, EvropskiIzvestajIDokaziDTO.class)).collect(Collectors.toList());
        return evropskiIzvestajIDokaziDTOS;
    }

    public List<EvropskiIzvestajIDokazi> convertListToEntity(List<EvropskiIzvestajIDokaziDTO> evropskiIzvestajIDokaziDTOS) throws ParseException{
        List<EvropskiIzvestajIDokazi> evropskiIzvestajIDokazis = new ArrayList<EvropskiIzvestajIDokazi>();
        evropskiIzvestajIDokazis = evropskiIzvestajIDokaziDTOS.stream().map(e -> modelMapper.map(e, EvropskiIzvestajIDokazi.class)).collect(Collectors.toList());
        return evropskiIzvestajIDokazis;
    }

    public EvropskiIzvestajIDokaziDTO convertToDto(EvropskiIzvestajIDokazi evropskiIzvestajIDokazi) {
        EvropskiIzvestajIDokaziDTO evropskiIzvestajIDokaziDTO = modelMapper.map(evropskiIzvestajIDokazi, EvropskiIzvestajIDokaziDTO.class);
        return evropskiIzvestajIDokaziDTO;
    }

    public EvropskiIzvestajIDokazi convertToEntity(EvropskiIzvestajIDokaziDTO evropskiIzvestajIDokaziDTO) throws ParseException {
        EvropskiIzvestajIDokazi evropskiIzvestajIDokazi = modelMapper.map(evropskiIzvestajIDokaziDTO, EvropskiIzvestajIDokazi.class);
        return evropskiIzvestajIDokazi;
    }

}
