package com.example.mapper;

import com.example.dto.PutnikSvedokDTO;
import com.example.model.PutnikSvedok;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PutnikSvedokMapper {


    @Autowired
    private ModelMapper modelMapper;


    public List<PutnikSvedokDTO> convertListToDto(List<PutnikSvedok> ps) {
        List<PutnikSvedokDTO> psDTO = new ArrayList<PutnikSvedokDTO>();
        psDTO = ps.stream().map(p -> modelMapper.map(p, PutnikSvedokDTO.class))
                .collect(Collectors.toList());
        return psDTO;
    }

    public List<PutnikSvedok> convertListToEntity(List<PutnikSvedokDTO> onvsDto) throws ParseException {
        List<PutnikSvedok> ps = new ArrayList<PutnikSvedok>();
        ps = onvsDto.stream().map(p -> modelMapper.map(p, PutnikSvedok.class)).collect(Collectors.toList());
        return ps;
    }

    public PutnikSvedokDTO convertToDto(PutnikSvedok onv) {
        PutnikSvedokDTO psDto = modelMapper.map(onv, PutnikSvedokDTO.class);
        return psDto;
    }

    public PutnikSvedok convertToEntity(PutnikSvedokDTO psDto) throws ParseException {
        PutnikSvedok ps = modelMapper.map(psDto, PutnikSvedok.class);
        return ps;
    }
}
