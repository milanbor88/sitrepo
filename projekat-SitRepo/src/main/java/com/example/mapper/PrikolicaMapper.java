package com.example.mapper;

import com.example.dto.PrikolicaDTO;
import com.example.model.Prikolica;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PrikolicaMapper {


    @Autowired
    private ModelMapper modelMapper;


    public List<PrikolicaDTO> convertListToDto(List<Prikolica> prikolicas) {
        List<PrikolicaDTO> prikolicaDTOS = new ArrayList<PrikolicaDTO>();
        prikolicaDTOS = prikolicas.stream().map(user -> modelMapper.map(user, PrikolicaDTO.class))
                .collect(Collectors.toList());
        return prikolicaDTOS;
    }

    public List<Prikolica> convertListToEntity(List<PrikolicaDTO> priksDto) throws ParseException {
        List<Prikolica> priks = new ArrayList<Prikolica>();
        priks = priksDto.stream().map(o -> modelMapper.map(o, Prikolica.class)).collect(Collectors.toList());
        return priks;
    }

    public PrikolicaDTO convertToDto(Prikolica pr) {
        PrikolicaDTO prDto = modelMapper.map(pr, PrikolicaDTO.class);
        return prDto;
    }

    public Prikolica convertToEntity(PrikolicaDTO prDto) throws ParseException {
        Prikolica pr = modelMapper.map(prDto, Prikolica.class);
        return pr;
    }

}
