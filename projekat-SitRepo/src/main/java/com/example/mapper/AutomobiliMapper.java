package com.example.mapper;

import com.example.dto.AutomobiliDTO;
import com.example.model.Automobili;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AutomobiliMapper {

    @Autowired
    private ModelMapper modelMapper;


    public List<AutomobiliDTO> convertListToDto(List<Automobili> automobilis) {
        List<AutomobiliDTO> automobiliDTOS = new ArrayList<AutomobiliDTO>();
        automobiliDTOS = automobilis.stream().map(user -> modelMapper.map(user, AutomobiliDTO.class)).collect(Collectors.toList());
        return automobiliDTOS;
    }

    public List<Automobili> convertListToEntity(List<AutomobiliDTO> automobiliDTOS) throws ParseException {
        List<Automobili> automobilis= new ArrayList<Automobili>();
        automobilis= automobiliDTOS.stream().map(user -> modelMapper.map(user, Automobili.class)).collect(Collectors.toList());
        return automobilis;
    }

    public AutomobiliDTO convertToDto(Automobili automobili) {
        AutomobiliDTO automobiliDTO = modelMapper.map(automobili, AutomobiliDTO.class);

        return automobiliDTO;
    }

    public Automobili convertToEntity(AutomobiliDTO automobiliDTO) throws ParseException {
        Automobili automobili= modelMapper.map(automobiliDTO, Automobili.class);
        return automobili;
    }

}
