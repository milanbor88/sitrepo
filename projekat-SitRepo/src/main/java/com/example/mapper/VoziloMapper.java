package com.example.mapper;

import com.example.dto.VoziloDTO;
import com.example.model.Vozilo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VoziloMapper {

    @Autowired
    private ModelMapper modelMapper;


    public List<VoziloDTO> convertListToDto(List<Vozilo> vozilos) {
        List<VoziloDTO> voziloDTOS = new ArrayList<VoziloDTO>();
        voziloDTOS = vozilos.stream().map(v -> modelMapper.map(v, VoziloDTO.class))
                .collect(Collectors.toList());
        return voziloDTOS;
    }

    public List<Vozilo> convertListToEntity(List<VoziloDTO> voziloDTOS) throws ParseException {
        List<Vozilo> vozilos = new ArrayList<Vozilo>();
        vozilos = voziloDTOS.stream().map(v -> modelMapper.map(v, Vozilo.class)).collect(Collectors.toList());
        return vozilos;
    }

    public VoziloDTO convertToDto(Vozilo vozilo) {
        VoziloDTO voziloDTO = modelMapper.map(vozilo, VoziloDTO.class);
        return voziloDTO;
    }

    public Vozilo convertToEntity(VoziloDTO voziloDTO) throws ParseException {
        Vozilo vozilo = modelMapper.map(voziloDTO, Vozilo.class);
        return vozilo;
    }
}
