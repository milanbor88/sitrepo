package com.example.mapper;

import com.example.dto.OkolnostiNezgodeBDTO;
import com.example.dto.OkolnostiNezgodeDTO;
import com.example.model.OkolnostiNezgode;
import com.example.model.OkolnostiNezgodeB;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OkolnostiNezgodeBMapper {


    @Autowired
    private ModelMapper modelMapper;


    public List<OkolnostiNezgodeBDTO> convertListToDto(List<OkolnostiNezgodeB> okolnostiNezgodeBs) {
        List<OkolnostiNezgodeBDTO> okolnostiNezgodeBDTOS = new ArrayList<OkolnostiNezgodeBDTO>();
        okolnostiNezgodeBDTOS = okolnostiNezgodeBs.stream().map(o -> modelMapper.map(o, OkolnostiNezgodeBDTO.class)).collect(Collectors.toList());
        return okolnostiNezgodeBDTOS;
    }

    public List<OkolnostiNezgodeB> convertListToEntity(List<OkolnostiNezgodeBDTO> okolnostiNezgodeBDTOS) throws ParseException {
        List<OkolnostiNezgodeB> okolnostiNezgodeBs = new ArrayList<OkolnostiNezgodeB>();
        okolnostiNezgodeBs = okolnostiNezgodeBDTOS.stream().map(o -> modelMapper
                .map(o, OkolnostiNezgodeB.class)).collect(Collectors.toList());
        return okolnostiNezgodeBs;
    }

    public OkolnostiNezgodeBDTO convertToDto(OkolnostiNezgodeB okolnostiNezgodeB) {
        OkolnostiNezgodeBDTO okolnostiNezgodeBDTO = modelMapper.map(okolnostiNezgodeB, OkolnostiNezgodeBDTO.class);
        return okolnostiNezgodeBDTO;
    }

    public OkolnostiNezgodeB convertToEntity(OkolnostiNezgodeBDTO okolnostiNezgodeBDTO) throws ParseException {
        OkolnostiNezgodeB okolnostiNezgodeB = modelMapper.map(okolnostiNezgodeBDTO, OkolnostiNezgodeB.class);
        return okolnostiNezgodeB;
    }
}
