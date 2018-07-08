package com.example.mapper;

import com.example.dto.PutnikIzvestajDTO;
import com.example.model.PutnikIzvestaj;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PutnikIzvestajMapper {

    @Autowired
    private ModelMapper modelMapper;


    public List<PutnikIzvestajDTO> convertListToDto(List<PutnikIzvestaj> putnikIzvestajs) {
        List<PutnikIzvestajDTO> putnikIzvestajDTOS = new ArrayList<PutnikIzvestajDTO>();
        putnikIzvestajDTOS = putnikIzvestajs.stream().map(pi -> modelMapper.map(pi, PutnikIzvestajDTO.class))
                .collect(Collectors.toList());
        return putnikIzvestajDTOS;
    }

    public List<PutnikIzvestaj> convertListToEntity(List<PutnikIzvestajDTO> putnikIzvestajDTOS) throws ParseException {
        List<PutnikIzvestaj> putnikIzvestajs = new ArrayList<PutnikIzvestaj>();
        putnikIzvestajs = putnikIzvestajDTOS.stream().map(pi -> modelMapper.map(pi, PutnikIzvestaj.class)).collect(Collectors.toList());
        return putnikIzvestajs;
    }

    public PutnikIzvestajDTO convertToDto(PutnikIzvestaj putnikIzvestaj) {
        PutnikIzvestajDTO putnikIzvestajDTO = modelMapper.map(putnikIzvestaj, PutnikIzvestajDTO.class);
        return putnikIzvestajDTO;
    }

    public PutnikIzvestaj convertToEntity(PutnikIzvestajDTO putnikIzvestajDTO) throws ParseException {
        PutnikIzvestaj putnikIzvestaj = modelMapper.map(putnikIzvestajDTO, PutnikIzvestaj.class);
        return putnikIzvestaj;
    }
}
