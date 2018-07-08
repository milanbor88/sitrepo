package com.example.mapper;

import com.example.dto.VoziloBDTO;
import com.example.dto.VoziloDTO;
import com.example.model.Vozilo;
import com.example.model.VoziloB;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VoziloBMapper {

    @Autowired
    private ModelMapper modelMapper;


    public List<VoziloBDTO> convertListToDto(List<VoziloB> voziloBS) {
        List<VoziloBDTO> voziloBDTOS = new ArrayList<VoziloBDTO>();
        voziloBDTOS = voziloBS.stream().map(vb -> modelMapper.map(vb, VoziloBDTO.class))
                .collect(Collectors.toList());
        return voziloBDTOS;
    }

    public List<VoziloB> convertListToEntity(List<VoziloBDTO> voziloBDTOS) throws ParseException {
        List<VoziloB> vozilosB = new ArrayList<VoziloB>();
        vozilosB = voziloBDTOS.stream().map(vb -> modelMapper.map(vb, VoziloB.class)).collect(Collectors.toList());
        return vozilosB;
    }

    public VoziloBDTO convertToDto(VoziloB voziloB) {
        VoziloBDTO voziloBDTO = modelMapper.map(voziloB, VoziloBDTO.class);
        return voziloBDTO;
    }

    public VoziloB convertToEntity(VoziloBDTO voziloBDTO) throws ParseException {
        VoziloB voziloB = modelMapper.map(voziloBDTO, VoziloB.class);
        return voziloB;
    }
}
