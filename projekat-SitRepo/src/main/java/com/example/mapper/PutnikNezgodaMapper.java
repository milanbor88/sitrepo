package com.example.mapper;

import com.example.dto.PutnikNezgodaDTO;
import com.example.model.PutnikNezgoda;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PutnikNezgodaMapper {

    @Autowired
    private ModelMapper modelMapper;


    public List<PutnikNezgodaDTO> convertListToDto(List<PutnikNezgoda> pn) {
        List<PutnikNezgodaDTO> pnDTO = new ArrayList<PutnikNezgodaDTO>();
        pnDTO = pn.stream().map(p -> modelMapper.map(p, PutnikNezgodaDTO.class))
                .collect(Collectors.toList());
        return pnDTO;
    }

    public List<PutnikNezgoda> convertListToEntity(List<PutnikNezgodaDTO> onvsDto) throws ParseException {
        List<PutnikNezgoda> pnl = new ArrayList<PutnikNezgoda>();
        pnl = onvsDto.stream().map(p -> modelMapper.map(p, PutnikNezgoda.class)).collect(Collectors.toList());
        return pnl;
    }

    public PutnikNezgodaDTO convertToDto(PutnikNezgoda onv) {
        PutnikNezgodaDTO pnDto = modelMapper.map(onv, PutnikNezgodaDTO.class);
        return pnDto;
    }

    public PutnikNezgoda convertToEntity(PutnikNezgodaDTO pnDto) throws ParseException {
        PutnikNezgoda pn = modelMapper.map(pnDto, PutnikNezgoda.class);
        return pn;
    }
}
