package com.example.mapper;

import com.example.dto.KlijentiDTO;
import com.example.model.Klijenti;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class KlijentiMapper {

    @Autowired
    private ModelMapper modelMapper;


    public List<KlijentiDTO> convertListToDto(List<Klijenti> klijentis) {
        List<KlijentiDTO> klijentiDTOS = new ArrayList<KlijentiDTO>();
        klijentiDTOS = klijentis.stream().map(klijenti -> modelMapper.map(klijenti, KlijentiDTO.class))
                .collect(Collectors.toList());
        return klijentiDTOS;
    }

    public List<Klijenti> convertListToEntity(List<KlijentiDTO> klijentiDTOS) throws ParseException {
        List<Klijenti> klijentis = new ArrayList<Klijenti>();
        klijentis = klijentiDTOS.stream().map(k -> modelMapper.map(k, Klijenti.class)).collect(Collectors.toList());
        return klijentis;
    }

    public KlijentiDTO convertToDto(Klijenti klijenti) {
        KlijentiDTO klijentiDTO = modelMapper.map(klijenti, KlijentiDTO.class);

        return klijentiDTO;
    }

    public Klijenti convertToEntity(KlijentiDTO klijentiDTO) throws ParseException {
        Klijenti k = modelMapper.map(klijentiDTO, Klijenti.class);
        return k;
    }

}
