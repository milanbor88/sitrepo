package com.example.mapper;

import com.example.dto.OkolnostiNezgodeDTO;
import com.example.dto.UserDTO;
import com.example.model.OkolnostiNezgode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OkolnostiNezgodeMapper {


    @Autowired
    private ModelMapper modelMapper;


    public List<OkolnostiNezgodeDTO> convertListToDto(List<OkolnostiNezgode> okolnostiNezgodes) {
        List<OkolnostiNezgodeDTO> okolnostiNezgodeDTOS = new ArrayList<OkolnostiNezgodeDTO>();
        okolnostiNezgodeDTOS = okolnostiNezgodes.stream().map(o -> modelMapper.map(o, OkolnostiNezgodeDTO.class)).collect(Collectors.toList());
        return okolnostiNezgodeDTOS;
    }

    public List<OkolnostiNezgode> convertListToEntity(List<OkolnostiNezgodeDTO> okolnostiNezgodeDTOS) throws ParseException {
        List<OkolnostiNezgode> okolnostiNezgodes = new ArrayList<OkolnostiNezgode>();
        okolnostiNezgodes = okolnostiNezgodeDTOS.stream().map(o -> modelMapper
                .map(o, OkolnostiNezgode.class)).collect(Collectors.toList());
        return okolnostiNezgodes;
    }

    public OkolnostiNezgodeDTO convertToDto(OkolnostiNezgode okolnostiNezgode) {
        OkolnostiNezgodeDTO okolnostiNezgodeDTO = modelMapper.map(okolnostiNezgode, OkolnostiNezgodeDTO.class);

        return okolnostiNezgodeDTO;
    }

    public OkolnostiNezgode convertToEntity(OkolnostiNezgodeDTO okolnostiNezgodeDTO) throws ParseException {
        OkolnostiNezgode okolnostiNezgode = modelMapper.map(okolnostiNezgodeDTO, OkolnostiNezgode.class);
        return okolnostiNezgode;
    }
}
