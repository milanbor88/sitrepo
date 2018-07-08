package com.example.mapper;

import com.example.dto.PovredjeniVaseVoziloDTO;
import com.example.model.PovredjeniVaseVozilo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PovredjeniVaseVoziloMapper {

    @Autowired
    private ModelMapper modelMapper;


    public List<PovredjeniVaseVoziloDTO> convertListToDto(List<PovredjeniVaseVozilo> povredjeniVaseVozilos) {
        List<PovredjeniVaseVoziloDTO> povredjeniVaseVoziloDTOS = new ArrayList<PovredjeniVaseVoziloDTO>();
        povredjeniVaseVoziloDTOS = povredjeniVaseVozilos.stream().map(pvv -> modelMapper.map(pvv, PovredjeniVaseVoziloDTO.class))
                .collect(Collectors.toList());
        return povredjeniVaseVoziloDTOS;
    }

    public List<PovredjeniVaseVozilo> convertListToEntity(List<PovredjeniVaseVoziloDTO> povredjeniVaseVoziloDTOS) throws ParseException {
        List<PovredjeniVaseVozilo> povredjeniVaseVozilos = new ArrayList<PovredjeniVaseVozilo>();
        povredjeniVaseVozilos = povredjeniVaseVoziloDTOS.stream().map(pvv -> modelMapper.map(pvv, PovredjeniVaseVozilo.class)).collect(Collectors.toList());
        return povredjeniVaseVozilos;
    }

    public PovredjeniVaseVoziloDTO convertToDto(PovredjeniVaseVozilo povredjeniVaseVozilo) {
        PovredjeniVaseVoziloDTO povredjeniVaseVoziloDTO = modelMapper.map(povredjeniVaseVozilo, PovredjeniVaseVoziloDTO.class);
        return povredjeniVaseVoziloDTO;
    }

    public PovredjeniVaseVozilo convertToEntity(PovredjeniVaseVoziloDTO povredjeniVaseVoziloDTO) throws ParseException {
        PovredjeniVaseVozilo povredjeniVaseVozilo = modelMapper.map(povredjeniVaseVoziloDTO, PovredjeniVaseVozilo.class);
        return povredjeniVaseVozilo;
    }

}
