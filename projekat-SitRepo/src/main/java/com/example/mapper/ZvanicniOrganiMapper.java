package com.example.mapper;

import com.example.dto.ZvanicniOrganiDTO;
import com.example.model.ZvanicniOrgani;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ZvanicniOrganiMapper {

    @Autowired
    private ModelMapper modelMapper;


    public List<ZvanicniOrganiDTO> convertListToDto(List<ZvanicniOrgani> zvanicniOrganis) {
        List<ZvanicniOrganiDTO> zvanicniOrganiDTOS = new ArrayList<ZvanicniOrganiDTO>();
        zvanicniOrganiDTOS = zvanicniOrganis.stream().map(zo -> modelMapper.map(zo, ZvanicniOrganiDTO.class))
                .collect(Collectors.toList());
        return zvanicniOrganiDTOS;
    }

    public List<ZvanicniOrgani> convertListToEntity(List<ZvanicniOrganiDTO> zvanicniOrganiDTOS) throws ParseException {
        List<ZvanicniOrgani> zvanicniOrganis = new ArrayList<ZvanicniOrgani>();
        zvanicniOrganis = zvanicniOrganiDTOS.stream().map(zo -> modelMapper.map(zo, ZvanicniOrgani.class)).collect(Collectors.toList());
        return zvanicniOrganis;
    }

    public ZvanicniOrganiDTO convertToDto(ZvanicniOrgani zvanicniOrgani) {
        ZvanicniOrganiDTO zvanicniOrganiDTO = modelMapper.map(zvanicniOrgani, ZvanicniOrganiDTO.class);
        return zvanicniOrganiDTO;
    }

    public ZvanicniOrgani convertToEntity(ZvanicniOrganiDTO zvanicniOrganiDTO) throws ParseException {
        ZvanicniOrgani zvanicniOrgani = modelMapper.map(zvanicniOrganiDTO, ZvanicniOrgani.class);
        return zvanicniOrgani;
    }
}
