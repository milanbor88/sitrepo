package com.example.mapper;


import com.example.dto.DrzaveDTO;
import com.example.dto.UserDTO;
import com.example.model.Drzave;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DrzaveMapper {
    @Autowired
    private ModelMapper modelMapper;


    public List<DrzaveDTO> convertListToDto(List<Drzave> drzaves) {
        List<DrzaveDTO>  drzaveDTOS= new ArrayList<DrzaveDTO>();
        drzaveDTOS = drzaves.stream().map(d -> modelMapper.map(d, DrzaveDTO.class))
                .collect(Collectors.toList());
        return drzaveDTOS;
    }

    public List<Drzave> convertListToEntity(List<DrzaveDTO> drzaveDTOS) throws ParseException {
        List<Drzave> drzaves = new ArrayList<Drzave>();
        drzaves = drzaveDTOS.stream().map(d -> modelMapper.map(d, Drzave.class)).collect(Collectors.toList());
        return drzaves;
    }

    public DrzaveDTO convertToDto(Drzave drzave) {
        DrzaveDTO drzaveDTO = modelMapper.map(drzave, DrzaveDTO.class);
        return drzaveDTO;
    }

    public Drzave convertToEntity(DrzaveDTO drzaveDTO) throws ParseException {
        Drzave drzave = modelMapper.map(drzaveDTO, Drzave.class);
        return drzave;
    }
}
