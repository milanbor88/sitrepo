package com.example.mapper;

import com.example.dto.VozacNezgodaVecaDTO;
import com.example.model.VozacNezgodaVeca;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VozacNezgodaVecaMapper {

    @Autowired
    private ModelMapper modelMapper;


    public List<VozacNezgodaVecaDTO> convertListToDto(List<VozacNezgodaVeca> izvestajVecaList) {
        List<VozacNezgodaVecaDTO>  izvestajVecaDTOS= new ArrayList<VozacNezgodaVecaDTO>();
        izvestajVecaDTOS = izvestajVecaList.stream().map
                (n -> modelMapper.map(n, VozacNezgodaVecaDTO.class))
                .collect(Collectors.toList());
        return izvestajVecaDTOS;
    }

    public List<VozacNezgodaVeca> convertListToEntity(List<VozacNezgodaVecaDTO> izvestajVecaDTOS) throws ParseException {
        List<VozacNezgodaVeca> izvestajVecaList = new ArrayList<VozacNezgodaVeca>();
        izvestajVecaList = izvestajVecaDTOS.stream().map
                (n -> modelMapper.map(n, VozacNezgodaVeca.class)).collect(Collectors.toList());
        return izvestajVecaList;
    }

    public VozacNezgodaVecaDTO convertToDto(VozacNezgodaVeca izvestajVeca) {
        VozacNezgodaVecaDTO izvestajVecaDTO = modelMapper.map(izvestajVeca , VozacNezgodaVecaDTO.class);
        return izvestajVecaDTO;
    }

    public VozacNezgodaVeca convertToEntity(VozacNezgodaVecaDTO izvestajVecaDTO) throws ParseException {
        VozacNezgodaVeca izvestajVeca = modelMapper.map(izvestajVecaDTO, VozacNezgodaVeca.class);
        return izvestajVeca;
    }
}
