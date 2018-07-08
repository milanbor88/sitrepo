package com.example.mapper;

import com.example.dto.VozacNezgodaIzvestajDTO;
import com.example.model.VozacNezgodaIzvestaj;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.NamingConventions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VozacNezgodaIzvestajMapper {

    @Autowired
    private ModelMapper modelMapper;


    public List<VozacNezgodaIzvestajDTO> convertListToDto(List<VozacNezgodaIzvestaj> nezgodaIzvestaj) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
        List<VozacNezgodaIzvestajDTO>  nezgodaIzvestajDTOS= new ArrayList<VozacNezgodaIzvestajDTO>();
        nezgodaIzvestajDTOS = nezgodaIzvestaj.stream().map
                (n -> modelMapper.map(n, VozacNezgodaIzvestajDTO.class))
                .collect(Collectors.toList());
        return nezgodaIzvestajDTOS;
    }

    public List<VozacNezgodaIzvestaj> convertListToEntity(List<VozacNezgodaIzvestajDTO> izvestajDTOS) throws ParseException {

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
        List<VozacNezgodaIzvestaj> izvestaj = new ArrayList<VozacNezgodaIzvestaj>();
        izvestaj = izvestajDTOS.stream().map
                (n -> modelMapper.map(n, VozacNezgodaIzvestaj.class)).collect(Collectors.toList());
        return izvestaj;
    }

    public VozacNezgodaIzvestajDTO convertToDto(VozacNezgodaIzvestaj izvestaj) {

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
        VozacNezgodaIzvestajDTO izvestajDTO = modelMapper.map(izvestaj , VozacNezgodaIzvestajDTO.class);
        return izvestajDTO;
    }

    public VozacNezgodaIzvestaj convertToEntity(VozacNezgodaIzvestajDTO izvestajDTO) throws ParseException {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);

        VozacNezgodaIzvestaj izvestaj = modelMapper.map(izvestajDTO, VozacNezgodaIzvestaj.class);
        return izvestaj;
    }
}
