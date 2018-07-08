package com.example.mapper;

import com.example.dto.KlijentiDTO;
import com.example.dto.TipKlijentaDTO;
import com.example.model.Klijenti;
import com.example.model.TipKlijenta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TipKlijentaMapper {

    @Autowired
    private ModelMapper modelMapper;


    public List<TipKlijentaDTO> convertListToDto(List<TipKlijenta> tipKlijentas) {
        List<TipKlijentaDTO> tipKlijentaDTOS = new ArrayList<TipKlijentaDTO>();
        tipKlijentaDTOS = tipKlijentas.stream().map(tipKlijenta -> modelMapper.map(tipKlijenta, TipKlijentaDTO.class))
                .collect(Collectors.toList());
        return tipKlijentaDTOS;
    }

    public List<TipKlijenta> convertListToEntity(List<TipKlijentaDTO> tipKlijentaDTOS) throws ParseException {
        List<TipKlijenta> tipKlijentas = new ArrayList<TipKlijenta>();
        tipKlijentas = tipKlijentaDTOS.stream().map(tk -> modelMapper.map(tk, TipKlijenta.class)).collect(Collectors.toList());
        return tipKlijentas;
    }

    public TipKlijentaDTO convertToDto(TipKlijenta tipKlijenta) {
        TipKlijentaDTO tipKlijentaDTO = modelMapper.map(tipKlijenta, TipKlijentaDTO.class);

        return tipKlijentaDTO;
    }

    public TipKlijenta convertToEntity(TipKlijentaDTO tipKlijentaDTO) throws ParseException {
        TipKlijenta tipKlijenta = modelMapper.map(tipKlijentaDTO, TipKlijenta.class);
        return tipKlijenta;
    }

}
