package com.example.mapper;

import com.example.dto.PovredjeniDrugoVoziloDTO;
import com.example.model.PovredjeniDrugoVozilo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PovredjeniDrugoVoziloMapper {


    @Autowired
    private ModelMapper modelMapper;


    public List<PovredjeniDrugoVoziloDTO> convertListToDto(List<PovredjeniDrugoVozilo> pdvs) {
        List<PovredjeniDrugoVoziloDTO> pdvsDTOS = new ArrayList<PovredjeniDrugoVoziloDTO>();
        pdvsDTOS = pdvs.stream().map(p -> modelMapper.map(p, PovredjeniDrugoVoziloDTO.class))
                .collect(Collectors.toList());
        return pdvsDTOS;
    }

    public List<PovredjeniDrugoVozilo> convertListToEntity(List<PovredjeniDrugoVoziloDTO> pdvsDto) throws ParseException {
        List<PovredjeniDrugoVozilo> pdvs = new ArrayList<PovredjeniDrugoVozilo>();
        pdvs = pdvsDto.stream().map(o -> modelMapper.map(o, PovredjeniDrugoVozilo.class)).collect(Collectors.toList());
        return pdvs;
    }

    public PovredjeniDrugoVoziloDTO convertToDto(PovredjeniDrugoVozilo pdv) {
        PovredjeniDrugoVoziloDTO pdvDto = modelMapper.map(pdv, PovredjeniDrugoVoziloDTO.class);
        return pdvDto;
    }

    public PovredjeniDrugoVozilo convertToEntity(PovredjeniDrugoVoziloDTO pdvDto) throws ParseException {
        PovredjeniDrugoVozilo pdv = modelMapper.map(pdvDto, PovredjeniDrugoVozilo.class);
        return pdv;
    }

}
